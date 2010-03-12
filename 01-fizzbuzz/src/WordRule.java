
public class WordRule implements Rule {

	private final Rule rule;
	private int divisor;
	private String answer;

	public WordRule(Integer divisor, String answer, Rule rule) {		
		this.divisor = divisor;
		this.answer = answer;
		this.rule = rule;
	}

	public void say(Integer n, StringBuilder result) {
		if (n % divisor == 0) {
			result.append(answer);
		}		
		rule.say(n, result);
	}

}
