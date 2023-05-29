package it.perigea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.entities.Run;
import it.perigea.entities.Timespan;
import it.perigea.serverResponse.AggregatesResponse;
import it.perigea.webclient.ApiWebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

	@Autowired
	private ApiWebClient apiWebClient;
	
	public Run getAggregates(String forexTicker, int multiplier, Timespan timespan, int from, int to) {
		
		Mono<AggregatesResponse> result= apiWebClient.aggregates(forexTicker, multiplier, timespan, from, to);
		//result.map(t -> t.);
		
		//aggiungere il job sul DB
		return null;
	}
}
