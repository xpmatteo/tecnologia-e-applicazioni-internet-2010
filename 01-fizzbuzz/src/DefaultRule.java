
public class DefaultRule implements Rule {

	public void say(Integer n, StringBuilder result) {
		if (result.toString().length() == 0) { 
			result.append(n.toString());
		}
	}

}
