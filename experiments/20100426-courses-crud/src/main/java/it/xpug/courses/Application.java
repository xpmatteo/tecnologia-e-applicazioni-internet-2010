package it.xpug.courses;

import it.xpug.html.Element;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Application {

	private final FakeCourseBase database;
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	
	public Application(HttpServletRequest request, HttpServletResponse response, FakeCourseBase courses) {
		this.request = request;
		this.response = response;
		this.database = courses;
	}

	public void service() throws IOException {
		if (get("/app/courses/list") || get("/app")) {
			response.getWriter().write("<x/>");
//			renderHtml(new CoursesList(database));
		
//		} else if (get("/app/courses/new")) {
//			renderHtml(new CourseForm(new Course("")));
//		
//		} else if (get("/app/courses/edit")) {
//			Course course = database.findById(request.getParameter("id"));
//			renderHtml(new CourseForm(course));
//		
//		} else if (post("/app/courses/create")) {
//			database.create(new Course(request.getParameter("title")));
//			redirect("/app/courses/list");
//		
//		} else if (post("/app/courses/update")) {
//			Course course = database.findById(request.getParameter("id"));
//			course.setTitle(request.getParameter("title"));
//			database.update(course);
//			redirect("/app/courses/list");

		} else {
			response.sendError(404);
		}
	}

	private boolean post(String path) {
		return matchesMethodAndPath("POST", path);
	}

	private boolean get(String path) {
		return matchesMethodAndPath("GET", path);
	}
	
	private void renderHtml(PageComponent component) throws IOException {
		Layout layout = new Layout(component);
		Element html = layout.toHtml();
		html.renderOn(response.getWriter());
	}

	private void redirect(String path) throws IOException {
		response.sendRedirect(path);
	}

	private boolean matchesMethodAndPath(String method, String path) {
		return request.getMethod().equals(method) && 
			request.getRequestURI().equals(path);
	}
}
