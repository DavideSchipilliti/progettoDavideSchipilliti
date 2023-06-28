package it.perigea.importer.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDTO implements Serializable{
	
	private static final long serialVersionUID = 232379819743491448L;
	
	private String requestId;
	private Integer resultsCount;
	private List<ResultDTO> results;
	
	//Getters and Setters
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Integer getResultsCount() {
		return resultsCount;
	}
	public void setResultsCount(Integer resultsCount) {
		this.resultsCount = resultsCount;
	}
	public List<ResultDTO> getResults() {
		return results;
	}
	public void setResults(List<ResultDTO> results) {
		this.results = results;
	}
}
