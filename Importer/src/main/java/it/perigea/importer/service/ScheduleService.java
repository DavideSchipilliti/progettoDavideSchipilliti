package it.perigea.importer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.perigea.importer.dto.ResponseDTO;
import it.perigea.importer.dto.ScheduleDTO;
import it.perigea.importer.entities.Schedule;
import it.perigea.importer.enums.State;
import it.perigea.importer.mappers.ScheduleMapper;
import it.perigea.importer.repository.ScheduleRepository;

/* Questa classe ha la funzione di gestire le schedulazioni, sia la loro persistensa (salvare nel DB gli oggetti di tipo Schedule)
 * sia la loro effettiva scedulazione, viene avviata la schedulazione tramite un TaskScheduler e salvata sulla mappa schedulesRunning.
*/

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private ScheduleMapper scheduleMapper;
	
	@Autowired
	private WebClientService webClientService;
	
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	private Map<Long, ScheduledFuture<?>> schedulesRunning;
	
	
	//Parte di Gestione della Persistenza
	
	public List<ScheduleDTO> viewAllSchedules(){
		
		List<Schedule> scheduleList=scheduleRepository.findAll();
		List<ScheduleDTO> scheduleListDTO=scheduleMapper.listScheduleToListScheduleDTO(scheduleList);
		return scheduleListDTO;
	}
	
	
	public ScheduleDTO viewScheduleById(Long id){
		
		Schedule schedule=scheduleRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Schedule con id=" + id + " non trovato."));
		ScheduleDTO scheduleDTO=scheduleMapper.scheduleToScheduleDTO(schedule);
		return scheduleDTO;
	}
	
	
	//Con un oggetto Optional controllo se la schedulazione è già presente nel DB (uso l'id), se presente elimino la schedulazione dallo
	//scheduler, aggiorno il valore nel DB e creo la nuova schedulazione.
	//Se la schedulazione è nuova la aggiungo al DB e la faccio partire dallo scheduler.
	@Transactional
	public ScheduleDTO setSchedule(ScheduleDTO scheduleToSaveDTO) {
		
		Optional<Schedule> scheduleToSaveOptional=null;
		ScheduleDTO scheduleSavedDTO=null;
		
		if (scheduleToSaveDTO.getId()!=null) {
		scheduleToSaveOptional=scheduleRepository.findById(scheduleToSaveDTO.getId());
		}
		
		if(scheduleToSaveOptional!=null && scheduleToSaveOptional.isPresent()) {
			Schedule scheduleToUpdate=scheduleToSaveOptional.get();
			Schedule scheduleUpdated=scheduleMapper.partialUpdate(scheduleToSaveDTO, scheduleToUpdate);		//Voglio davvero il partialUpdate?
			
			this.removeSchedule(scheduleToUpdate);
			this.addNewSchedule(scheduleUpdated);
			
			scheduleSavedDTO=scheduleMapper.scheduleToScheduleDTO(scheduleUpdated);
			
		} else {
			Schedule scheduleToSave=scheduleMapper.scheduleDTOToSchedule(scheduleToSaveDTO);
			Schedule scheduleSaved=scheduleRepository.save(scheduleToSave);
			this.addNewSchedule(scheduleSaved);
			scheduleSavedDTO=scheduleMapper.scheduleToScheduleDTO(scheduleSaved);
		}
		
		return scheduleSavedDTO;
	}
	
	
	//Al posto di eliminare dal DB una schedulazione uso removeSchedule() per assegnare state=stopped e interrompere la schedulazione dallo scheduler.
	//Uso l'id per verificare se la schedulazione esiste, altrimenti sollev un eccezione.
	@Transactional
	public ScheduleDTO stopSchedule(ScheduleDTO scheduleToStopDTO) {
		
		Optional<Schedule> scheduleToStopOptional=scheduleRepository.findById(scheduleToStopDTO.getId());
		Schedule scheduleToStop=scheduleToStopOptional.orElseThrow( () -> new EntityNotFoundException("Schedule con id=" + scheduleToStopDTO.getId() + " non trovato."));
		
		//Controllo che la schedulazione sia effettivamente da stoppare.
		if (scheduleToStop.getState()!=State.pending) {
			throw new IllegalArgumentException("La schedulazione non è in stato di pending.");
		}
		
		this.removeSchedule(scheduleToStop);
		scheduleToStop.setState(State.stopped);
		
		Schedule scheduleUpdated=scheduleRepository.save(scheduleToStop);
		ScheduleDTO scheduleUpdatedDTO=scheduleMapper.scheduleToScheduleDTO(scheduleUpdated);
		return scheduleUpdatedDTO;
	}
	
	@Transactional
	public ScheduleDTO startSchedule(ScheduleDTO scheduleToStartDTO) {
		
		Optional<Schedule> scheduleToStartOptional=scheduleRepository.findById(scheduleToStartDTO.getId());
		Schedule scheduleToStart=scheduleToStartOptional.orElseThrow( () -> new EntityNotFoundException("Schedule con id=" + scheduleToStartDTO.getId() + " non trovato."));
		
		if (scheduleToStart.getState()!=State.stopped) {
			throw new IllegalArgumentException("La schedulazione non è in stato di stopped.");
		}

		scheduleToStart.setState(State.pending);
		this.addNewSchedule(scheduleToStart);
		
		Schedule scheduleUpdated=scheduleRepository.save(scheduleToStart);
		ScheduleDTO scheduleUpdatedDTO=scheduleMapper.scheduleToScheduleDTO(scheduleUpdated);
		return scheduleUpdatedDTO;
	}
	
	
	//Elimina la schedulazione sia dallo scheduler che dal DB. Controllare cosa succede ai job (Run)
	@Transactional
	public ScheduleDTO deleteSchedule(ScheduleDTO scheduleDTO) {
		
		Optional<Schedule> scheduleOptional=scheduleRepository.findById(scheduleDTO.getId());
		Schedule schedule=scheduleOptional.orElseThrow( () -> new EntityNotFoundException("Schedule con id=" + scheduleDTO.getId() + " non trovato."));
		
		this.removeSchedule(schedule);
		
		scheduleRepository.delete(schedule);
		ScheduleDTO scheduleDeletedDTO=scheduleMapper.scheduleToScheduleDTO(schedule);
		return scheduleDeletedDTO;
	}
	
	
	@Transactional
	public List<ScheduleDTO> deleteAllSchedules() {
		
		List<Schedule> schedulesDeleted=scheduleRepository.findAll();
		schedulesDeleted.stream().forEach(schedule -> this.removeSchedule(schedule));
		scheduleRepository.deleteAll();
		List<ScheduleDTO> schedulesDeletedDTO=scheduleMapper.listScheduleToListScheduleDTO(schedulesDeleted);
		return schedulesDeletedDTO;
	}
	
	

	//Parte di Creazione delle Schedulazioni
	
	@PostConstruct	//dato che utilizzo dei bean è meglio farlo partire subito dopo la costruzione
	public void executeOnStartup() {
		schedulesRunning= new HashMap<>();
		List<Schedule> schedulesToRun=scheduleRepository.findAll();
		schedulesToRun.forEach(schedule -> addNewSchedule(schedule));
	}

	
	public void addNewSchedule(Schedule schedule) {
		if(schedule.getState() != State.pending) {
			return;
		}
		
		CronTrigger trigger=new CronTrigger(schedule.getCronString());
		
		//creo una lambda function ed evito di chiamare la classe RunnableTask
		ScheduledFuture<?> scheduled=taskScheduler.schedule(()-> run(schedule), trigger);

		//aggiungo la schedulazione all'HashMap, chiave=chiave di schedule valore=ScheduledFuture
		schedulesRunning.put(schedule.getId(), scheduled);
	}
	
	
	public void removeSchedule(Schedule schedule) {
		if(schedule.getState() == State.pending) {
			ScheduledFuture<?> removed=schedulesRunning.remove(schedule.getId());
			removed.cancel(false);	//true se voglio interrompere il task anche se è in esecuzione in questo momento
		}
	}
	
	public void removeAllSchedules() {		//da controllare
		schedulesRunning.forEach((id, element) -> element.cancel(false));
		schedulesRunning.clear();
	}
	
	private void run(Schedule schedule) {
		ResponseDTO response=null;
		String topic=null;
		
		switch(schedule.getTypeOfRequest()) {
			case Aggregates: 
				response=webClientService.getAggregates(schedule);
				topic="AggregatesResponse";
				break;
			case GroupedDaily: 
				response=webClientService.getGroupedDaily(schedule);
				topic="GroupedDailyResponse";
				break;
			case PreviousClose:
				response=webClientService.getPreviousClose(schedule);
				topic="PreviousCloseResponse";
				break;
		}
		
		//invio la risposta a Kafka
		if(response!=null) {
			kafkaProducerService.sendResponseToKafka(topic, response);
		}
	}
}
