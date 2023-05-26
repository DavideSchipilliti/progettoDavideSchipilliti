package it.perigea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.entities.Run;
import it.perigea.entities.Schedule;
import it.perigea.repository.RunRepository;

@Service
public class RunService {
	
	@Autowired
	private RunRepository repository;
	
	public List<Run> viewAllRun(){
		return repository.findAll();
	}
	
	public List<Run> viewAllRunBySchedule(Schedule schedule){
		return repository.findAllByJob(schedule);
	}
	
	
}
