package it.perigea.importer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.perigea.importer.dto.RunDTO;
import it.perigea.importer.service.RunService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/run")
public class RunController {

	@Autowired
	private RunService runService;
	
	@GetMapping("/getAllRuns")
	public ResponseEntity<List<RunDTO>> getAllRuns(){
		List<RunDTO> runs = runService.viewAllRuns();
		if(runs.isEmpty()) {
			return new ResponseEntity<List<RunDTO>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>> (runs, HttpStatus.OK);
	}
	
	@GetMapping("/getRunsBySchedule")
	public ResponseEntity<List<RunDTO>> getRunsBySchedule(@PathVariable Long schedule){
		List<RunDTO> runs = runService.viewAllRunsBySchedule(schedule);
		if(runs.isEmpty()) {
			return new ResponseEntity<List<RunDTO>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>> (runs, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAllRuns")
	public ResponseEntity<List<RunDTO>> deleteAllRuns(){
		List<RunDTO> runsDeleted = runService.deleteAllRuns();
		return new ResponseEntity<List<RunDTO>> (runsDeleted, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAllRunsBySchedule")
	public ResponseEntity<List<RunDTO>> deleteAllRunsBySchedule(@PathVariable Long schedule){
		List<RunDTO> runsDeleted = runService.deleteAllRunsBySchedule(schedule);
		return new ResponseEntity<List<RunDTO>> (runsDeleted, HttpStatus.OK);
	}
}
