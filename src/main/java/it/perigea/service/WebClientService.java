package it.perigea.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import it.perigea.entities.Run;
import it.perigea.entities.Schedule;
import it.perigea.entities.Timespan;
import it.perigea.serverResponse.AggregatesResponse;
import it.perigea.serverResponse.GroupedDailyResponse;
import it.perigea.serverResponse.PreviousCloseResponse;
import it.perigea.webclient.ApiWebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

	@Autowired
	private ApiWebClient apiWebClient;
	
	@Autowired
	private RunService runService;
	
	@Scheduled
	public void getAggregates(Schedule job, String forexTicker, int multiplier, Timespan timespan, int from, int to) {
		List<AggregatesResponse> responses=new ArrayList<>();
		
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		Mono<AggregatesResponse> result= apiWebClient.aggregates(forexTicker, multiplier, timespan, from, to);
		result.subscribe(responses::add);
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());
		
		AggregatesResponse response=responses.remove(0);
		String id=response.getRequestId();
		String status=response.getStatus();
		
		//Aggiungo il job sul DB usando RunService
		Run run = new Run(id, started, finished, status, job);
		runService.setRun(run);
		
		//Inviare result al kafkaService
	}
	
	@Scheduled
	public void getGroupedDaily(Schedule job, Timestamp date) {
		List<GroupedDailyResponse> responses=new ArrayList<>();
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		Mono<GroupedDailyResponse> result= apiWebClient.groupedDaily(date);
		result.subscribe(responses::add);
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());
		
		GroupedDailyResponse response=responses.remove(0);
		String id=response.getRequestId();
		String status=response.getStatus();
		
		//Aggiungo il job sul DB usando RunService
		Run run = new Run(id, started, finished, status, job);
		runService.setRun(run);
		
		//Inviare result al kafkaService
	}
	
	@Scheduled
	public void getPreviousClose(Schedule job, String forexTicker) {
		List<PreviousCloseResponse> responses=new ArrayList<>();
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		Mono<PreviousCloseResponse> result= apiWebClient.previousClose(forexTicker);
		result.subscribe(responses::add);
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());
		
		PreviousCloseResponse response=responses.remove(0);
		String id=response.getRequestId();
		String status=response.getStatus();
		
		//Aggiungo il job sul DB usando RunService
		Run run = new Run(id, started, finished, status, job);		//lo status dovrei personalizzarlo
		runService.setRun(run);
		
		//Inviare result al kafkaService
	}
}
