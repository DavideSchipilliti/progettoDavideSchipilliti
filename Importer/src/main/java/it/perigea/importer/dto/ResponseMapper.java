package it.perigea.importer.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.perigea.importer.serverResponse.AggregatesResponse;
import it.perigea.importer.serverResponse.GroupedDailyResponse;
import it.perigea.importer.serverResponse.PreviousCloseResponse;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ResultMapper.class})
public interface ResponseMapper {
	
	@Mapping(target = "results[].ticker", source = "ticker")
	ResponseDTO aggregatesResponseToResponseDTO(AggregatesResponse aggregatesResponse);
	
	ResponseDTO groupedDailyResponseToResponseDTO(GroupedDailyResponse groupedDailyResponse);
	
	ResponseDTO previousCloseResponseToResponseDTO(PreviousCloseResponse previousCloseResponse);
}
