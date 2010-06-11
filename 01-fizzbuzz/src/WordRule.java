
public class WordRule implements Rule {

	private int divisor;
	private String answer;

	public WordRule(Integer divisor, String answer) {		
		this.divisor = divisor;
		this.answer = answer;
	}

	public void say(Integer n, StringBuilder result) {
		if (n % divisor == 0) {
			result.append(answer);
		}		
	}

}
