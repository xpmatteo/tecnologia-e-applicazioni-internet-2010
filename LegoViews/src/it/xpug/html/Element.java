package it.xpug.html;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Element extends HtmlDocument {

	public enum EmptyMode { SINGLE_TAG, TWO_TAGS }
	
	private final String name;
	private List<HtmlDocument> contents = new ArrayList<HtmlDocument>();
	private Map<String, String> attributes = new TreeMap<String, String>();
	private final EmptyMode emptyMode;

	public Element(String name) {
		this.name = name;
		this.emptyMode = EmptyMode.TWO_TAGS;
	}

	public Element(String name, EmptyMode emptyMode) {
		this.name = name;
		this.emptyMode = emptyMode;
	}

	public Element with(String attributeName, String attributeValue) {
		this.attributes .put(attributeName, attributeValue);
		return this;
	}

	public Element add(HtmlDocument content) {
		contents.add(content);
		return this;
	}

	public Element addAll(HtmlDocument[] contents) {
		for (HtmlDocument element : contents) {
			add(element);
		}
		return this;
	}

	@Override
	public void renderOn(Writer writer) throws IOException {
		if (EmptyMode.SINGLE_TAG == emptyMode && contents.isEmpty()) {
			writer.write(uniqueTag());
		} else {			
			writer.write(startTag());
			for (HtmlDocument node : contents) {
				node.renderOn(writer);
			}
			writer.write(endTag());
		}
	}

	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			renderOn(writer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}
	
	@Override
	public String contentsAsText() {
		String result = "";
		for (HtmlDocument content : contents) {
			result += content.contentsAsText();
		}
		return result;
	}

	public String getAttribute(String attributeName) {
		return attributes.get(attributeName);
	}

	public String getName() {
		return name;
	}

	public List<HtmlDocument> getContents() {
		return contents;
	}

	public List<Element> getElements() {
		List<Element> result = new ArrayList();
		for (HtmlDocument doc : contents) {
			if (doc instanceof Element) {
				result.add((Element) doc);
			}
		}
		return result;
	}

	public Element findElementById(String identifier) throws ElementNotFoundException {
		if (attributes.containsKey("id") && attributes.get("id").equals(identifier)) {
			return this;
		} else {
			for (HtmlDocument content : contents) {
				try {
					if (content instanceof Element) {
						return ((Element) content).findElementById(identifier);
					}
				} catch (Exception ignored) {}
			}
			throw new ElementNotFoundException(String.format("element with id '%s'", identifier));
		}
	}

	@Override
	public Element findLinkByLabel(String string) throws ElementNotFoundException {
		if (name.equals("a") && contentsAsText().equals(string)) {
			return this;
		} else {
			for (HtmlDocument content : contents) {
				try {
					return content.findLinkByLabel(string);
				} catch (Exception ignored) {}
			}
			throw new ElementNotFoundException("link with label " + string);
		}
	}

	public boolean matchesXPath(String xpath) {
		return numberOfXPathMatches(xpath) > 0;
	}

	public Element findByXPath(String xpath) throws ElementNotFoundException {
		Node node = toXmlDocument().getNode(xpath);
		return fromXmlNode(node);
	}

	public int numberOfXPathMatches(String xpath) {
		return toXmlDocument().numberOfMatches(xpath);
	}

	public String textContentByXPath(String xpath) throws ElementNotFoundException {
		return toXmlDocument().nodeText(xpath);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Element))
			return false;
		Element other = (Element) obj;
		if (!attributes.equals(other.attributes))
			return false;
		if (!contents.equals(other.contents))
			return false;
		if (!name.equals(other.name))
			return false;
		return true;
	}
	
    private XmlDocument toXmlDocument() {
		return new XmlDocument(toString());
	}

	private static Element fromXmlNode(Node node) {
		Element result = new Element(node.getLocalName());
		result.add(new TextNode(node.getTextContent()));
		NamedNodeMap nodeAttributes = node.getAttributes();
		for (int i=0; i<nodeAttributes.getLength(); i++) {
			Node item = nodeAttributes.item(i);
			String attributeName = item.getLocalName();
			String attributeValue = item.getNodeValue();
			result.with(attributeName, attributeValue);
		}
		return result;
	}

	private String startTag() {
		return makeTag("\n<%s%s>");
	}

	private String endTag() {
		return String.format("</%s>\n", name);
	}

	private String uniqueTag() {
		return makeTag("\n<%s%s />");
	}
	
	private String makeTag(String format) {
		String attrs = "";
		for (String name : attributes.keySet()) {
			String value = attributes.get(name);
			attrs += String.format(" %s='%s'", name, value);
		}
		return String.format(format, name, attrs);
	}
}
