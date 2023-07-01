package it.perigea.importer.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import it.perigea.importer.dto.RunDTO;
import it.perigea.importer.entities.Run;

@Mapper(componentModel = "spring")
public interface RunMapper {

	Run runDTOToRun (RunDTO runDTO);
	
	RunDTO runToRunDTO (Run run);
	
	@InheritConfiguration(name = "runDTOToRun")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Run partialUpdate(RunDTO runDTO, @MappingTarget Run run);
	
	List<RunDTO> listRunToListRunDTO (List<Run> listRun);
	
	List<Run> listRunDTOToListRun (List<RunDTO> listRunDTO);
	
}
