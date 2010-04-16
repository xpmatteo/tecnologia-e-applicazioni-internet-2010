package it.xpug.courses;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoursesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath() + request.getServletPath();
		PrintWriter writer = response.getWriter();
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/new")) {
			NewCourseForm form = new NewCourseForm();
			form.toHtml(context).renderOn(writer);
		} else {
			CourseList list = new CourseList();
			CourseListPage page = new CourseListPage(list);			
			page.toHtml(context).renderOn(writer);
		}
	}
	

}
