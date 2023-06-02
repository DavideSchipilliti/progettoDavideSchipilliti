package it.perigea.service;

import java.sql.Timestamp;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.entities.Run;
import it.perigea.entities.Schedule;
import it.perigea.entities.Timespan;
import it.perigea.serverResponse.AggregatesResponse;
import it.perigea.serverResponse.GroupedDailyResponse;
import it.perigea.serverResponse.PreviousCloseResponse;
import it.perigea.webclient.ApiWebClient;

//Questa classe si occupa di fare la chiamata a ApiWebClient, ricevere la risposta e con essa salvare il job sul DB e inviare i dati al KafkaService.

@Service
public class WebClientService {

	@Autowired
	private ApiWebClient apiWebClient;
	
	@Autowired
	private RunService runService;
	
	//@Scheduled
	public AggregatesResponse getAggregates(Schedule job, String forexTicker, int multiplier, Timespan timespan, Timestamp from, Timestamp to) {

		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		AggregatesResponse response= apiWebClient.aggregates(forexTicker, multiplier, timespan, from, to)
				.orElseThrow(() -> new EntityNotFoundException());
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());

		//Aggiungo il job sul DB usando RunService
		String id=response.getRequestId();
		String status=response.getStatus();
		Run run = new Run(id, started, finished, status, job);
		runService.setRun(run);
		
		//Inviare result al kafkaService
		return response;
	}
	
	//@Scheduled
	public GroupedDailyResponse getGroupedDaily(Schedule job, String date) {
		
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		GroupedDailyResponse response= apiWebClient.groupedDaily(date)
				.orElseThrow(() -> new EntityNotFoundException());
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());

		//Aggiungo il job sul DB usando RunService
		String id=response.getRequestId();
		String status=response.getStatus();
		Run run = new Run(id, started, finished, status, job);
		runService.setRun(run);
		
		//Inviare result al kafkaService
		return response;
	}
	
	//@Scheduled
	public PreviousCloseResponse getPreviousClose(Schedule job, String forexTicker) {
		
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		PreviousCloseResponse response= apiWebClient.previousClose(forexTicker)
				.orElseThrow(() -> new EntityNotFoundException());
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());

		//Aggiungo il job sul DB usando RunService
		String id=response.getRequestId();
		String status=response.getStatus();
		Run run = new Run(id, started, finished, status, job);		//lo status dovrei personalizzarlo
		runService.setRun(run);
		
		//Inviare result al kafkaService
		return response;
	}
}
