package it.xpug.counter;

import java.io.IOException;
import java.io.Writer;

public interface HtmlComponent {
	void renderOn(Writer writer) throws IOException;
}
