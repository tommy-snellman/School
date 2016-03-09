package view;

public class CurrencyChangeEvent {
	private String currency;

	public CurrencyChangeEvent(String curr) {
		this.currency = curr;
	}

	public String getCurrency() {
		return this.currency;
	}
}
