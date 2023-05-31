package it.perigea.serverResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PreviousCloseResult {		//uguale a GroupedDailyResult
	
	@JsonProperty("T")
	private String ticker;
	@JsonProperty("v")
	private long tradingVolume;			//numero di unità scambiate
	@JsonProperty("vw")
	private float vwap;					//Volume-Weighted Average Price (prezzo medio ponderato per il volume)
	@JsonProperty("o")
	private float openPrice;
	@JsonProperty("c")
	private float closePrice;
	@JsonProperty("h")
	private float highestPrice;
	@JsonProperty("l")
	private float lowestPrice;
	@JsonProperty("t")
	private int startTimestamp;
	@JsonProperty("n")
	private int numberOfTransaction;
	
	
	//Getters and Setters
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public long getTradingVolume() {
		return tradingVolume;
	}
	public void setTradingVolume(long tradingVolume) {
		this.tradingVolume = tradingVolume;
	}
	public float getVwap() {
		return vwap;
	}
	public void setVwap(float vwap) {
		this.vwap = vwap;
	}
	public float getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(float openPrice) {
		this.openPrice = openPrice;
	}
	public float getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(float closePrice) {
		this.closePrice = closePrice;
	}
	public float getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(float highestPrice) {
		this.highestPrice = highestPrice;
	}
	public float getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(float lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public int getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(int startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public int getNumberOfTransaction() {
		return numberOfTransaction;
	}
	public void setNumberOfTransaction(int numberOfTransaction) {
		this.numberOfTransaction = numberOfTransaction;
	}
}
