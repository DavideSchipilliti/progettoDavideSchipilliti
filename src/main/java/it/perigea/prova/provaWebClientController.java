package it.perigea.prova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.perigea.entities.Schedule;
import it.perigea.entities.Timespan;
import it.perigea.serverResponse.AggregatesResponse;
import it.perigea.service.WebClientService;

@RestController
@RequestMapping("/testWebClient")
public class provaWebClientController {
	
	@Autowired
	WebClientService webClientService;
	
	@GetMapping("/getAggregates/{forexTicker}/{multipler}/{timespan}/{from}/{to}")
	public ResponseEntity<AggregatesResponse> testGetAggregates(@RequestParam String forexTicker, @RequestParam int multiplier, @RequestParam Timespan timespan, @RequestParam int from, @RequestParam int to) {
		
		AggregatesResponse response= webClientService.getAggregates(new Schedule(), forexTicker, multiplier, timespan, from, to);
		
		return new ResponseEntity<AggregatesResponse>(response, HttpStatus.OK);
		
	}
	
}
