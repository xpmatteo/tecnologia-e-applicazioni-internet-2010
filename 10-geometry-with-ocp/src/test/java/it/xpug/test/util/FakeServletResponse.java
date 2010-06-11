package it.xpug.test.util;

import java.io.IOException;
import static org.junit.Assert.*;
import static java.lang.String.format;

public class FakeServletResponse extends EmptyServletResponse {

	private String expectedRedirect;

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
	
	

}
