package it.xpug.courses;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoursesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseList list = new CourseList();
		list.add(new Course("Cinema 2.0"));
		list.add(new Course("Fumetti 3.0"));
		CourseListPage page = new CourseListPage(list);
		page.toHtml().renderOn(response.getWriter());
	}
	

}
