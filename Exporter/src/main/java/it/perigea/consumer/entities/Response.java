package it.perigea.consumer.entities;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable{

	private static final long serialVersionUID = 8501999464471319969L;
	
	private String requestId;
	private Integer resultsCount;
	private List<Result> results;
	
	
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
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
}
