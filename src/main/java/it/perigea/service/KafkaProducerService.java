package it.perigea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendMessage(String topic, Object message) {
		kafkaTemplate.send(topic, message);
	}
 }
