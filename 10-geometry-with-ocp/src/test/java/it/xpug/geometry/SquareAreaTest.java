package it.xpug.geometry;
import java.util.HashMap;
import java.util.Map;

import it.xpug.geometry.SquareAreaPage;
import it.xpug.html.Element;

import org.junit.Test;

import static org.junit.Assert.*;



public class SquareAreaTest {
	SquareAreaCalculator calculator = new SquareAreaCalculator();
	Map<String, String[]> params = new HashMap<String, String[]>();

	@Test
	public void producesForm() throws Exception {
		 PageComponent component = new SquareAreaPage(calculator, params);
		 
		 Element html = component.toHtml();
		 html.findByXPath("//form[@method='get']");
		 html.findByXPath("//form[@method='get']//input[@type='text'][@name='side'][@value='']");
		 html.findByXPath("//form[@method='get']//input[@type='submit']");
	}
	
	@Test
	public void calculateSquareArea() throws Exception {
		double area = calculator.calculate(4.0);
		
		assertEquals(16.0, area, Math.pow(10, -6));
	}


	@Test
	public void computesArea() throws Exception {
		params.put("side", new String[] { "3.0" });
		
		PageComponent component = new SquareAreaPage(calculator, params); 
		Element html = component.toHtml();

		html.findByXPath("//p[text()='Area: lato x lato = 9.0']");
	}

}
