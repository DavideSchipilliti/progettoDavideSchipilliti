package it.perigea.importer.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.importer.entities.Schedule;
import it.perigea.importer.repository.ScheduleRepository;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository repository;
	
	public List<Schedule> viewAllSchedule(){
		return repository.findAll();
	}
	
	public Schedule viewScheduleById(Long id){
		Schedule schedule= repository.findById(id).orElseThrow( () -> new EntityNotFoundException("Schedule con id=" + id + " non trovato."));
		return schedule;
	}
	
	public Schedule setSchedule(Schedule schedule) {
		repository.save(schedule);
		return schedule;
	}
	
	public Schedule deleteSchedule(Schedule schedule) {
		repository.delete(schedule);
		return schedule;
	}
	
}