package it.perigea.importer.dto;

import java.util.List;

import org.mapstruct.Mapper;

import it.perigea.importer.serverResponse.AggregatesResult;
import it.perigea.importer.serverResponse.GroupedDailyResult;
import it.perigea.importer.serverResponse.PreviousCloseResult;

@Mapper(componentModel = "spring")
public interface ResultMapper {

	ResultDTO aggregatesResultToResultDTO(AggregatesResult aggregatesResult);
	
	ResultDTO groupedDailyResultToResultDTO(GroupedDailyResult groupedDailyResult);
	
	ResultDTO previousCloseResultToResultDTO(PreviousCloseResult previousCloseResult);
	
	List<ResultDTO> listAggregatesResultToListResultDTO(List<AggregatesResult> listAggregatesResult);
	
}
