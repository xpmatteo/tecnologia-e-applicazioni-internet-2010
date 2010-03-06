import java.util.List;


public class Concatenator {

	public void concatenate(List<String> result, List<String> a, List<String> b) {
		int length = Math.max(a.size(), b.size());
		for (int i=0; i<length; i++) {
			String line = getElement(a, i) + getElement(b, i);
			result.add(line);
		}
	}

	private String getElement(List<String> a, int index) {
		if (index >= a.size()) {
			return "";
		}
		return a.get(index);
	}

}
