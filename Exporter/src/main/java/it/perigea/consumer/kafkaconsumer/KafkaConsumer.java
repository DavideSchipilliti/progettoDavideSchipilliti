package it.perigea.consumer.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@KafkaListener(topics="AggregatesResponse")
	public void consumeAggregatesResponse(Object response) {
		System.out.println(response);
	}
}
