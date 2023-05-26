package it.perigea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.perigea.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}
