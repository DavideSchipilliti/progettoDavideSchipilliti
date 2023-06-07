package it.perigea.importer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.perigea.importer.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}
