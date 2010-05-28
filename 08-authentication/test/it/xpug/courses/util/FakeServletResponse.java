package it.xpug.courses.util;

import java.io.IOException;

public class FakeServletResponse extends EmptyServletResponse {

	public String redirectLocation;

	@Override
	public void sendRedirect(String location) throws IOException {
		this.redirectLocation = location;
	}
	
	

}
