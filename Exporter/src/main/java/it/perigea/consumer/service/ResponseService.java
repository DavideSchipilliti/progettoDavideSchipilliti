package it.perigea.consumer.service;

import java.util.List;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.consumer.entities.Response;
import it.perigea.consumer.repository.ResponseRepository;

@Service
public class ResponseService {

	@Autowired
	ResponseRepository responseRepository;
	
	public Response viewResponseById(String idToFind) {
		Response response=responseRepository.findById(idToFind).orElseThrow(()->new ResourceNotFoundException("Result con id= " + idToFind + " non trovato."));
		return response;
	}
	public List<Response> viewAllResponses() {
		List<Response> allResponses=responseRepository.findAll();
		return allResponses;
	}
	
	public List<Response> viewResponsesByType(String typeToView) {
		List<Response> allResponses=responseRepository.findAllByTypeOfResponse(typeToView);
		return allResponses;
	}
	
	public Response setResponse(Response responseToInsert) {
		responseRepository.insert(responseToInsert);
		return responseToInsert;
	}
	
	public Response deleteResponse(Response responseToDelete) {
		responseRepository.delete(responseToDelete);
		return responseToDelete;
	}
	
	public List<Response> deleteAllResponses() {
		List<Response> deletedResponses=responseRepository.findAll();
		responseRepository.deleteAll();
		return deletedResponses;
	}
	
	public List<Response> deleteResponsesByType(String typeToDelete) {
		List<Response> deletedResponses=responseRepository.deleteByTypeOfResponse(typeToDelete);
		return deletedResponses;
	}
}
