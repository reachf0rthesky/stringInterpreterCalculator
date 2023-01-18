package AdvancedCalc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Calculator {

	private String calcString="";
	
	
	
	
	public Calculator(){
		
	}
	
	public Calculator(String calcString){
		
		this.calcString=calcString;
		
	}
	
	

	public void calculation() throws IOException {
		
		//boolean gogo=true;
		
		// Rechenoperation wird in eine ArrayList gesplittet
		
		ArrayList<String> calcList = new ArrayList<String>(Arrays.asList(calcString.split("")));
		
		
		//Check if theres still stuff to do
		
		// Nummern werden zusammengefasst z.B. von "6"5"4" zu 654
		calcList= NrAdding(calcList);
		
		System.out.println("Ausgabe: ");
		System.out.print("Rechnung: ");
		System.out.print("'");
		
		//Base Calc Ausgabe als Test
		
		for (String string : calcList) {
		
			System.out.print(string+"'");
			
		}
		
		// Rechnung
		
		System.out.println();
		
		System.out.printf("Operation beendet. Lösung: %.8f",SuperCalc(calcList));
				
		
	}
	
	
	public double SuperCalc(ArrayList<String> calcList) {
		
		double erg=0;
		
		// Start der Rechnung
		

		
		
	
		while(true) {
	
		//Rechnung
			
		//Klammern
			
		while(true) {
			
			if(calcList.contains("(")) {
				
				KlammernCalc(calcList);
				
			}
			else {
				
				break;
				
			}
			
		}
		
		//Punkt Calc vor Strich 
		
		if(calcList.contains("*")||calcList.contains("/")||calcList.contains("^")) {
			
			System.out.print("Zwischenprodukt: ");
			
			System.out.print("'");
			
			for (String string : calcList) {
				
				System.out.printf("%s'",string);
				
			}
			
			System.out.println();
			
			calcList=PunktCalcs(calcList);
			
			
			//System.out.println("im outtt");
			
		}
	
		//Strich Calc
		
		else if (calcList.contains("+")||calcList.contains("-")) {
			
			System.out.print("Zwischenprodukt: ");
			
			System.out.print("'");
			
			for (String string : calcList) {
				
				System.out.printf("%s'",string);
				
			}
			
			System.out.println();
			
			calcList=StrichCalcs(calcList);
			
			
		}
		
		// Falls alles gerechnet wurde wird das Ergebnis ausgegeben
		
		if(calcList.size()==1) {
			
			erg=Double.parseDouble(calcList.get(0));
			//System.out.printf("Operation beendet. Lösung: %.8f",erg);
			break;
			
		}
		}
		
		
		return erg;
		
		
	}
	
	public ArrayList<String> KlammernCalc(ArrayList<String> calcList) {
		
		int i;
		int size=calcList.size();
		double erg=0;
		int start=0;
		int end=99;
		
		ArrayList<String> tempList = new ArrayList<String>();
		
		for(i=1;i<size;i++) {
			
			//Anfang und Ende der Klammern finden und speichern
			
			
			
			
			if(calcList.get(i).equals("(")) {
				
				start=i;
				
			}
			
			if(calcList.get(i).equals(")")) {
				
				end=i;
				tempList.add(calcList.get(i));
				calcList.set(i, " ");
				break;
				
			}
			
			if(start!=0 && i<end) {
				
				tempList.add(calcList.get(i));
				calcList.set(i, " ");
				
			}
	
			

		}
		
		// Testausgabe
		
		for (String string : tempList) {
			
			System.out.print(string + " ");
			
		}
		
		tempList.remove("(");
		tempList.remove(")");
		
		
		
		//Alte Werte der Klammer in der CalcList löschen
		
		while(calcList.contains(" ")) {
			
			calcList.remove(" ");
			
		}

		
		erg=SuperCalc(tempList);
		
		System.out.printf("Klammer Ergebniss: %.4f\n",erg);
		
		
		
		calcList.add(start, String.valueOf(erg));
		
		
		
		
		return calcList;
		
	}
	
	
	public ArrayList<String> PunktCalcs(ArrayList<String> calcList) {
		
		int i;
		double erg=0;
		
		//System.out.println("test calc");
		
		int size=calcList.size();
		
		for(i=1;i<size;i++) {
			
			if(calcList.get(i).equals("^")) {

				erg=Double.parseDouble(calcList.get(i-1));
				
				for(int m=1;m<Double.parseDouble(calcList.get(i+1));m++){
					
					erg*=Double.parseDouble(calcList.get(i-1));
					
				}
				
				calcList.set(i, String.valueOf(erg));
				calcList.remove(i-1);
				calcList.remove(i);
				i=0;
				
			}
			else if (!calcList.contains("^")) {
				
				switch (calcList.get(i)) {

				case "/":
					
					erg=Double.parseDouble(calcList.get(i-1))/Double.parseDouble(calcList.get(i+1));
					
					calcList.set(i, String.valueOf(erg));
					calcList.remove(i-1);
					calcList.remove(i);
					i=0;
					
					break;
				case "*":
					
					//System.out.println("test2");
					
					erg=Double.parseDouble(calcList.get(i-1))*Double.parseDouble(calcList.get(i+1));
					
					calcList.set(i, String.valueOf(erg));
					calcList.remove(i-1);
					calcList.remove(i);
					i=0;
					
					break;
					
				default:
					//System.out.println("breako");
					break;
					
				}
				
				
			}
			
			
			
			if(i==calcList.size()-1) {
				
				//System.out.println("testtttt");
				break;
				
			}
			
			
			
			//System.out.println("punkt calc");
			
			
			
			
		}
		
		return calcList;
		
	
	}
	
	///Strichrechnung
	
	public ArrayList<String> StrichCalcs(ArrayList<String> calcList) {
		
		int i;
		
		double erg=0;
		
		int size=calcList.size();
		
		for(i=1;i<size;i++) {
			
			switch (calcList.get(i)) {
			case "+":
				
				erg=Double.parseDouble(calcList.get(i-1))+Double.parseDouble(calcList.get(i+1));
				
				calcList.set(i, String.valueOf(erg));
				calcList.remove(i-1);
				calcList.remove(i);
				i=0;
				
				break;
			case "-":
				
				erg=Double.parseDouble(calcList.get(i-1))-Double.parseDouble(calcList.get(i+1));
				
				calcList.set(i, String.valueOf(erg));
				calcList.remove(i-1);
				calcList.remove(i);
				i=0;
				
				break;
								
			default:
				break;
			}
			
			
			if(i==calcList.size()-1) {
				
				//System.out.println("testtttt 2");
				break;
				
			}
			
		}
		
		
//		System.out.println("dunnnnn");
//		
//		
//		for (String string : calcList) {
//			
//			System.out.println(string);
//			
//		}
		
		
		return calcList;
	
	}
	
	
	// Calc to the power of stellen
	
	public double Pow(double number, int stellen) {
		
		double erg=number;
		
		for(int i=1;i<stellen;i++) {
			
			erg*=10;
			
		}
		
		//System.out.println("pow erg: "+erg);
		
		return erg;
		
	}
	
	//Zusammenfassung der Zahlen
	
	public ArrayList<String> NrAdding(ArrayList<String> erg) {
		
		
		int t=erg.size();
		
	
		
		for(int i=0;i<t;i++) {
			
			//Zahlen LÃ¤nger als eine Stelle zusammenfassen
			double newNumber = 0;
			
			
			
			if(i<erg.size()&&erg.get(i).matches(".*\\d.*")) {
				
				
				if(Double.parseDouble(erg.get(i))>9) {
					
					break;
					
				}
				
			
				
				int b=1;
				
				
				
				
				while(true) {
					
					
					
					
					if(i+b<erg.size()&&erg.get(i+b).matches(".*\\d.*")) {
						

						b++;
						
						
					}
					else {
						
						//System.out.println("raus hier");
						break;
						
					}
					
					
					
				}
				
				//Nummer wird zusammengefasst
				
				int b2=b;
				
				for(int n=0;n<b2;n++) {

					newNumber+=Pow(Double.parseDouble(erg.get(i+n)), b);
					
					//System.out.println("b"+ b);
					
					erg.set(i+n,"x");
					
					b--;
					
					
					
				}
				
				
				erg.add(i, String.valueOf(newNumber));
				
				//System.out.println("nn: "+newNumber+"\n -------------------------");
				
				//Placeholder rausschmeiÃŸen
				erg.remove("x");
				erg.remove("x");
				erg.remove("x");
				erg.remove("x");
				erg.remove("x");
				
				
				
				
				
				newNumber=0;
				
				
			}
				
		
		}
		
		
		
		
		
		
		return erg;
		
	}
	
	
	
	
	
	
	
	
}
