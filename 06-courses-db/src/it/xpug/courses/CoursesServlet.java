package it.xpug.courses;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoursesServlet extends HttpServlet {
	private FakeCourseBase database = new FakeCourseBase();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		CoursesApplication app = new CoursesApplication(request, response, database);
		app.service();	
	}
	

}
