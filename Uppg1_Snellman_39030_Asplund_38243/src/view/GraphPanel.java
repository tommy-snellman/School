package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Stock;

@SuppressWarnings("serial")
class GraphPanel extends JPanel {
	
	public Stock stock1, stock2;
	
	public void paintComponent( Graphics g ) {
		super.paintComponent(g);
		
		int width = this.getWidth();
		int height = this.getHeight();
		if (stock1 != null && stock2 != null){
			double s1Min = stock1.getMin();
			double s1Max = stock1.getMax();
			double s2Min = stock2.getMin();
			double s2Max = stock2.getMax();
			double min, max;
			if (s1Min < s2Min) min = s1Min;
			else min = s2Min;
			
			if (s1Max > s2Max) max = s1Max;
			else max = s2Max;
			g.setFont(new Font("Test", Font.BOLD, 15));
			g.setColor(Color.red);
			int size = stock1.getSize() - 1;
			for (int i = 0; i < size; i++){
				g.drawLine(map(i, 0, size, 0, width), map(stock1.getCloneClose(i), max, min, 0, height), map(i+1, 0, size, 0, width), map(stock1.getCloneClose(i + 1), max, min, 0, height));
			}
			g.drawString(String.format(stock1.getTicker() + " max close: %.2f", s1Max), 2, 12);
			g.drawString(String.format(stock1.getTicker() + " min close: %.2f", s1Min), 2, height - 12);
			g.setColor(Color.green);
			
			for (int i = 0; i < size; i++){
				g.drawLine(map(i, 0, size, 0, width), map(stock2.getCloneClose(i), max, min, 0, height), map(i+1, 0, size, 0, width), map(stock2.getCloneClose(i + 1), max, min, 0, height));
			}
			
			g.drawString(String.format(stock2.getTicker() + " max close: %.2f", s2Max), 2, 28);
			g.drawString(String.format(stock2.getTicker() + " min close: %.2f", s2Min), 2, height - 28);
		}
	}
	
	public double norm(double value, double min, double max) {
		return (value - min) / (max - min);
	}
	
	public double lerp(double norm, int min, int max){
		return (max - min) * norm + min;
	}
	
	public int map(double value, double sourceMin, double sourceMax, int destMin, int destMax){
		return (int)lerp(norm(value, sourceMin, sourceMax), destMin, destMax);
	}
	
}