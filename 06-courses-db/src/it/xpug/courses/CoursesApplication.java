package it.xpug.courses;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoursesApplication {

	private final FakeCourseBase database;
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	
	public CoursesApplication(HttpServletRequest request, HttpServletResponse response, FakeCourseBase courses) {
		this.request = request;
		this.response = response;
		this.database = courses;
	}

	public void service() throws IOException {
		if (get("/app/courses/list") || get("/app")) {
			renderHtml(new CoursesList(database));
		
		} else if (get("/app/courses/new")) {
			renderHtml(new CourseForm(new Course("")));
		
		} else if (get("/app/courses/edit")) {
			Course course = database.findById(request.getParameter("id"));
			renderHtml(new CourseForm(course));
		
		} else if (post("/app/courses/create")) {
			database.create(new Course(request.getParameter("title")));
			redirect("/app/courses/list");
		
		} else if (post("/app/courses/update")) {
			Course course = database.findById(request.getParameter("id"));
			database.update(course);
			redirect("/app/courses/list");

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
		component.toHtml().renderOn(response.getWriter());
	}

	private void redirect(String path) throws IOException {
		response.sendRedirect(path);
	}

	private boolean matchesMethodAndPath(String method, String path) {
		return request.getMethod().equals(method) && 
			request.getRequestURI().equals(path);
	}
}
