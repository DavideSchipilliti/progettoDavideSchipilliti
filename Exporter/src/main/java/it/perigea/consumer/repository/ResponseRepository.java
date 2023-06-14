package it.perigea.consumer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.perigea.consumer.entities.Response;

public interface ResponseRepository extends MongoRepository<Response, String>{

	List<Response> findAllByTypeOfResponse(String type);
	List<Response> deleteAllByTypeOfResponse(String type);
}
