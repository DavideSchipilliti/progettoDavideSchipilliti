package it.perigea.importer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.importer.entities.Run;
import it.perigea.importer.entities.Schedule;
import it.perigea.importer.repository.RunRepository;

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

	public Run setRun(Run run) {
		repository.save(run);
		return run;
	}
	
	
}
