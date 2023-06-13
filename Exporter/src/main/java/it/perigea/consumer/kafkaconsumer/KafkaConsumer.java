
package it.perigea.consumer.kafkaconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.perigea.consumer.entities.Response;
import it.perigea.consumer.service.ResponseService;

@Component
public class KafkaConsumer {

	@Autowired
	private ResponseService responseService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@KafkaListener(topics="AggregatesResponse", groupId="Response", containerFactory="responseKafkaListenerContainerFactory")
	public void listenAggregatesResponse(String response) {
		System.out.println("Ricevuto AggregatesResponse: " + response);
		
		//aggiungo la risposta al mongoDB
		Response responseDTO = stringToResponseDTO(response);
		responseDTO.setTypeOfResponse("AggregatesResponse");
		responseService.setResponse(responseDTO);
	}
	
	@KafkaListener(topics="GroupedDailyResponse", groupId = "Response", containerFactory = "responseKafkaListenerContainerFactory")
	public void listenGroupedDailyResponse(String response) {
		System.out.println("Ricevuto GroupedDailyResponse: " + response);
		
		//aggiungo la risposta al mongoDB
		Response responseDTO = stringToResponseDTO(response);
		responseDTO.setTypeOfResponse("GroupedDailyResponse");
		responseService.setResponse(responseDTO);
	}
	
	@KafkaListener(topics="PreviousCloseResponse", groupId = "Response", containerFactory = "responseKafkaListenerContainerFactory")
	public void listenPreviousCloseResponse(String response) {
		System.out.println("Ricevuto PreviousCloseResponse: " + response);
		
		//aggiungo la risposta al mongoDB
		Response responseDTO = stringToResponseDTO(response);
		responseDTO.setTypeOfResponse("PreviousCloseResponse");
		responseService.setResponse(responseDTO);
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
