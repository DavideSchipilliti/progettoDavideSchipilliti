package it.perigea.serverResponse;

public class AggregatesResult {

	private float closePrice;
	private float highestPrice;
	private float lowestPrice;
	private int numberOfTransaction;
	private float openPrice;
	private int startTimestamp;
	private long tradingVolume;			//numero di unità scambiate
	private float vwap;					//Volume-Weighted Average Price (prezzo medio ponderato per il volume)
	
	
	//Getters and Setters
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
	public int getNumberOfTransaction() {
		return numberOfTransaction;
	}
	public void setNumberOfTransaction(int numberOfTransaction) {
		this.numberOfTransaction = numberOfTransaction;
	}
	public float getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(float openPrice) {
		this.openPrice = openPrice;
	}
	public int getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(int startTimestamp) {
		this.startTimestamp = startTimestamp;
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
	
}
