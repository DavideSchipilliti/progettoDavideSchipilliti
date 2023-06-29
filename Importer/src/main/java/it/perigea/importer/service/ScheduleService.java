package it.perigea.importer.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.perigea.importer.dto.ScheduleDTO;
import it.perigea.importer.dto.mappers.ScheduleMapper;
import it.perigea.importer.entities.Schedule;
import it.perigea.importer.entities.enums.State;
import it.perigea.importer.repository.ScheduleRepository;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private ScheduleMapper scheduleMapper;
	
	@Autowired
	private SchedulerService scheduler;
	
	
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
		
		Optional<Schedule> scheduleToSaveOptional=scheduleRepository.findById(scheduleToSaveDTO.getId());
		ScheduleDTO scheduleSavedDTO=null;
		
		if(scheduleToSaveOptional.isPresent()) {
			Schedule scheduleToUpdate=scheduleToSaveOptional.get();
			Schedule scheduleUpdated=scheduleMapper.partialUpdate(scheduleToSaveDTO, scheduleToUpdate);		//Voglio davvero il partialUpdate?
			
			scheduler.removeSchedule(scheduleToUpdate);
			scheduler.addNewSchedule(scheduleUpdated);
			
			scheduleSavedDTO=scheduleMapper.scheduleToScheduleDTO(scheduleUpdated);
			
		} else {
			Schedule scheduleToSave=scheduleMapper.scheduleDTOToSchedule(scheduleToSaveDTO);
			Schedule scheduleSaved=scheduleRepository.save(scheduleToSave);
			scheduler.addNewSchedule(scheduleSaved);
			scheduleSavedDTO=scheduleMapper.scheduleToScheduleDTO(scheduleSaved);
		}
		
		return scheduleSavedDTO;
	}
	
	
	//Al posto di eliminare dal DB una schedulazione uso removeSchedule() per assegnare state=stopped e interrompere la schedulazione dallo scheduler.
	//Uso l'id per verificare se la schedulazione esiste, altrimenti sollev un eccezione.
	@Transactional
	public ScheduleDTO removeSchedule(ScheduleDTO scheduleToRemoveDTO) {
		
		Optional<Schedule> scheduleToRemoveOptional=scheduleRepository.findById(scheduleToRemoveDTO.getId());
		Schedule scheduleToRemove=scheduleToRemoveOptional.orElseThrow( () -> new EntityNotFoundException("Schedule con id=" + scheduleToRemoveDTO.getId() + " non trovato."));
		
		//Controllo che la schedulazione sia effettivamente da stoppare.
		if (scheduleToRemove.getState()!=State.pending) {
			throw new IllegalArgumentException("La schedulazione non è in stato di pending.");
		}
		
		scheduler.removeSchedule(scheduleToRemove);
		scheduleToRemove.setState(State.stopped);
		
		Schedule scheduleUpdated=scheduleRepository.save(scheduleToRemove);
		ScheduleDTO scheduleUpdatedDTO=scheduleMapper.scheduleToScheduleDTO(scheduleUpdated);
		return scheduleUpdatedDTO;
	}
	
	
	//Elimina la schedulazione sia dallo scheduler che dal DB. Controllare cosa succede ai job (Run)
	@Transactional
	public ScheduleDTO deleteSchedule(ScheduleDTO scheduleDTO) {
		
		Optional<Schedule> scheduleOptional=scheduleRepository.findById(scheduleDTO.getId());
		Schedule schedule=scheduleOptional.orElseThrow( () -> new EntityNotFoundException("Schedule con id=" + scheduleDTO.getId() + " non trovato."));
		
		scheduleRepository.delete(schedule);
		ScheduleDTO scheduleDeletedDTO=scheduleMapper.scheduleToScheduleDTO(schedule);
		return scheduleDeletedDTO;
	}
	
	
	@Transactional
	public List<ScheduleDTO> deleteAllSchedules() {
		
		List<Schedule> schedulesDeleted=scheduleRepository.findAll();
		schedulesDeleted.stream().forEach(schedule -> scheduler.removeSchedule(schedule));
		scheduleRepository.deleteAll();
		List<ScheduleDTO> schedulesDeletedDTO=scheduleMapper.listScheduleToListScheduleDTO(schedulesDeleted);
		return schedulesDeletedDTO;
	}
}
