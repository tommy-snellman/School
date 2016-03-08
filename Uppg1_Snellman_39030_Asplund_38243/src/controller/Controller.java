package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	private Stock stock1, stock2;
	private Currency currency;
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	
	private void compareStocks(Stock s1, Stock s2) {
		for (int i = 0; i < (s1.getSize() - 1); i++) {
			String cl1 = String.format("%.2f", Double.parseDouble(s1.getClose(i)));
			String cl2 = String.format("%.2f", Double.parseDouble(s2.getClose(i)));
			System.out.println(s1.getDate(i) + " stock: " + s1.getTicker() + " close: " + cl1
											+ " stock: " + s2.getTicker() + " close: " + cl2);
		}
	}
	
	private void updateStocks(Stock s1, Stock s2) {
		
	}
	
	@Override
	public void searchPerformed(SearchFormEvent event) {
		String t1 = event.getT1();
		String t2 = event.getT2();
		String d1 = event.getDate1();
		String d2 = event.getDate2(); 
		
		stock1 = new Stock(t1, d1, d2);
		stock2 = new Stock(t2, d1, d2);
		stock1.fetchData();
		stock2.fetchData();
		
		compareStocks(stock1, stock2);
	}

	@Override
	public void currencySwitch(CurrencyChangeEvent event) {
		String curr = event.getCurrency();
		String d1 = event.getDateF();
		String d2 = event.getDateT();
		
		currency = new Currency(curr, d1, d2);
	}
	
	
}
