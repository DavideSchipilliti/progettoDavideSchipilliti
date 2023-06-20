package it.perigea.importer.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import it.perigea.importer.entities.Schedule;
import it.perigea.importer.entities.State;
import it.perigea.importer.repository.ScheduleRepository;
import it.perigea.importer.service.WebClientService;

@Component
public class Scheduler {

	@Autowired		//uso schedule repository al posto di schedule service per non avere una dipendenza ciclica
	private ScheduleRepository scheduleRepository;	
	
	@Autowired
	private WebClientService webClientService;
	
	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;
	
	private Map<Long, ScheduledFuture<?>> schedulesRunning;
	
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
	
	private void run(Schedule schedule) {
		switch(schedule.getTypeOfRequest()) {
			case Aggregates: webClientService.getAggregates(schedule);
				break;
			case GroupedDaily: webClientService.getGroupedDaily(schedule);
				break;
			case PreviousClose: webClientService.getPreviousClose(schedule);
				break;
		}
	}
	
	public void removeSchedule(Schedule schedule) {
		if(schedule.getState() == State.pending) {
			ScheduledFuture<?> removed=schedulesRunning.remove(schedule.getId());
			removed.cancel(false);	//true se voglio interrompere il task anche se è in esecuzione in questo momento
		}
	}
}
