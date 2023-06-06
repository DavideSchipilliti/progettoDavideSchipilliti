package it.perigea.webclient;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import it.perigea.customException.CustomClientErrorException;
import it.perigea.customException.CustomServerErrorException;
import it.perigea.entities.Timespan;
import it.perigea.serverResponse.AggregatesResponse;
import it.perigea.serverResponse.GroupedDailyResponse;
import it.perigea.serverResponse.PreviousCloseResponse;
import reactor.core.publisher.Mono;

// Questa classe si occupa di fare la chiamata alle API del Server.

@Component
public class ApiWebClient {
	
	private final String MY_SECRET_TOCKEN="TOrI8vcJ03RhtmdLbA2Ah25TE3ph0tb4";

	private WebClient client = WebClient.builder()
			  .baseUrl("https://api.polygon.io")
			  .defaultHeader("Authorization", "Bearer " + MY_SECRET_TOCKEN)
			  .build();
	
	//Ottieni le informazioni per una coppia di valuta in un dato intervallo di tempo e con una data frequenza. 
	public Optional<AggregatesResponse> aggregates(String forexTicker, int multiplier, Timespan timespan, Timestamp from, Timestamp to) {
		
		UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
		RequestBodySpec bodySpec = uriSpec.uri("/v2/aggs/ticker/{forexTicker}/range/{multiplier}/{timespan}/{from}/{to}",
				forexTicker, multiplier, timespan, from.getTime(), to.getTime());
		Mono<AggregatesResponse> response = bodySpec.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse ->
					Mono.error(new CustomClientErrorException("Client Error with reason: " + clientResponse.statusCode().getReasonPhrase())) )
				.onStatus(HttpStatus::is5xxServerError,clientResponse ->
					Mono.error(new CustomServerErrorException("Server Error with reason: " + clientResponse.statusCode().getReasonPhrase())) )
				.bodyToMono(AggregatesResponse.class)
				.onErrorResume(WebClientResponseException.class, ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
		
		return response.blockOptional();		//blocca il flusso al primo result e restituisce l'oggetto
	}
	
	//Ottieni i valori di tutte le coppie di valute in un dato giorno.
	public Optional<GroupedDailyResponse> groupedDaily(String date){
		
		UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
		RequestBodySpec bodySpec = uriSpec.uri("/v2/aggs/grouped/locale/global/market/fx/{date}", date);
		Mono<GroupedDailyResponse> response = bodySpec.retrieve()
			.onStatus(HttpStatus::is4xxClientError, clientResponse ->
				Mono.error(new CustomClientErrorException("Client Error with reason: " + clientResponse.statusCode().getReasonPhrase())) )
			.onStatus(HttpStatus::is5xxServerError,clientResponse ->
				Mono.error(new CustomServerErrorException("Server Error with reason: " + clientResponse.statusCode().getReasonPhrase())) )
			.bodyToMono(GroupedDailyResponse.class);
		
		return response.blockOptional();
	}
	
	//Ottieni i valori del giorno precedente di una coppia di valute.
	public Optional<PreviousCloseResponse> previousClose(String forexTicker){
		
		UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
		RequestBodySpec bodySpec = uriSpec.uri("/v2/aggs/ticker/{forexTicker}/prev", forexTicker);
		Mono<PreviousCloseResponse> response = bodySpec.retrieve()
			.onStatus(HttpStatus::is4xxClientError, clientResponse ->
				Mono.error(new CustomClientErrorException("Client Error with reason: " + clientResponse.statusCode().getReasonPhrase())) )
			.onStatus(HttpStatus::is5xxServerError,clientResponse ->
				Mono.error(new CustomServerErrorException("Server Error with reason: " + clientResponse.statusCode().getReasonPhrase())) )
			.bodyToMono(PreviousCloseResponse.class);
		
		return response.blockOptional();
	}
}
