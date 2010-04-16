package it.xpug.courses;

import it.xpug.html.Element;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoursesServlet extends HttpServlet {
	private FakeCourseBase courses = new FakeCourseBase();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoursesApplication application = new CoursesApplication(courses);
		Element html = application.doGet(request.getPathInfo());
		html.renderOn(response.getWriter());
	}		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoursesApplication application = new CoursesApplication(courses);
		String redirectUrl = application.doPost(request.getPathInfo(), request.getParameterMap());
		response.sendRedirect( request.getContextPath() + request.getServletPath() + redirectUrl);
	}

	protected void printInformation(HttpServletRequest request, PrintWriter writer) {
		writer.write("<h1>Hi!</h1>");
		writer.write("<ul>");
		writer.write("<li> contextPath '" + request.getContextPath() + "'</li>");
		writer.write("<li> servletPath '" + request.getServletPath() + "'</li>");
		writer.write("<li> pathInfo '" + request.getPathInfo() + "'</li>");
		writer.write("</ul>");
	}
	

}
