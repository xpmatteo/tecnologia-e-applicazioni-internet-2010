package it.xpug.courses.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.*;
import static java.lang.String.format;

public class FakeServletResponse extends EmptyServletResponse {

	private String expectedRedirect;
	private Writer writer = new StringWriter();
	private PrintWriter printWriter = new PrintWriter(writer);

	public void expectRedirect(String expectedLocation) {
		this.expectedRedirect = expectedLocation;
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		if (null == expectedRedirect) {
			fail(format("Unexpected call to sendRedirect with %s (none were expected)", location));
		}
		if (!expectedRedirect.equals(location)) {
			fail(format("Unexpected call to sendRedirect with %s (expected %s)", location, expectedRedirect));			
		}
		// everything OK
	}
	
	

	@Override
	public PrintWriter getWriter() throws IOException {
		return printWriter;
	}

	public String getOutputAsString() {
		printWriter.flush();
		return writer.toString();
	}
	
	

}
