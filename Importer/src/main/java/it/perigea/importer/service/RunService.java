package it.perigea.importer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perigea.importer.dto.RunDTO;
import it.perigea.importer.entities.Run;
import it.perigea.importer.mappers.RunMapper;
import it.perigea.importer.repository.RunRepository;

@Service
public class RunService {
	
	@Autowired
	private RunRepository runRepository;
	
	@Autowired
	private RunMapper runMapper;
	
	
	public List<RunDTO> viewAllRuns(){
		
		List<Run> runList=runRepository.findAll();
		List<RunDTO> runListDTO= runMapper.listRunToListRunDTO(runList);
		return runListDTO;
	}
	
	
	public List<RunDTO> viewAllRunsBySchedule(Long schedule){
		
		List<Run> runList=runRepository.findAllBySchedule(schedule);
		List<RunDTO> runListDTO= runMapper.listRunToListRunDTO(runList);
		return runListDTO;
	}

	
	public RunDTO setRun(RunDTO runToSaveDTO) {
		
		Run runToSave=runMapper.runDTOToRun(runToSaveDTO);
		Run runSaved=runRepository.save(runToSave);
		RunDTO runSavedDTO=runMapper.runToRunDTO(runSaved);
		
		return runSavedDTO;
	}
	
	
	public List<RunDTO> deleteAllRuns(){
		
		List<Run> runsDeleted=runRepository.findAll();
		runRepository.deleteAll();
		List<RunDTO> runsDeletedDTO=runMapper.listRunToListRunDTO(runsDeleted);
		return runsDeletedDTO;
	}
	
	
	public List<RunDTO> deleteAllRunsBySchedule(Long schedule){
		List<Run> runsDeleted=runRepository.deleteAllBySchedule(schedule);
		List<RunDTO> runsDeletedDTO=runMapper.listRunToListRunDTO(runsDeleted);
		return runsDeletedDTO;
	}
}
