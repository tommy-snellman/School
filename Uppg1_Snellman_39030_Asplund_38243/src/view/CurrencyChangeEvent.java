package view;

public class CurrencyChangeEvent {
	private String currency;
	private String dateF;
	private String dateT;
	
	public CurrencyChangeEvent(String curr, String dateF, String dateT) {
		this.currency = curr;
		this.dateF = dateF;
		this.dateT = dateT;
	}
	
	public String getCurrency() {
		return this.currency;
	}

	public String getDateF() {
		return dateF;
	}

	public String getDateT() {
		return dateT;
	}
}
