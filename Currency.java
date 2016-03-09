package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Currency {
	private String currency;
	private String start;
	private String end;
	private ArrayList<Node> data = new ArrayList<>();

	public Currency(String curr, String start, String end, int size) {
		this.currency = curr;
		this.start = start;
		this.end = end;
		if (curr.equals("USD")) setCurrencyData(size);
		else fetchCurrencyData(this.currency, this.start, this.end);
	}

	private void setCurrencyData(int size) {
		for (int i = 0; i < size; i++) {
			data.add(new Node("1", "1", "1", "1", "1", "1", "1"));
		}
	}

	public void fetchCurrencyData(String curr, String dateF, String dateT) {
		try {
			String date = dateF + "." + dateT;
			String[] tmp = date.split("\\.");
			
			URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + curr + "=X&a=" + tmp[1] + "&b=" + tmp[0]
					+ "&c=" + tmp[2] + "&d=" + tmp[4] + "&e=" + tmp[3] + "&f=" + tmp[5] + "&g=d&ignore=.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String s;
			while ((s = reader.readLine()) != null) {
				String[] info = s.split("\\,");
				data.add(new Node(info[0], info[1], info[2], info[3], info[4], info[5], info[6]));
			}
			Collections.reverse(data);
			data.remove(data.size()-1);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getSize() {
		return this.data.size();
	}
	
	public String getClose(int i) {
		return this.data.get(i).close;
	}
	
	@SuppressWarnings("unused")
	private class Node {
		private String date;
		private String open;
		private String high;
		private String low;
		private String close;
		private String volume;
		private String adj_close;

		private Node(String d, String o, String h, String l, String c, String v, String ac) {
			this.date = d;
			this.open = o;
			this.high = h;
			this.low = l;
			this.close = c;
			this.volume = v;
			this.adj_close = ac;
		}
	}
}
