package it.perigea.consumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.perigea.consumer.entities.Response;

public interface ResponseRepository extends MongoRepository<Response, String>{

}
