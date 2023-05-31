package it.perigea.serverResponse;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregatesResponse {
	
	private String ticker;
	private int queryCount;
	private int resultsCount;
	private boolean adjusted;
	private List<AggregatesResult> results;
	private String status;
	@JsonProperty("request_id")
	private String requestId;
	private int count;
	private String next_url;
	
	
	public AggregatesResponse() {
		results=new ArrayList<>();
	}
	
	
	//Getters and Setters
	public boolean isAdjusted() {
		return adjusted;
	}
	public void setAdjusted(boolean adjusted) {
		this.adjusted = adjusted;
	}
	public int getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(int queryCount) {
		this.queryCount = queryCount;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public List<AggregatesResult> getResults() {
		return results;
	}
	public void setResults(List<AggregatesResult> results) {
		this.results = results;
	}
	public int getResultsCount() {
		return resultsCount;
	}
	public void setResultsCount(int resultsCount) {
		this.resultsCount = resultsCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNext_url() {
		return next_url;
	}
	public void setNext_url(String next_url) {
		this.next_url = next_url;
	}
}
