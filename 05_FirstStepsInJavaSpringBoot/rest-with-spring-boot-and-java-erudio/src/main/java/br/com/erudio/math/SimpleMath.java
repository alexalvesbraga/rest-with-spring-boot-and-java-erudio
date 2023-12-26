package br.com.erudio.math;


public class SimpleMath {
	
	
	public Double sum(Double numberOne, Double numberTwo) {	
		return (numberOne) + (numberTwo);
	}
	
	
	public Double subtract(Double numberOne, Double numberTwo) {			
		return numberOne - numberTwo;
	}
	
	
	public Double multiply(Double numberOne, Double numberTwo) {		
		return numberOne * numberTwo;
	}


	public Double divide(Double numberOne, Double numberTwo) {		
		return numberOne / numberTwo;
	}
	
	
	public Double sqrt(Double numberOne) {			
		return Math.sqrt(numberOne);
	}
	

	public Double mean(Double numberOne, Double numberTwo) {		
		return (numberOne + numberTwo) /2;
	}
}
