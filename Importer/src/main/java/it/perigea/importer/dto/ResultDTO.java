package it.perigea.importer.dto;

import java.io.Serializable;

public class ResultDTO implements Serializable{

	private static final long serialVersionUID = -1272159966083998748L;
	
	private String ticker;
	private Float openPrice;
	private Float closePrice;
	private Float highestPrice;
	private Float lowestPrice;
	private Long tradingVolume;			//numero di unità scambiate
	private Float vwap;					//Volume-Weighted Average Price (prezzo medio ponderato per il volume)
	private Integer numberOfTransaction;
	
	
	//Getters and Setters
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
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
	public Integer getNumberOfTransaction() {
		return numberOfTransaction;
	}
	public void setNumberOfTransaction(Integer numberOfTransaction) {
		this.numberOfTransaction = numberOfTransaction;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
