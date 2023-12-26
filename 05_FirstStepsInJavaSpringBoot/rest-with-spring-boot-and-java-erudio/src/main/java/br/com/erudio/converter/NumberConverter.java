package br.com.erudio.converter;

public class NumberConverter {

	public static boolean isZero(String strNumber) {
		if(convertDouble(strNumber) == 0D) return true;
		return false;
	}
	public static Double convertDouble(String strNumber) {
		if(strNumber == null) return 0D;
		String number = strNumber.replaceAll(",", ".");
		if(isnumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	public static boolean isnumeric(String strNumber) {
		if(strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

}
