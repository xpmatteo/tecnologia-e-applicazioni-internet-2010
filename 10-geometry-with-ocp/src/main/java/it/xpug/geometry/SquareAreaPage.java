package it.xpug.geometry;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

public class SquareAreaPage implements PageComponent {

	private final SquareAreaCalculator areaCalculator;
	private Double side;

	public SquareAreaPage(SquareAreaCalculator calculator, Map<String, String[]> params) {
		this.areaCalculator = calculator;
		this.side = getSide(params);
	}

	public String pageTitle() {
		return "Area of a square";
	}

	public Element toHtml() {
		Element form = 
			form("", "get",
				paragraph(
					text("Side:"),
					newLine(),
					textField("side"), 
					submitButton("Calculate")));
		
		double answer = areaCalculator.calculate(side);
		return div(form, paragraph(String.format("Area: side x side = %s", answer)));
	}

	public boolean wantsToHandle(HttpServletRequest request) {
		throw new RuntimeException("Not yet implemented!");
	}

	private Double getSide(Map<String, String[]> params) {
		if (params.containsKey("side")) {
			return Double.parseDouble(params.get("side")[0]);
		}
		return 0.0;
	}
}
