package it.perigea.consumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.consumer.entities.Response;
import it.perigea.consumer.repository.ResponseRepository;

@Service
public class ResponseService {

	@Autowired
	ResponseRepository responseRepository;
	
	public List<Response> viewAllResponse() {
		List<Response> allResponses=responseRepository.findAll();
		return allResponses;
	}
	
	public Response setResponse(Response response) {
		responseRepository.insert(response);
		return response;
	}
}
