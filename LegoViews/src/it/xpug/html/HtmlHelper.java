package it.xpug.html;

public class HtmlHelper {

	public static Element paragraph() {
		return new Element("p");
	}
	
	public static Element paragraph(String string) {
		return elementWithText("p", string);
	}
	
	public static Element div(HtmlDocument ... contents) {
		return elementWithContents("div", contents);
	}
	
	public static Element title(String title) {
		return elementWithText("title", title);
	}

	public static Element style(String rules) {
		return elementWithText("style", rules).with("type", "text/css");
	}

	public static HtmlDocument link(String href, String label) {
		return elementWithText("a", label).with("href", href);
	}
	
	public static Element head(HtmlDocument ... elements) {
		return new Element("head").addAll(elements);
	}

	public static Element body(HtmlDocument ... elements) {
		return elementWithContents("body", elements);
	}

	public static Element image(String source) {
		return new Element("img").with("src", source).with("alt", "");
	}

	public static Element html(Element ... elements) {
		return new Element("html").addAll(elements) ;
	}
	
	public static Element table(Element ... elements) {
		return elementWithContents("table", elements);
	}

	public static Element row(Element ... elements) {
		return elementWithContents("tr", elements);
	}

	public static Element cell(Element ... elements) {
		return elementWithContents("td", elements);
	}

	public static Element cell(String text) {
		return elementWithText("td", text);
	}

	public static Element h1(String text) {
		return elementWithText("h1", text);
	}

	public static Element tableHeader(Element ... elements) {
		return elementWithContents("th", elements);
	}

	private static Element elementWithContents(String name, HtmlDocument ... contents) {
		return new Element(name).addAll(contents) ;
	}

	private static Element elementWithText(String name, String text) {
		return new Element(name).add(new TextNode(text));
	}
}
