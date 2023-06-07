package it.perigea.importer.prova;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.perigea.importer.entities.Schedule;
import it.perigea.importer.entities.Timespan;
import it.perigea.importer.repository.ScheduleRepository;
import it.perigea.importer.serverResponse.AggregatesResponse;
import it.perigea.importer.serverResponse.GroupedDailyResponse;
import it.perigea.importer.serverResponse.PreviousCloseResponse;
import it.perigea.importer.service.WebClientService;

@RestController
@RequestMapping("/testWebClient")
public class provaWebClientController {
	
	@Autowired
	private WebClientService webClientService;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@GetMapping("/getAggregates/{forexTicker}/{multiplier}/{timespan}/{from}/{to}")		//from e to vanno bene sia come Timestamp (al millisecondo) sia come data del tipo YYYY-MM-DD
	public ResponseEntity<AggregatesResponse> testGetAggregates(@PathVariable String forexTicker, @PathVariable int multiplier, @PathVariable Timespan timespan, @PathVariable Timestamp from, @PathVariable Timestamp to) {
		
		Schedule schedule=scheduleRepository.findById((long) 6).orElseThrow();
		System.out.println( "Valori in ingresso al controller: " + forexTicker +" "+ multiplier +" "+ timespan +" "+ from +" "+ to);
		AggregatesResponse response= webClientService.getAggregates(schedule, forexTicker, multiplier, timespan, from, to);
		
		return new ResponseEntity<AggregatesResponse>(response, HttpStatus.OK);
		
	}
	
	
	@DateTimeFormat
	@GetMapping("/getGroupedDaily/{date}")			//date va bene solo come data del tipo YYYY-MM-DD
	public ResponseEntity<GroupedDailyResponse> testGetGroupedDaily(@PathVariable String date){
		
		Schedule schedule=scheduleRepository.findById((long) 6).orElseThrow();
		GroupedDailyResponse response= webClientService.getGroupedDaily(schedule, date);

		return new ResponseEntity<GroupedDailyResponse>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/getPreviousClose/{forexTicker}")
	public ResponseEntity<PreviousCloseResponse> testGetPreviousClose(@PathVariable String forexTicker){
		
		Schedule schedule=scheduleRepository.findById((long) 6).orElseThrow();
		PreviousCloseResponse response= webClientService.getPreviousClose(schedule, forexTicker);

		return new ResponseEntity<PreviousCloseResponse>(response, HttpStatus.OK);
	}
}
