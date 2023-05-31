package it.perigea.serverResponse;

public class PreviousCloseResult {		//uguale a GroupedDailyResult
	
	private String ticker;
	private long tradingVolume;			//numero di unità scambiate
	private float vwap;					//Volume-Weighted Average Price (prezzo medio ponderato per il volume)
	private float openPrice;
	private float closePrice;
	private float highestPrice;
	private float lowestPrice;
	private int startTimestamp;
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
