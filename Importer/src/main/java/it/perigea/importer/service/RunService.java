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
	private RunRepository runRepository;
	
	public List<Run> viewAllRuns(){
		return runRepository.findAll();
	}
	
	public List<Run> viewAllRunsBySchedule(Schedule schedule){
		return runRepository.findAllBySchedule(schedule);
	}

	public Run setRun(Run run) {
		runRepository.save(run);
		return run;
	}
	
	public List<Run> deleteAllRuns(){
		List<Run> runsDeleted=runRepository.findAll();
		runRepository.deleteAll();
		return runsDeleted;
	}
}
