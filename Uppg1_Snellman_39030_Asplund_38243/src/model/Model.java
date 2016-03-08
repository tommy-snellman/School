package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Model {
	
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
				System.out.println(s);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
