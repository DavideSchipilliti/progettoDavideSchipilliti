package it.perigea.importer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.perigea.importer.dto.ResponseDTO;

@Service
public class KafkaProducerService {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private ObjectMapper objectMapper=new ObjectMapper();
	
	public void sendResponseToKafka(String topic, ResponseDTO responseDTO) {
		String responseString=null;
		
		try {
			responseString=objectMapper.writeValueAsString(responseDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		kafkaTemplate.send(topic, responseString);
	}
}
