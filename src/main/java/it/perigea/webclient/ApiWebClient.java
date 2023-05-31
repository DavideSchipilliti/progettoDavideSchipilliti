package it.perigea.webclient;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import it.perigea.entities.Timespan;
import it.perigea.serverResponse.AggregatesResponse;
import it.perigea.serverResponse.GroupedDailyResponse;
import it.perigea.serverResponse.PreviousCloseResponse;
import reactor.core.publisher.Mono;

@Component
public class ApiWebClient {
	
	private final String MY_SECRET_TOCKET="TOrI8vcJ03RhtmdLbA2Ah25TE3ph0tb4";

	private WebClient client = WebClient.builder()
			  .baseUrl("https://api.polygon.io")
			  .defaultHeader("Authorization:", "Bearer " + MY_SECRET_TOCKET)
			  .build();
	
	//Ottieni le informazioni per una coppia di valuta in un dato intervallo di tempo e con una data frequenza. 
	public Optional<AggregatesResponse> aggregates(String forexTicker, int multiplier, Timespan timespan, int from, int to) {
		
		UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
		RequestBodySpec bodySpec = uriSpec.uri("/v2/aggs/ticker/{forexTicker}/range/{multiplier}/{timespan}/{from}/{to}", forexTicker, multiplier, timespan, from, to);
		Mono<AggregatesResponse> response = bodySpec.retrieve()
				/*
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    log.error("Client error while retrieving token from API: " + clientResponse.statusCode().getReasonPhrase());
                    log(LogLevel.ERROR, "Client error while retrieving token from API: " + clientResponse.statusCode().getReasonPhrase(), null);
                    return Mono.empty();
                    })
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> {
					throw new ServerException("Server error while retrieving tocken from API: " + clientResponse.statusCode().getReasonPhrase() );
				})
				*/
				.bodyToMono(AggregatesResponse.class)
				.onErrorResume(WebClientResponseException.class, ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
		//così prendo solo il body, altrimenti Mono<ResponseEntity<AggregatesResponse>>
		//aggiungere .onStatus()
		
		return response.blockOptional();		//blocca la chiamata al primo result e restituisce l'oggetto
	}
	
	//Ottieni i valori di tutte le coppie di valute in un dato giorno.
	public Optional<GroupedDailyResponse> groupedDaily(Timestamp date){
		
		UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
		RequestBodySpec bodySpec = uriSpec.uri("/v2/aggs/grouped/locale/global/market/fx/{date}", date);
		Mono<GroupedDailyResponse> response = bodySpec.retrieve().bodyToMono(GroupedDailyResponse.class);
		
		return response.blockOptional();
	}
	
	//Ottieni i valori del giorno precedente di una coppia di valute.
	public Optional<PreviousCloseResponse> previousClose(String forexTicker){
		
		UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
		RequestBodySpec bodySpec = uriSpec.uri("/v2/aggs/ticker/{forexTicker}/prev", forexTicker);
		Mono<PreviousCloseResponse> response = bodySpec.retrieve().bodyToMono(PreviousCloseResponse.class);
		
		return response.blockOptional();
	}
}
