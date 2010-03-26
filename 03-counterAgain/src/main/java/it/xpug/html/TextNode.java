package it.xpug.html;

import java.io.IOException;
import java.io.Writer;

public class TextNode extends HtmlDocument {

	private final String content;

	public TextNode(String content) {
		this.content = content;
	}

	@Override
	public void renderOn(Writer writer) {
		try {
			writer.write(content);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "TextNode [" + content + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TextNode))
			return false;
		TextNode other = (TextNode) obj;
		if (!content.equals(other.content))
			return false;
		return true;
	}

	@Override
	public String contentsAsText() {
		return content;
	}

	@Override
	public Element findLinkByLabel(String string) throws ElementNotFoundException {
		throw new ElementNotFoundException("link with label " + string);
	}
}
