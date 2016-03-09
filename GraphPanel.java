package view;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Stock;

class GraphPanel extends JPanel {
	
	Stock stock1, stock2;
	
	public void paintComponent( Graphics g ) {
		super.paintComponent(g);
		
		int width = this.getWidth();
		System.out.println("Hej");
		if (stock1 != null && stock2 != null){
			System.out.println("Hej igen");
			double s1Min = stock1.getMin();
			double s1Max = stock1.getMax();
			double s2Min = stock2.getMin();
			double s2Max = stock2.getMax();
			
			g.setColor(Color.red);
			int size = stock1.getSize();
			System.out.println(map(0, 0, size, 0, width));
			for (int i = 0; i < size - 1; i++){
				g.drawLine(map(i, 0, size, 0, width), (int) norm(s1Min, s1Max,stock1.getCloneClose(i)), map(i+1, 0, size, 0, width), (int) norm(s1Min, s1Max,  stock1.getCloneClose(i + 1)));
			}
			
		}
	}
	
	public double norm( double Min, double Max, double value) {
		return (value - Min) / (Max - Min);
	}
	
	public double lerp(double norm, int min, int max){
		return (max - min) * norm + min;
	}
	
	public int map(int value, int sourceMin, int sourceMax, int destMin, int destMax){
		return (int)lerp(norm(value, sourceMin, sourceMax), destMin, destMax);
	}
	
}
