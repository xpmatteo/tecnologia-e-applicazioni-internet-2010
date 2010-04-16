package it.xpug.courses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import static org.junit.Assert.*;


public class CoursesServletTest {
	HttpServletRequest request = new EmptyHttpServletRequest() {

		@Override
		public String getContextPath() {
			return "/context";
		}

		@Override
		public String getPathInfo() {
			"";
		}

		@Override
		public String getServletPath() {
			throw new RuntimeException("Not yet implemented!");
		}
		
	};

	@Test
	public void testname() throws Exception {
		CoursesServlet servlet = new CoursesServlet();
		FakeServletResponse response = new FakeServletResponse();
		response.pathInfo = null;
		servlet.doGet(request, response);
	}
}
