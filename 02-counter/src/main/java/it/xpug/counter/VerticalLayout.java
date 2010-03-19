package it.xpug.counter;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class VerticalLayout implements HtmlComponent {

	private List<HtmlComponent[]> rows = new ArrayList<HtmlComponent[]>();

	public void renderOn(Writer writer) throws IOException {
		writer.write("<table>");
		for (HtmlComponent[] row : rows) {
			writer.write("<tr align='center'>");
			writer.write("<td>");
			for (HtmlComponent htmlComponent : row) {
				htmlComponent.renderOn(writer);
				writer.write(" ");
			}
			writer.write("</td>");
			writer.write("</tr>");
		}
		writer.write("</table>");
	}

	public void add(HtmlComponent ... components) {
		this.rows.add(components);
	}

}
