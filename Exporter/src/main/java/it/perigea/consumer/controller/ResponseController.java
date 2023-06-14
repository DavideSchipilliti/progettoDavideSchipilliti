package it.perigea.consumer.controller;

import java.util.List;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.perigea.consumer.entities.Response;
import it.perigea.consumer.service.ResponseService;

@RestController
@RequestMapping("/response")
public class ResponseController {

	@Autowired
	private ResponseService responseService;
	
	@GetMapping("/getAllResponses")
	public ResponseEntity<List<Response>> getAllResponses(){
		List<Response> responseList = responseService.viewAllResponses();
		if (responseList.isEmpty()) {
			return new ResponseEntity<List<Response>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Response>> (responseList, HttpStatus.OK);
	}
	
	@GetMapping("/getAllResponsesByType/{type}")
	public ResponseEntity<List<Response>> getAllResponsesByType(@PathVariable String type){
		List<Response> responseList = responseService.viewResponsesByType(type);
		if (responseList.isEmpty()) {
			return new ResponseEntity<List<Response>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Response>> (responseList, HttpStatus.OK);
	}
	
	@GetMapping("/getResponseById/{id}")
	public ResponseEntity<Response> getResponseById(@PathVariable String id){
		Response response=new Response();
		try {
			response = responseService.viewResponseById(id);
		} catch (ResourceNotFoundException e){
			return new ResponseEntity<Response> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response> (response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteResponse")
	public ResponseEntity<Response> deleteResponse(@RequestBody Response responseToDelete){
		Response responseDeleted = responseService.deleteResponse(responseToDelete);
		return new ResponseEntity<Response> (responseDeleted, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAllResponses")
	public ResponseEntity<List<Response>> deleteAllResponse(){
		List<Response> responsesDeleted = responseService.deleteAllResponses();
		return new ResponseEntity<List<Response>> (responsesDeleted, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAllResponsesByType/{type}")
	public ResponseEntity<List<Response>> deleteResponsesByType(@PathVariable String type){
		List<Response> responsesDeleted = responseService.deleteResponsesByType(type);
		return new ResponseEntity<List<Response>> (responsesDeleted, HttpStatus.OK);
	}
}
