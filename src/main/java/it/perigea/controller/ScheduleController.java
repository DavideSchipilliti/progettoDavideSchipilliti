package it.perigea.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.perigea.entities.Schedule;
import it.perigea.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleService service;
	
	@GetMapping("/getAllSchedule")
	public ResponseEntity<List<Schedule>> getAllSchedule(){
		List<Schedule> scheduleList = service.viewAllSchedule();
		if (scheduleList.isEmpty()) {
			return new ResponseEntity<List<Schedule>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Schedule>> (scheduleList, HttpStatus.OK);
	}
	
	@GetMapping("/getScheduleById/{id}")
	public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id){
		Schedule schedule = new Schedule();
		try {
			schedule= service.viewScheduleById(id);
		} catch (EntityNotFoundException e){
			return new ResponseEntity<Schedule> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Schedule> (schedule, HttpStatus.OK);
	}
	
	@PutMapping("/setSchedule")
	public ResponseEntity<Schedule> setSchedule (@RequestBody Schedule scheduleToSave){
		scheduleToSave.setCreation(new Timestamp(System.currentTimeMillis() ));
		Schedule scheduleSaved = service.setSchedule(scheduleToSave);
		return new ResponseEntity<Schedule> (scheduleSaved, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSchedule")
	public ResponseEntity<Schedule> deleteSchedule (@RequestBody Schedule scheduleToDelete){
		Schedule scheduleDeleted = service.deleteSchedule(scheduleToDelete);
		return new ResponseEntity<Schedule> (scheduleDeleted, HttpStatus.OK);
	}
	
}
