package it.perigea.importer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.perigea.importer.entities.Run;
import it.perigea.importer.entities.Schedule;
import it.perigea.importer.service.RunService;

@RestController
@RequestMapping("/run")
public class RunController {

	@Autowired
	private RunService runService;
	
	@GetMapping("/getAllRuns")
	public ResponseEntity<List<Run>> getAllRuns(){
		List<Run> runs = runService.viewAllRuns();
		if(runs.isEmpty()) {
			return new ResponseEntity<List<Run>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Run>> (runs, HttpStatus.OK);
	}
	
	@GetMapping("/getRunsBySchedule")
	public ResponseEntity<List<Run>> getRunsBySchedule(@RequestBody Schedule schedule){
		List<Run> runs = runService.viewAllRunsBySchedule(schedule);
		if(runs.isEmpty()) {
			return new ResponseEntity<List<Run>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Run>> (runs, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAllRuns")
	public ResponseEntity<List<Run>> deleteAllRuns(){
		List<Run> runsDeleted = runService.deleteAllRuns();
		return new ResponseEntity<List<Run>> (runsDeleted, HttpStatus.OK);
	}
}
