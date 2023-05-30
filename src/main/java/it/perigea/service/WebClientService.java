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
import it.perigea.webclient.ApiWebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

	@Autowired
	private ApiWebClient apiWebClient;
	
	@Autowired
	private RunService runService;
	
	@Scheduled
	public AggregatesResponse getAggregates(Schedule job, String forexTicker, int multiplier, Timespan timespan, int from, int to) {
		String id;
		String status;
		List<AggregatesResponse> responses=new ArrayList<>();
		
		Timestamp started=new Timestamp(System.currentTimeMillis());
		Mono<AggregatesResponse> result= apiWebClient.aggregates(forexTicker, multiplier, timespan, from, to);
		result.subscribe(responses::add);
		Timestamp finished=new Timestamp(System.currentTimeMillis());
		
		AggregatesResponse response=responses.remove(0);
		id=response.getRequestId();
		status=response.getStatus();
		
		//Aggiungo il job sul DB usando RunService
		Run run = new Run();
		run.setId(id);
		run.setStarted(started);
		run.setFinished(finished);
		run.setJob(job);
		run.setStatus(status);
		
		runService.setRun(run);
		
		return response;
	}
}
