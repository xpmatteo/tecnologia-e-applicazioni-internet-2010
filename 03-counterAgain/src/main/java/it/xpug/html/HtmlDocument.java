package it.xpug.html;

import java.io.IOException;
import java.io.Writer;


public abstract class HtmlDocument {

	public abstract void renderOn(Writer writer) throws IOException;

	public abstract String contentsAsText();

	public abstract Element findLinkByLabel(String string) throws ElementNotFoundException;

}
