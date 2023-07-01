package it.perigea.importer.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.importer.dto.AggregatesResponse;
import it.perigea.importer.dto.GroupedDailyResponse;
import it.perigea.importer.dto.PreviousCloseResponse;
import it.perigea.importer.dto.ResponseDTO;
import it.perigea.importer.dto.RunDTO;
import it.perigea.importer.entities.Schedule;
import it.perigea.importer.enums.Timespan;
import it.perigea.importer.mappers.ResponseMapper;
import it.perigea.importer.webclient.ApiWebClient;

//Questa classe si occupa di fare la chiamata a ApiWebClient, ricevere la risposta e con essa salvare il job sul DB.

@Service
public class WebClientService {

	@Autowired
	private ApiWebClient apiWebClient;
	
	@Autowired
	private RunService runService;
	
	@Autowired
	private ResponseMapper responseMapper;
	
	public ResponseDTO getAggregates(Schedule schedule) {

		String forexTicker=schedule.getForexTicker();
		int multiplier=schedule.getMultiplier();
		Timespan timespan=schedule.getTimespan();
		Timestamp from=schedule.getDate1();
		Timestamp to=schedule.getDate2();
		
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		AggregatesResponse response= apiWebClient.aggregates(forexTicker, multiplier, timespan, from, to)
				.orElseThrow(() -> new EntityNotFoundException());
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());

		//Aggiungo il job sul DB usando RunService
		String status=response.getStatus();														//lo status dovrei personalizzarlo
		RunDTO run = new RunDTO(started, finished, status, schedule.getId());
		runService.setRun(run);
		
		ResponseDTO responseDTO=responseMapper.aggregatesResponseToResponseDTO(response);
		
		return responseDTO;
	}
	
	public ResponseDTO getGroupedDaily(Schedule schedule) {
		
		//trasformo il timestamp in una stringa
		Date date = new Date(schedule.getDate1().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		GroupedDailyResponse response= apiWebClient.groupedDaily(dateString)
				.orElseThrow(() -> new EntityNotFoundException());
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());

		//Aggiungo il job sul DB usando RunService
		String status=response.getStatus();
		RunDTO run = new RunDTO(started, finished, status, schedule.getId());
		runService.setRun(run);
		
		ResponseDTO responseDTO=responseMapper.groupedDailyResponseToResponseDTO(response);
		
		return responseDTO;
	}
	
	public ResponseDTO getPreviousClose(Schedule schedule) {
		
		String forexTicker=schedule.getForexTicker();
		
		Timestamp started=new Timestamp(System.currentTimeMillis());
		
		PreviousCloseResponse response= apiWebClient.previousClose(forexTicker)
				.orElseThrow(() -> new EntityNotFoundException());
		
		Timestamp finished=new Timestamp(System.currentTimeMillis());

		//Aggiungo il job sul DB usando RunService
		String status=response.getStatus();
		RunDTO run = new RunDTO(started, finished, status, schedule.getId());
		runService.setRun(run);
		
		ResponseDTO responseDTO=responseMapper.previousCloseResponseToResponseDTO(response);
		
		return responseDTO;
	}
}
