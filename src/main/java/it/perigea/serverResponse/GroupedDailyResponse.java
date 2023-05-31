package it.perigea.serverResponse;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupedDailyResponse {
	
	private int queryCount;
	private int resultsCount;
	private boolean adjusted;
	private List<GroupedDailyResult> results;
	private String status;
	@JsonProperty("request_id")
	private String requestId;
	private int count;
	
	
	public GroupedDailyResponse() {
		super();
		results=new ArrayList<>();
	}
	
	
	//Getters and Setters
	public int getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(int queryCount) {
		this.queryCount = queryCount;
	}
	public int getResultsCount() {
		return resultsCount;
	}
	public void setResultsCount(int resultsCount) {
		this.resultsCount = resultsCount;
	}
	public boolean isAdjusted() {
		return adjusted;
	}
	public void setAdjusted(boolean adjusted) {
		this.adjusted = adjusted;
	}
	public List<GroupedDailyResult> getResults() {
		return results;
	}
	public void setResults(List<GroupedDailyResult> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
