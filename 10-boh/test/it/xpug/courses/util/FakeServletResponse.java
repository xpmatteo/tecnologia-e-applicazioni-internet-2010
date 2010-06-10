package it.xpug.courses.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class FakeServletResponse extends EmptyServletResponse {

	public String redirectLocation;
	private Writer writer = new StringWriter();
	private PrintWriter printWriter = new PrintWriter(writer);

	public String getBody() {
		return writer.toString();
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		this.redirectLocation = location;
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		return printWriter;
	}
	
}
