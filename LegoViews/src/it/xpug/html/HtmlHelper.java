package it.xpug.html;

import java.util.List;

import it.xpug.html.Element.EmptyMode;

public class HtmlHelper {

	public static Element paragraph() {
		return new Element("p");
	}
	
	public static Element paragraph(String string) {
		return elementWithText("p", string);
	}
	
	public static Element paragraph(HtmlDocument ... contents) {
		return elementWithContents("p", contents);
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

	public static Element link(String href, String label) {
		return elementWithText("a", label).with("href", href);
	}
	
	public static Element link(String context, String href, String label) {
		return elementWithText("a", label).with("href", url(context, href));
	}

	public static Element head(HtmlDocument ... elements) {
		return new Element("head").addAll(elements);
	}
	
	public static Element stylesheetLink(String href) {
		return new Element("link", Element.EmptyMode.SINGLE_TAG)
			.with("href", href)
			.with("rel", "stylesheet")
			.with("type", "text/css");
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

	public static Element table(List<HtmlDocument> rows) {
		return elementWithContents("table", rows);
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
		return heading(text, 1);
	}

	public static Element h2(String text) {
		return heading(text, 2);
	}

	public static Element h3(String text) {
		return heading(text, 3);
	}

	private static Element heading(String text, int level) {
		return elementWithText("h" + level, text);
	}

	public static Element tableHeader(Element ... elements) {
		return elementWithContents("th", elements);
	}
	
	public static Element form(String action, String method, HtmlDocument ... contents) {
		return new Element("form").with("action", action).with("method", method).addAll(contents);
	}

	public static Element submitButton(String label) {
		return new Element("input", EmptyMode.SINGLE_TAG).with("type", "submit").with("value", label);
	}

	public static Element textField(String name) {
		return new Element("input", EmptyMode.SINGLE_TAG).with("type", "text").with("name", name);
	}
	
	public static Element textField(String name, String value) {
		return new Element("input", EmptyMode.SINGLE_TAG).with("type", "text").with("name", name).with("value", value);
	}
	
	public static Element hiddenField(String name, String value) {
		return new Element("input", EmptyMode.SINGLE_TAG).with("type", "hidden").with("name", name).with("value", value);
	}
	
	public static TextNode text(String text) {
		return new TextNode(text);
	}
	
	public static Element newLine() {
		return new Element("br", EmptyMode.SINGLE_TAG);
	}
	
	private static String url(String context, String href) {
		return context + "/" + href;
	}

	private static Element elementWithContents(String name, HtmlDocument ... contents) {
		return new Element(name).addAll(contents) ;
	}

	private static Element elementWithContents(String name, List<HtmlDocument> contents) {
		return new Element(name).addAll(contents.toArray(new HtmlDocument[]{})) ;
	}

	private static Element elementWithText(String name, String text) {
		return new Element(name).add(new TextNode(text));
	}
}
