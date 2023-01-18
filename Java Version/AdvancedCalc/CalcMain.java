package AdvancedCalc;

import java.io.IOException;

public class CalcMain {

	public static void main(String[] args) throws IOException {
		
		Calculator cal = new Calculator("54*67+(560*40^4)/(60-22*23^5)*23^5/457^2/456");
		
		
		cal.calculation();
		
		
	}
	
	
}
