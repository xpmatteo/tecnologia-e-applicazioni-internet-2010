import java.io.OutputStream;
import java.io.PrintWriter;


public class Parser {

	private final Calculator calculator;
	private final OutputStream stream;

	public Parser(Calculator calculator, OutputStream stream) {
		this.calculator = calculator;
		this.stream = stream;
	}

	public void calculate(String string) {
		String[] tokens = string.split(" ");
		int a = Integer.parseInt(tokens[0]);
		int b = Integer.parseInt(tokens[2]);
		println(calculator.add(a, b));
	}

	private void println(Double result) {
		PrintWriter writer = new PrintWriter(stream);
		writer.println(result.toString());
		writer.close();
	}

}
