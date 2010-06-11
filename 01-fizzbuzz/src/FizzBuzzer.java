import java.util.List;


public class FizzBuzzer {


	private final List<Rule> rules;

	public FizzBuzzer(List<Rule> rules) {
		this.rules = rules;
	}

	public String say(Integer n) {
		StringBuilder result = new StringBuilder();
		for (Rule rule : rules) {
			rule.say(n, result);
		}		
		return result.toString();
	}

}
