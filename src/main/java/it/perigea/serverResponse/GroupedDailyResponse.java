package it.perigea.serverResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupedDailyResponse implements Serializable{
	
	private static final long serialVersionUID = -1274405829653402130L;
	
	private Integer queryCount;
	private Integer resultsCount;
	private boolean adjusted;
	private List<GroupedDailyResult> results;
	private String status;
	@JsonProperty("request_id")
	private String requestId;
	private Integer count;
	
	
	public GroupedDailyResponse() {
		super();
		results=new ArrayList<>();
	}
	
	
	//Getters and Setters
	public Integer getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}
	public Integer getResultsCount() {
		return resultsCount;
	}
	public void setResultsCount(Integer resultsCount) {
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
