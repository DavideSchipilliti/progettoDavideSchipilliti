package it.perigea.importer.dto.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.perigea.importer.dto.ResultDTO;
import it.perigea.importer.dto.serverResponses.AggregatesResult;
import it.perigea.importer.dto.serverResponses.GroupedDailyResult;
import it.perigea.importer.dto.serverResponses.PreviousCloseResult;

@Component
public class ResultMapper {

	public ResultDTO aggregatesResultToResultDTO(AggregatesResult aggregatesResult) {
		ResultDTO resultDTO=new ResultDTO();
		resultDTO.setOpenPrice(aggregatesResult.getOpenPrice());
		resultDTO.setClosePrice(aggregatesResult.getClosePrice());
		resultDTO.setHighestPrice(aggregatesResult.getHighestPrice());
		resultDTO.setLowestPrice(aggregatesResult.getLowestPrice());
		resultDTO.setNumberOfTransaction(aggregatesResult.getNumberOfTransaction());
		resultDTO.setTradingVolume(aggregatesResult.getTradingVolume());
		resultDTO.setVwap(aggregatesResult.getVwap());
		resultDTO.setStartTimestamp(aggregatesResult.getStartTimestamp());
		return resultDTO;
	}
	
	public ResultDTO groupedDailyResultToResultDTO(GroupedDailyResult groupedDailyResult) {
		ResultDTO resultDTO=new ResultDTO();
		resultDTO.setTicker(groupedDailyResult.getTicker());
		resultDTO.setOpenPrice(groupedDailyResult.getOpenPrice());
		resultDTO.setClosePrice(groupedDailyResult.getClosePrice());
		resultDTO.setHighestPrice(groupedDailyResult.getHighestPrice());
		resultDTO.setLowestPrice(groupedDailyResult.getLowestPrice());
		resultDTO.setNumberOfTransaction(groupedDailyResult.getNumberOfTransaction());
		resultDTO.setTradingVolume(groupedDailyResult.getTradingVolume());
		resultDTO.setVwap(groupedDailyResult.getVwap());
		resultDTO.setStartTimestamp(groupedDailyResult.getStartTimestamp());
		return resultDTO;
	}
	
	public ResultDTO previousCloseResultToResultDTO(PreviousCloseResult previousCloseResult) {
		ResultDTO resultDTO=new ResultDTO();
		resultDTO.setTicker(previousCloseResult.getTicker());
		resultDTO.setOpenPrice(previousCloseResult.getOpenPrice());
		resultDTO.setClosePrice(previousCloseResult.getClosePrice());
		resultDTO.setHighestPrice(previousCloseResult.getHighestPrice());
		resultDTO.setLowestPrice(previousCloseResult.getLowestPrice());
		resultDTO.setNumberOfTransaction(previousCloseResult.getNumberOfTransaction());
		resultDTO.setTradingVolume(previousCloseResult.getTradingVolume());
		resultDTO.setVwap(previousCloseResult.getVwap());
		resultDTO.setStartTimestamp(previousCloseResult.getStartTimestamp());
		return resultDTO;
	}
	
	public List<ResultDTO> listAggregatesResultToListResultDTO(List<AggregatesResult> listAggregatesResult, String ticker){
		if (listAggregatesResult==null) {
			return null;
		}
		List<ResultDTO> listResultDTO = new ArrayList<>(listAggregatesResult.size());
        for (AggregatesResult aggregatesResult : listAggregatesResult) {
        	ResultDTO resultDTO=aggregatesResultToResultDTO(aggregatesResult);
        	resultDTO.setTicker(ticker);
            listResultDTO.add(resultDTO);
        }
        return listResultDTO;
	}
	
	public List<ResultDTO> listGroupedDailyResultToListResultDTO(List<GroupedDailyResult> listGroupedDailyResult){
		if (listGroupedDailyResult==null) {
			return null;
		}
		List<ResultDTO> listResultDTO = new ArrayList<>(listGroupedDailyResult.size());
        for (GroupedDailyResult groupedDailyResult : listGroupedDailyResult) {
            listResultDTO.add(groupedDailyResultToResultDTO(groupedDailyResult));
        }
        return listResultDTO;
	}
	
	public List<ResultDTO> listPreviousCloseResultToListResultDTO(List<PreviousCloseResult> listPreviousCloseResult){
		if (listPreviousCloseResult==null) {
			return null;
		}
		List<ResultDTO> listResultDTO = new ArrayList<>(listPreviousCloseResult.size());
        for (PreviousCloseResult previousCloseResult : listPreviousCloseResult) {
            listResultDTO.add(previousCloseResultToResultDTO(previousCloseResult));
        }
        return listResultDTO;
	}
	
}
