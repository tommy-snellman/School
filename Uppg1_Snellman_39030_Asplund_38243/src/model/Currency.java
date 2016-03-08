package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Currency {
	private String currency;
	private String start;
	private String end;
	private ArrayList<Node> data = new ArrayList<>();
	
	public Currency(String curr, String start, String end) {
		this.currency = curr;
		this.start = start;
		this.end = end;
		
		fetchCurrencyData(this.currency, this.start, this.end);
	}
	
	public void fetchCurrencyData(String curr, String dateF, String dateT) {
		String date = dateF + "." + dateT;
		String[] tmp = date.split("\\.");
		int[] i_date = new int[6];
		for (int i = 0; i < tmp.length; i++) {
			i_date[i] = Integer.parseInt(tmp[i]);
		}
		i_date[1]--;
		i_date[4]--;
		for (int x = 0; x < i_date.length; x++) {
			tmp[x] = String.valueOf(i_date[x]);
		}
		try {
			URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s="+curr+"=X&a="+tmp[1]+"&b="+tmp[0]+"&c="+tmp[2]+"&d="+tmp[4]+"&e="+tmp[3]+"&f="+tmp[5]+"&g=d&ignore=.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String s;
			while ((s = reader.readLine()) != null) {
				String[] info = s.split("\\,");
				data.add(new Node(info[0], info[1], info[2], info[3], info[4], info[5], info[5]));
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
