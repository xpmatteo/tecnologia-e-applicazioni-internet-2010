import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



public class FizzBuzzTest {

	private FizzBuzzer buzzer;

	@Before
	public void setUp() throws Exception {
		buzzer = new FizzBuzzer(
						new WordRule(3, "Fizz", 
								new WordRule(5, "Buzz",
										new WordRule(7, "Bang", 
											new DefaultRule()))));		
	}
	
	@Test
	public void sayTheNumber() throws Exception {
		assertEquals("1", buzzer.say(1));
		assertEquals("2", buzzer.say(2));
	}
	
	@Test
	public void sayFizzWhenMultipleOfThree() throws Exception {
		assertEquals("Fizz", buzzer.say(3));
		assertEquals("Fizz", buzzer.say(9));
	}
	
	@Test
	public void sayBuzzWhenMultipleOfFive() throws Exception {
		assertEquals("Buzz", buzzer.say(5));
	}

	@Test
	public void sayFizzBuzzWhenMultipleOfFiveAndThree() throws Exception {
		assertEquals("FizzBuzz", buzzer.say(15));
	}

	@Test
	public void sayBangWhenMultipleOfSeven() throws Exception {
		assertEquals("Bang", buzzer.say(7));
	}

	@Test
	public void sayFizzBangWhenMultipleOfSeven() throws Exception {
		assertEquals("FizzBang", buzzer.say(7*3));
		assertEquals("BuzzBang", buzzer.say(7*5));
		assertEquals("FizzBuzzBang", buzzer.say(7*5*3));
	}

}
