import java.io.PrintStream;


public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser(new Calculator(), System.out);
		parser.calculate(args[0]);
	}
}
