package it.perigea.importer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.perigea.importer.entities.Run;
import it.perigea.importer.entities.Schedule;

public interface RunRepository extends JpaRepository<Run, String>{

	List<Run> findAllByJob(Schedule job);

}
