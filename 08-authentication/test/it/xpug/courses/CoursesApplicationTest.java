package it.xpug.courses;

import java.io.IOException;

import it.xpug.courses.util.FakeServletRequest;
import it.xpug.courses.util.FakeServletResponse;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class CoursesApplicationTest {
	CourseBase courseBase = new FakeCourseBase();
	FakeUserBase userBase = new FakeUserBase();
	FakeServletRequest request = new FakeServletRequest();
	FakeServletResponse response = new FakeServletResponse();
	CoursesApplication app = new CoursesApplication(request, response, courseBase, userBase);

	@Before
	public void setUp() throws Exception {
		Course course = new Course("foo");
		courseBase.create(course);
	}
	
	@Test
	public void redirectsToLoginFormIfNotAuthenticated() throws Exception {
		get("/app/courses/list");
		assertEquals("/app/users/login", response.redirectLocation);
	}

	@Test@Ignore
	public void willAuthenticateUser() throws Exception {
		userBase.containsOnlyThisUser(123, "admin", "secret");
		
		request.setParameter("login", "admin");
		request.setParameter("password", "secret");
		post("/app/users/authenticate");
		
		assertEquals(123, request.getSession().getAttribute("user_id"));
		assertEquals("/app/courses/list", response.redirectLocation);
	}
	
	@Test@Ignore
	public void willStopUsersIfPasswordIsWrong() throws Exception {
		userBase.containsOnlyThisUser(123, "admin", "secret");
		
		request.setParameter("login", "admin");
		request.setParameter("password", "bad password");
		post("/app/users/authenticate");
		
		assertEquals("nothing in session", null, request.getSession().getAttribute("user_id"));
		assertEquals("no redirection", null, response.redirectLocation);
	}
	
	@Test
	public void updateChangesMyObjectAndRedirects() throws Exception {
		loginAs("admin");
		request.setParameter("id", "" + courseBase.getLast().getId());
		request.setParameter("title", "bar");

		post("/app/courses/update");
		
		assertEquals("bar", courseBase.getLast().getTitle());
		assertEquals("/app/courses/list", response.redirectLocation);
	}
	
	private void loginAs(String string) {
		request.setSessionAttribute("user_id", 0);
	}

	private void post(String path) throws IOException {
		request.method = "POST";
		request.requestUri = path;
		app.service();
	}

	private void get(String path) throws IOException {
		request.method = "GET";
		request.requestUri = path;
		app.service();
	}
}
