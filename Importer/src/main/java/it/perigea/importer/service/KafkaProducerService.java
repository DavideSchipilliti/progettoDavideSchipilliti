package it.perigea.importer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import it.perigea.importer.dto.ResponseDTO;

@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, ResponseDTO> kafkaTemplate;
	
	public void sendMessage(String topic, ResponseDTO message) {
		kafkaTemplate.send(topic, message);
	}
 }
