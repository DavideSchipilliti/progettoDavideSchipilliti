
package it.perigea.consumer.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.perigea.consumer.entities.Response;

@Component
public class KafkaConsumer {

	private ObjectMapper objectMapper=new ObjectMapper();
	
	@KafkaListener(topics="AggregatesResponse", groupId="Response", containerFactory="responseKafkaListenerContainerFactory")
	public void listenAggregatesResponse(String response) {
		System.out.println("Ricevuto AggregatesResponse: " + response);
		
		Response responseDTO = stringToResponseDTO(response);
		System.out.println("responseDTO: " + responseDTO.getRequestId());
	}
	
	@KafkaListener(topics="GroupedDailyResponse", groupId = "Response", containerFactory = "responseKafkaListenerContainerFactory")
	public void listenGroupedDailyResponse(String response) {
		System.out.println("Ricevuto GroupedDailyResponse: " + response);
		
		Response responseDTO = stringToResponseDTO(response);
		System.out.println("responseDTO: " + responseDTO);
	}
	
	@KafkaListener(topics="PreviousCloseResponse", groupId = "Response", containerFactory = "responseKafkaListenerContainerFactory")
	public void listenPreviousCloseResponse(String response) {
		System.out.println("Ricevuto PreviousCloseResponse: " + response);
		
		Response responseDTO = stringToResponseDTO(response);
		System.out.println("responseDTO: " + responseDTO);
	}
	
	//Riceve in ingresso una stringa e restituisce il relativo oggetto di tipo Response
	private Response stringToResponseDTO(String response) {
		Response responseDTO=new Response();
		try {
			responseDTO=objectMapper.readValue(response, Response.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return responseDTO;
	}
}
