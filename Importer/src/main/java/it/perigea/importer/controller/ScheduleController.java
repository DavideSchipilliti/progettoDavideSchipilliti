package it.perigea.importer.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.perigea.importer.dto.ScheduleDTO;
import it.perigea.importer.service.ScheduleService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("/getAllSchedules")
	public ResponseEntity<List<ScheduleDTO>> getAllSchedules(){
		List<ScheduleDTO> schedules = scheduleService.viewAllSchedules();
		if (schedules.isEmpty()) {
			return new ResponseEntity<List<ScheduleDTO>> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ScheduleDTO>> (schedules, HttpStatus.OK);
	}
	
	@GetMapping("/getScheduleById/{id}")
	public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id){
		ScheduleDTO schedule = null;
		try {
			schedule=scheduleService.viewScheduleById(id);
		} catch (EntityNotFoundException e){
			return new ResponseEntity<ScheduleDTO> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ScheduleDTO> (schedule, HttpStatus.OK);
	}
	
	//Se esiste già uno schedule con quel id lo aggiorna altrimenti ne crea uno nuovo
	@PutMapping("/setSchedule")
	public ResponseEntity<ScheduleDTO> setSchedule(@RequestBody ScheduleDTO scheduleToSave){
		scheduleToSave.setCreation(new Timestamp(System.currentTimeMillis() ));
		ScheduleDTO scheduleSaved=scheduleService.setSchedule(scheduleToSave);
		return new ResponseEntity<ScheduleDTO> (scheduleSaved, HttpStatus.OK);
	}
	
	@PutMapping("/stopSchedule")
	public ResponseEntity<ScheduleDTO> stopSchedule(@RequestBody ScheduleDTO scheduleToStop){
		ScheduleDTO scheduleStopped = null;
		try {
			scheduleStopped=scheduleService.stopSchedule(scheduleToStop);
		} catch (EntityNotFoundException e){
			return new ResponseEntity<ScheduleDTO> (HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<ScheduleDTO> (HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ScheduleDTO> (scheduleStopped, HttpStatus.OK);
	}
	
	@PutMapping("/startSchedule")
	public ResponseEntity<ScheduleDTO> startSchedule(@RequestBody ScheduleDTO scheduleToStart){
		ScheduleDTO scheduleStarted = null;
		try {
			scheduleStarted=scheduleService.startSchedule(scheduleToStart);
		} catch (EntityNotFoundException e){
			return new ResponseEntity<ScheduleDTO> (HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<ScheduleDTO> (HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ScheduleDTO> (scheduleStarted, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSchedule")
	public ResponseEntity<ScheduleDTO> deleteSchedule(@RequestBody ScheduleDTO scheduleToDelete){
		ScheduleDTO scheduleDeleted=null;
		try {
			scheduleDeleted=scheduleService.deleteSchedule(scheduleToDelete);
		} catch (EntityNotFoundException e){
			return new ResponseEntity<ScheduleDTO> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ScheduleDTO> (scheduleDeleted, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAllSchedules")
	public ResponseEntity<List<ScheduleDTO>> deleteAllSchedules(){
		List<ScheduleDTO> schedulesDeleted = scheduleService.deleteAllSchedules();
		return new ResponseEntity<List<ScheduleDTO>> (schedulesDeleted, HttpStatus.OK);
	}
}
