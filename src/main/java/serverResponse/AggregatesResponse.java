package serverResponse;

import java.util.ArrayList;
import java.util.List;

public class AggregatesResponse {
	private boolean adjusted;
	private int queryCount;
	private String requestId;
	private List<AggregatesResult> results;
	private int resultsCount;
	private String status;
	private String ticker;
	
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
}
