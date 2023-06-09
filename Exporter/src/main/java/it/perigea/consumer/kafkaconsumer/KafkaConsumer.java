package it.perigea.consumer.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import it.perigea.consumer.entities.Response;

@Component
public class KafkaConsumer {

	@KafkaListener(topics="AggregatesResponse", groupId = "Response", containerFactory = "responseKafkaListenerContainerFactory")
	public void listenAggregatesResponse(Response response) {
		System.out.println("Ricevuto AggregatesResponse: " + response);
	}
	
	@KafkaListener(topics="GroupedDailyResponse", groupId = "Response", containerFactory = "responseKafkaListenerContainerFactory")
	public void listenGroupedDailyResponse(Response response) {
		System.out.println("Ricevuto GroupedDailyResponse: " + response);
	}
	
	@KafkaListener(topics="PreviousCloseResponse", groupId = "Response", containerFactory = "responseKafkaListenerContainerFactory")
	public void listenPreviousCloseResponse(Response response) {
		System.out.println("Ricevuto PreviousCloseResponse: " + response);
	}
}
