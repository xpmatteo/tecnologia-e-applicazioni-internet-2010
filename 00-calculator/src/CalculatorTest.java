import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.Test;

import static org.junit.Assert.*;



public class CalculatorTest {

	private static final double EPSILON = 1e-6;

	@Test
	public void twoAndThreeIsFive() throws Exception {
		Calculator calculator = new Calculator();
		double result = calculator.add(2, 3);
		assertEquals(5.0, result, EPSILON);
	}
	
	@Test
	public void willParseTwoAndFive() throws Exception {
		Calculator calculator = new Calculator();
		OutputStream stream = new ByteArrayOutputStream();
		new Parser(calculator, stream).calculate("2 + 5");
		assertEquals("7.0\n", stream.toString());
	}
}
