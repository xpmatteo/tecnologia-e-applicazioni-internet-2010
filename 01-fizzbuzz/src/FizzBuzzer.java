
public class FizzBuzzer {

	private final Rule rule;

	public FizzBuzzer(Rule rule) {
		this.rule = rule;
	}

	public String say(Integer n) {
		StringBuilder result = new StringBuilder();
		rule.say(n, result);
		return result.toString();
	}

}
