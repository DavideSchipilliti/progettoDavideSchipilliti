package it.perigea.importer.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.importer.entities.Schedule;
import it.perigea.importer.entities.State;
import it.perigea.importer.repository.ScheduleRepository;
import it.perigea.importer.scheduler.Scheduler;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private Scheduler scheduler;
	
	public List<Schedule> viewAllSchedules(){
		return scheduleRepository.findAll();
	}
	
	public Schedule viewScheduleById(Long id){
		Schedule schedule=scheduleRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Schedule con id=" + id + " non trovato."));
		return schedule;
	}
	
	public Schedule setSchedule(Schedule schedule) {
		scheduleRepository.save(schedule);
		scheduler.addNewSchedule(schedule);
		return schedule;
	}
	
	public Schedule removeSchedule(Schedule schedule) {
		scheduler.removeSchedule(schedule);
		schedule.setState(State.stopped);
		Schedule scheduleUpdated=scheduleRepository.save(schedule);
		return scheduleUpdated;
	}
	
	public Schedule deleteSchedule(Schedule schedule) {
		scheduleRepository.delete(schedule);
		return schedule;
	}
	
	public List<Schedule> deleteAllSchedules() {
		List<Schedule> schedulesDeleted=scheduleRepository.findAll();
		scheduleRepository.deleteAll();
		return schedulesDeleted;
	}
}
