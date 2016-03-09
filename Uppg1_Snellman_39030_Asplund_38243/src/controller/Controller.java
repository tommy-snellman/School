package controller;

import model.Currency;
import model.Model;
import model.Stock;
import view.CurrencyChangeEvent;
import view.CurrencyListener;
import view.SearchFormEvent;
import view.SearchListener;
import view.View;

public class Controller implements SearchListener, CurrencyListener {
	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	private void compareStocks(Stock s1, Stock s2) {
		String s = "";
		for (int i = 0; i < (s1.getSize() - 1); i++) {
			String cl1 = String.format("%.2f", Double.parseDouble(s1.getCloneClose(i)));
			String cl2 = String.format("%.2f", Double.parseDouble(s2.getCloneClose(i)));
			s += s1.getDate(i) + " stock: " + s1.getTicker() + " close: " + cl1 + " stock: " + s2.getTicker()
					+ " close: " + cl2 + "\n";
		}
		view.setText(s);
	}

	private void updateStocks(Stock s1, Stock s2, Currency curr) {
		for (int i = 0; i < s1.getSize(); i++) {
			s1.setClose(i, curr.getClose(i));
			s2.setClose(i, curr.getClose(i));
		}
	}

	@Override
	public void searchPerformed(SearchFormEvent event) {
		String t1 = event.getT1();
		String t2 = event.getT2();
		String d1 = event.getDate1();
		String d2 = event.getDate2();
		
		model.setStocks(t1, t2, d1, d2);
		
		model.getStock1().fetchData();
		model.getStock2().fetchData();

		compareStocks(model.getStock1(), model.getStock2());
	}

	@Override
	public void currencySwitch(CurrencyChangeEvent event) {
		String curr = event.getCurrency();
		String d1 = model.getStock1().getStart();
		String d2 = model.getStock1().getEnd();
		
		model.setCurrency(curr, d1, d2, model.getStock1().getSize());
		
		updateStocks(model.getStock1(), model.getStock2(), model.getCurrency());
		compareStocks(model.getStock1(), model.getStock2());
	}

}
