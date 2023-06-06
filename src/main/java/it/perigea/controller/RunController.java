package it.perigea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.perigea.entities.Run;
import it.perigea.entities.Schedule;
import it.perigea.service.RunService;

@RestController
@RequestMapping("/run")
public class RunController {

	@Autowired
	private RunService service;
	
	@GetMapping("/getAllRun")
	public ResponseEntity<List<Run>> getAllRun(){
		List<Run> runList = service.viewAllRun();
		if(runList.isEmpty()) {
			return new ResponseEntity<List<Run>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Run>> (runList, HttpStatus.OK);
	}
	
	@GetMapping("/getRunBySchedule")
	public ResponseEntity<List<Run>> getRunBySchedule(@RequestBody Schedule schedule){
		List<Run> runList = service.viewAllRunBySchedule(schedule);
		if(runList.isEmpty()) {
			return new ResponseEntity<List<Run>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Run>> (runList, HttpStatus.OK);
	}
}
