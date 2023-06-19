package it.perigea.importer.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.perigea.importer.dto.ResponseDTO;
import it.perigea.importer.entities.Run;
import it.perigea.importer.entities.Schedule;
import it.perigea.importer.entities.Timespan;
import it.perigea.importer.mapper.ResponseMapper;
import it.perigea.importer.serverResponse.AggregatesResponse;
import it.perigea.importer.serverResponse.GroupedDailyResponse;
import it.perigea.importer.serverResponse.PreviousCloseResponse;
import it.perigea.importer.webclient.ApiWebClient;

//Questa classe si occupa di fare la chiamata a ApiWebClient, ricevere la risposta e con essa salvare il job sul DB e inviare i dati al KafkaService.

@Service
public class WebClientService {

	@Autowired
	private ApiWebClient apiWebClient;
	
	@Autowired
	private RunService runService;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private ResponseMapper responseMapper;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public AggregatesResponse getAggregates(Schedule job) {

		String forexTicker=job.getForexTicker();
		int multiplier=job.getMultiplier();
		Timespan timespan=job.getTimespan();
		Timestamp from=job.getStart();
		Timestamp to=job.getStop();
		
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		AggregatesResponse response= apiWebClient.aggregates(forexTicker, multiplier, timespan, from, to)
				.orElseThrow(() -> new EntityNotFoundException());
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());

		//Aggiungo il job sul DB usando RunService
		String id=response.getRequestId();
		String status=response.getStatus();
		Run run = new Run(id, started, finished, status, job);
		runService.setRun(run);
		
		//Invio result al kafkaService
		ResponseDTO responseDTO=responseMapper.aggregatesResponseToResponseDTO(response);
		sendResponseToKafka("AggregatesResponse", responseDTO);
		
		return response;
	}
	
	public GroupedDailyResponse getGroupedDaily(Schedule job) {
		
		//trasformo il timestamp in una stringa
		Date date = new Date(job.getStart().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		GroupedDailyResponse response= apiWebClient.groupedDaily(dateString)
				.orElseThrow(() -> new EntityNotFoundException());
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());

		//Aggiungo il job sul DB usando RunService
		String id=response.getRequestId();
		String status=response.getStatus();
		Run run = new Run(id, started, finished, status, job);
		runService.setRun(run);
		
		//Invio result al kafkaService
		ResponseDTO responseDTO=responseMapper.groupedDailyResponseToResponseDTO(response);
		sendResponseToKafka("GroupedDailyResponse", responseDTO);
		
		return response;
	}
	
	public PreviousCloseResponse getPreviousClose(Schedule job) {
		
		String forexTicker=job.getForexTicker();
		
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
		ResponseDTO responseDTO=responseMapper.previousCloseResponseToResponseDTO(response);
		sendResponseToKafka("PreviousCloseResponse", responseDTO);
		
		return response;
	}
	
	private void sendResponseToKafka(String topic, ResponseDTO responseDTO) {
		String responseString=new String();
		try {
			responseString=objectMapper.writeValueAsString(responseDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		kafkaTemplate.send(topic, responseString);
	}
}
