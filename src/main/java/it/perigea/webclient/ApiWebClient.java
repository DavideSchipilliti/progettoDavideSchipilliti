package it.perigea.webclient;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import it.perigea.entities.Timespan;
import it.perigea.serverResponse.AggregatesResponse;
import reactor.core.publisher.Mono;

@Component
public class ApiWebClient {
	
	private final String MY_SECRET_TOCKET="TOrI8vcJ03RhtmdLbA2Ah25TE3ph0tb4";

	private WebClient client = WebClient.builder()
			  .baseUrl("https://api.polygon.io")
			  .defaultHeader("Authorization:", "Bearer " + MY_SECRET_TOCKET)
			  .build();
	
	public Mono<AggregatesResponse> aggregates(String forexTicker, int multiplier, Timespan timespan, int from, int to) {
		UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
		RequestBodySpec bodySpec = uriSpec.uri("/v2/aggs/ticker/{forexTicker}/range/{multiplier}/{timespan}/{from}/{to}", forexTicker, multiplier, timespan, from, to);
		Mono<AggregatesResponse> response = bodySpec.retrieve().bodyToMono(AggregatesResponse.class)
				.onErrorResume(WebClientResponseException.class, ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));;
		//così prendo solo il body, altrimenti Mono<ResponseEntity<AggregatesResponse>>
		
		return response;
	}
}
