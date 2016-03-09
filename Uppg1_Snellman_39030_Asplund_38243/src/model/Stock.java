package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class Stock {
	private String ticker;
	private String url;
	private ArrayList<Node> data = new ArrayList<>();
	private ArrayList<Node> clone;
	// String[]
	// 0 = date, 1 = open, 2 = high, 3 = low, 4 = close, 5 = volume, 6 = adj
	// close
	private String start;
	private String end;

	public Stock(String ticker, String start, String end) {
		this.ticker = ticker;
		this.start = start;
		this.end = end;
		makeUrl(start, end);
	}

	private void makeUrl(String sD, String eD) {
		try {
			String dt = sD + "." + eD;
			String[] dates = dt.split("\\.");
			int[] date = new int[6];
			for (int i = 0; i < dates.length; i++) {
				date[i] = Integer.parseInt(dates[i]);
			}
			date[1]--;
			date[4]--;
			for (int x = 0; x < date.length; x++) {
				dates[x] = String.valueOf(date[x]);
			}
			this.start = dates[0] + "." + dates[1] + "." + dates[2];
			this.end = dates[3] + "." + dates[4] + "." + dates[5];
			this.url = "http://ichart.finance.yahoo.com/table.csv?s=" + this.ticker + "&a=" + dates[1] + "&b="
					+ dates[0] + "&c=" + dates[2] + "&d=" + dates[4] + "&e=" + dates[3] + "&f=" + dates[5]
					+ "&g=d&ignore=.csv";
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Felaktigt input, får ej vara tomt, försök igen!", "Felaktigt input",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void fetchData() {
		try {
			URL ul = new URL(this.url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(ul.openStream()));
			String s;
			reader.readLine();
			while ((s = reader.readLine()) != null) {
				String[] tmp = s.split("\\,");
				data.add(new Node(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], tmp[6]));
			}
			Collections.reverse(data);
		} catch (MalformedURLException e) {

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Felaktigt input, försök igen!\nKontrollera" + " att tickers och/eller daturm är korrekt ifyllt!",
					"Felaktigt input", JOptionPane.ERROR_MESSAGE);
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Felaktigt input, får ej vara tomt, försök igen!", "Felaktigt input",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Felaktigt input, försök igen!\nKontrollera" + " att tickers och/eller daturm är korrekt ifyllt!",
					"Felaktigt input", JOptionPane.ERROR_MESSAGE);
		}
		setClone();
	}

	private void setClone() {
		clone = new ArrayList<>(data.size());
		for (Node n : data) {
			clone.add(new Node(n.date, n.open, n.high, n.low, n.close, n.volume, n.adj_close));
		}
	}

	public String getTicker() {
		return this.ticker;
	}

	public int getSize() {
		return this.data.size();
	}

	public String getStart() {
		return this.start;
	}
	
	public String getEnd() {
		return this.end;
	}
	
	public String getDate(int i) {
		return data.get(i).date;
	}

	public Double getClose(int i) {
		return data.get(i).close;
	}
	
	public Double getCloneClose(int i) {
		return clone.get(i).close;
	}
	
	public double getMax() {
		double max = 0;
		
		for (Node n : clone) {
			if (n.close > max) {
				max = n.close;
			}
		}
		return max;
	}
	
	public double getMin() {
		double min = clone.get(0).close;
		
		for (Node n : clone) {
			if (n.close < min) {
				min = n.close;
			}
		}
		return min;
	}
	
	public void setClose(int i, String s) {
		double tmp1 = this.data.get(i).close;
		double tmp2 = Double.parseDouble(s);
		tmp1 *= tmp2;
		this.clone.get(i).close = tmp1;
	}

	private class Node {
		private String date;
		private Double open;
		private Double high;
		private Double low;
		private Double close;
		private Double volume;
		private Double adj_close;

		private Node(String d, String o, String h, String l, String c, String v, String ac) {
			this.date = d;
			this.open = Double.parseDouble(o);
			this.high = Double.parseDouble(h);
			this.low = Double.parseDouble(l);
			this.close = Double.parseDouble(c);
			this.volume = Double.parseDouble(v);
			this.adj_close = Double.parseDouble(ac);
		}
		
		private Node(String d, double o, double h, double l, double c, double v, double ac) {
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
