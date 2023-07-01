package it.perigea.importer.mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.perigea.importer.dto.AggregatesResponse;
import it.perigea.importer.dto.GroupedDailyResponse;
import it.perigea.importer.dto.PreviousCloseResponse;
import it.perigea.importer.dto.ResponseDTO;
import it.perigea.importer.dto.ResultDTO;

@Component
public class ResponseMapper {
	
	@Autowired
	private ResultMapper resultMapper;

	public ResponseDTO aggregatesResponseToResponseDTO(AggregatesResponse aggregatesResponse) {
		ResponseDTO responseDTO=new ResponseDTO();
		responseDTO.setRequestId(aggregatesResponse.getRequestId());
		responseDTO.setResultsCount(aggregatesResponse.getResultsCount());
		List<ResultDTO> resultsDTO=resultMapper.listAggregatesResultToListResultDTO(aggregatesResponse.getResults(), aggregatesResponse.getTicker());
		responseDTO.setResults(resultsDTO);
		return responseDTO;
	}
	
	public ResponseDTO groupedDailyResponseToResponseDTO(GroupedDailyResponse groupedDailyResponse) {
		ResponseDTO responseDTO=new ResponseDTO();
		responseDTO.setRequestId(groupedDailyResponse.getRequestId());
		responseDTO.setResultsCount(groupedDailyResponse.getResultsCount());
		List<ResultDTO> resultsDTO=resultMapper.listGroupedDailyResultToListResultDTO(groupedDailyResponse.getResults());
		responseDTO.setResults(resultsDTO);
		return responseDTO;
	}
	
	public ResponseDTO previousCloseResponseToResponseDTO(PreviousCloseResponse previousCloseResponse) {
		ResponseDTO responseDTO=new ResponseDTO();
		responseDTO.setRequestId(previousCloseResponse.getRequestId());
		responseDTO.setResultsCount(previousCloseResponse.getResultsCount());
		List<ResultDTO> resultsDTO=resultMapper.listPreviousCloseResultToListResultDTO(previousCloseResponse.getResults());
		responseDTO.setResults(resultsDTO);
		return responseDTO;
	}
}
