package d0;

import java.awt.Font;

import edu.princeton.cs.algs4.StdDraw;

public class d0 {

	public static void main(String[] args) {
		
		StdDraw.clear(StdDraw.BLACK);
		StdDraw.setXscale(-1.0, +1.0);
		StdDraw.setYscale(-1.0, +1.0);
		StdDraw.setPenColor(StdDraw.WHITE);
		
		double x = 0;
		
		while(x < 2) {
			StdDraw.rectangle(-0.5, -0.3, x , x);
			x = x + 0.05;
		}
		
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.line(-0.9, -1, 0.9, 1);
		Font font = new Font("Arial", Font.BOLD , 30);
		
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setFont(font);
		StdDraw.text(0.6, -0.8, "This is a text");
		
		StdDraw.setPenColor(StdDraw.PINK);
		StdDraw.filledEllipse(-0.7, 0.8, 0.2, 0.1);

	}
}
