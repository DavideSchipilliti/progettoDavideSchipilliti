package it.perigea.importer.serverResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregatesResponse implements Serializable{
	
	private static final long serialVersionUID = -2390861288966743800L;
	
	private String ticker;
	private Integer queryCount;
	private Integer resultsCount;
	private boolean adjusted;
	private List<AggregatesResult> results;
	private String status;
	@JsonProperty("request_id")
	private String requestId;
	private Integer count;
	private String next_url;	//nel caso in cui la risposta supera la lunghezza limite
	
	
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
	public Integer getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(Integer queryCount) {
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
	public void setResultsCount(Integer resultsCount) {
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getNext_url() {
		return next_url;
	}
	public void setNext_url(String next_url) {
		this.next_url = next_url;
	}
}
