package br.com.erudio.controler;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.converter.NumberConverter;
import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.math.SimpleMath;

@RestController
public class MathController {
	
	private final AtomicLong counter = new AtomicLong();
	
	private SimpleMath simpleMath = new SimpleMath();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return simpleMath.sum(NumberConverter.convertDouble(numberOne) , NumberConverter.convertDouble(numberTwo));
	}
	
	@RequestMapping(value = "/subtract/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double subtract(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return simpleMath.subtract(NumberConverter.convertDouble(numberOne) , NumberConverter.convertDouble(numberTwo));
	}
	
	@RequestMapping(value = "/multiply/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double multiply(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return simpleMath.multiply(NumberConverter.convertDouble(numberOne) , NumberConverter.convertDouble(numberTwo));
	}

	@RequestMapping(value = "/divide/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double divide(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		if(NumberConverter.isZero(numberTwo)) {
			throw new UnsupportedMathOperationException("Divisão por zero não permitida!");
		}
		
		return simpleMath.divide(NumberConverter.convertDouble(numberOne) , NumberConverter.convertDouble(numberTwo));
	}
	
	@RequestMapping(value = "/sqrt/{numberOne}",
			method = RequestMethod.GET)
	public Double sqrt(
			@PathVariable(value = "numberOne") String numberOne
			) throws Exception {
		
		if(!NumberConverter.isnumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return simpleMath.sqrt(NumberConverter.convertDouble(numberOne));
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double mean(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return simpleMath.mean(NumberConverter.convertDouble(numberOne) , NumberConverter.convertDouble(numberTwo));
	}

	
	

	
}
