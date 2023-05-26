package it.perigea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.perigea.entities.Run;
import it.perigea.entities.Schedule;

public interface RunRepository extends JpaRepository<Run, String>{

	List<Run> findAllByJob(Schedule job);

}
