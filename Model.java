package model;

public class Model {
	private Stock stock1, stock2;
	private Currency currency;
	
	public Stock getStock1() {
		return this.stock1;
	}
	
	public Stock getStock2() {
		return this.stock2;
	}
	
	public void setStocks(String t1, String t2, String dF, String dT) {
		this.stock1 = new Stock(t1, dF, dT);
		this.stock2 = new Stock(t2, dF, dT);
	}
	
	public void setCurrency(String currency, String start, String end, int size) {
		this.currency = new Currency(currency, start, end, size);
	}

	public Currency getCurrency() {
		return this.currency;
	}
}
