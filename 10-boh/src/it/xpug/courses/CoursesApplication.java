package it.xpug.courses;

import it.xpug.courses.persistence.CourseBase;
import it.xpug.html.Element;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoursesApplication {

	private final CourseBase database;
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final UserBase userBase;
	
	public CoursesApplication(HttpServletRequest request, HttpServletResponse response, CourseBase courses, UserBase userBase) {
		this.request = request;
		this.response = response;
		this.database = courses;
		this.userBase = userBase;
	}

	public void service() throws IOException {
		if (post("/app/users/authenticate")) {
			if (authenticate()) {
				redirect("/app/courses/list");
			} else {
				renderHtml(new LoginForm("/app/users/authenticate"));
			}
			
		} else if (get("/app/users/login")) {
			renderHtml(new LoginForm("/app/users/authenticate"));	
			
		} else if (userNotAuthenticated()) {
			redirect("/app/users/login");
		
		} else if (get("/app/courses/list") || get("/app")) {
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
			course.setTitle(request.getParameter("title"));
			database.update(course);
			redirect("/app/courses/list");

		} else {
			response.sendError(404);
		}
	}

	private boolean authenticate() {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User user = userBase.findByLoginAndEncryptedPassword(login, User.encrypt(password));
		if (null == user) {
			return false;
		}
		request.getSession().setAttribute("user_id", user.getId());
		return true;
	}

	private boolean userNotAuthenticated() {
		Integer userId = (Integer) request.getSession().getAttribute("user_id");
		return userId == null;
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
