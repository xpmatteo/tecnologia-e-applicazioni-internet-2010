package it.xpug.counter;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import static org.junit.Assert.*;


public class CounterTest {

	private Writer writer = new StringWriter();

	@Test
	public void willDisplayANumber() throws Exception {
		CounterDisplay display = new CounterDisplay(3);
		assertRenders(display, "<h1>3</h1>");
	}

	@Test
	public void willProduceALink() throws Exception {
		CounterLink link = new CounterLink(7, "pippo");
		assertRenders(link, "<a href='?value=7'>pippo</a>");
	}
	
	@Test
	public void willMakeAVerticalLayout() throws Exception {
		VerticalLayout layout = new VerticalLayout();
		layout.add(text("A"));
		layout.add(text("B"), text("C"));
		assertRenders(layout, "" +
				"<table>" +
				
				"<tr align='center'>" +
				"<td>A </td>" +
				"</tr>" +

				"<tr align='center'>" +
				"<td>B C </td>" +
				"</tr>" +

				"</table>");
	}
	
	@Test
	public void willInitializeCounterFromString() throws Exception {
		Counter counter = new Counter("12");
		counter.renderOn(writer);
		assertDisplayShows("12");
		assertLinkPointsToValue("dec", "11");
		assertLinkPointsToValue("inc", "13");
	}
	
	@Test
	public void defaultValueIsZero() throws Exception {
		Counter counter = new Counter(null);
		counter.renderOn(writer);
		assertDisplayShows("0");
	}

	private void assertLinkPointsToValue(String linkLabel, String value) {
		String message = String.format("No link found with label %s that points to value %s", linkLabel, value);
		String regex = String.format(".*href='\\?value=%s'>%s<.*", value, linkLabel);
		assertTrue(message, writer.toString().matches(regex));
	}

	private void assertDisplayShows(String string) {
		String message = "display does not show " + string;
		assertTrue(message, writer.toString().matches(".*h1>" + string + "</h1.*"));	
	}
	
	private HtmlComponent text(final String string) {
		return new HtmlComponent() {
			public void renderOn(Writer writer) throws IOException {
				writer.write(string);
			}
		};
	}

	private void assertRenders(HtmlComponent display, String expected) throws IOException {
		display.renderOn(writer);
		assertEquals(expected, writer.toString());
	}
}
