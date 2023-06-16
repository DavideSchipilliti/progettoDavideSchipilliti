package it.perigea.importer.controller;

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

import it.perigea.importer.entities.Schedule;
import it.perigea.importer.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("/getAllSchedules")
	public ResponseEntity<List<Schedule>> getAllSchedules(){
		List<Schedule> schedules = scheduleService.viewAllSchedules();
		if (schedules.isEmpty()) {
			return new ResponseEntity<List<Schedule>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Schedule>> (schedules, HttpStatus.OK);
	}
	
	@GetMapping("/getScheduleById/{id}")
	public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id){
		Schedule schedule = new Schedule();
		try {
			schedule=scheduleService.viewScheduleById(id);
		} catch (EntityNotFoundException e){
			return new ResponseEntity<Schedule> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Schedule> (schedule, HttpStatus.OK);
	}
	
	@PutMapping("/setSchedule")
	public ResponseEntity<Schedule> setSchedule(@RequestBody Schedule scheduleToSave){
		scheduleToSave.setCreation(new Timestamp(System.currentTimeMillis() ));
		Schedule scheduleSaved=scheduleService.setSchedule(scheduleToSave);
		//Qui devo chiamare il metodo di SchedulerConfig addNewSchedule
		return new ResponseEntity<Schedule> (scheduleSaved, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSchedule")
	public ResponseEntity<Schedule> deleteSchedule(@RequestBody Schedule scheduleToDelete){
		Schedule scheduleDeleted = scheduleService.deleteSchedule(scheduleToDelete);
		//al posto di eliminarlo posso mettere status=stopped o aggiungere un campo deleted.
		return new ResponseEntity<Schedule> (scheduleDeleted, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAllSchedules")
	public ResponseEntity<List<Schedule>> deleteAllSchedules(){
		List<Schedule> schedulesDeleted = scheduleService.deleteAllSchedules();
		return new ResponseEntity<List<Schedule>> (schedulesDeleted, HttpStatus.OK);
	}
}
