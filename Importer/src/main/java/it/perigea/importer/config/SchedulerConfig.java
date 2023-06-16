package it.perigea.importer.config;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import it.perigea.importer.entities.Schedule;
import it.perigea.importer.entities.State;
import it.perigea.importer.service.ScheduleService;
import it.perigea.importer.service.WebClientService;

@Configuration
@EnableScheduling
public class SchedulerConfig{
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private WebClientService webClientService;
	
	private List<Schedule> schedulesToRun;
	private Map<Long, ScheduledFuture<?>> schedulesRunning;
	
	@PostConstruct	//dato che utilizzo dei bean è meglio farlo partire subito dopo la costruzione
	public void executeOnStartup() {
		schedulesToRun=scheduleService.viewAllSchedules();
		schedulesToRun.forEach(schedule -> addNewSchedule(schedule));
	}

	public void addNewSchedule(Schedule schedule) {
		if(!schedule.getState().equals(State.pending)) {
			return;
		}
		
		ThreadPoolTaskScheduler taskScheduler=new ThreadPoolTaskScheduler();
		
		CronTrigger trigger=new CronTrigger(schedule.getCronString());
		
		//creo una lambda function ed evito di chiamare la classe RunnableTask
		ScheduledFuture<?> scheduled=taskScheduler.schedule(()->run(schedule), trigger);
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
}
