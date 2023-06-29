package it.perigea.importer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.perigea.importer.entities.Run;

public interface RunRepository extends JpaRepository<Run, String>{

	List<Run> findAllBySchedule(Long schedule);
	
	List<Run> deleteAllBySchedule(Long schedule);

}
