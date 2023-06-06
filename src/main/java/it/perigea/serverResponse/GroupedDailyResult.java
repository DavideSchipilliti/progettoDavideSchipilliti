package it.perigea.serverResponse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupedDailyResult implements Serializable{

	private static final long serialVersionUID = -4680667528024770394L;
	
	@JsonProperty("T")
	private String ticker;
	@JsonProperty("v")
	private Long tradingVolume;
	@JsonProperty("vw")
	private Float vwap;
	@JsonProperty("o")
	private Float openPrice;
	@JsonProperty("c")
	private Float closePrice;
	@JsonProperty("h")
	private Float highestPrice;
	@JsonProperty("l")
	private Float lowestPrice;
	@JsonProperty("t")
	private Long startTimestamp;
	@JsonProperty("n")
	private Integer numberOfTransaction;
	
	
	//Getters and Setters
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public Long getTradingVolume() {
		return tradingVolume;
	}
	public void setTradingVolume(Long tradingVolume) {
		this.tradingVolume = tradingVolume;
	}
	public Float getVwap() {
		return vwap;
	}
	public void setVwap(Float vwap) {
		this.vwap = vwap;
	}
	public Float getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Float openPrice) {
		this.openPrice = openPrice;
	}
	public Float getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Float closePrice) {
		this.closePrice = closePrice;
	}
	public Float getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(Float highestPrice) {
		this.highestPrice = highestPrice;
	}
	public Float getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(Float lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public Long getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(Long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public Integer getNumberOfTransaction() {
		return numberOfTransaction;
	}
	public void setNumberOfTransaction(Integer numberOfTransaction) {
		this.numberOfTransaction = numberOfTransaction;
	}
}
