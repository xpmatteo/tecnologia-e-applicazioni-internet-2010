package it.xpug.courses;

import java.io.IOException;

import it.xpug.courses.persistence.CourseBase;
import it.xpug.courses.persistence.FakeCourseBase;
import it.xpug.courses.persistence.FakeUserBase;
import it.xpug.courses.util.FakeServletRequest;
import it.xpug.courses.util.FakeServletResponse;
import it.xpug.html.XmlDocument;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

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
	
	@Test
	public void rendersLoginForm() throws Exception {
		get("/app/users/login");
		assertResponseIsHtml();
		
	}

	@Test
	public void willAuthenticateUser() throws Exception {
		userBase.containsOnlyThisUser(123, "admin", "secret");
		
		request.setParameter("login", "admin");
		request.setParameter("password", "secret");
		post("/app/users/authenticate");
		
		assertEquals(123, request.getSession().getAttribute("user_id"));
		assertRedirectedTo("/app/courses/list");
	}
	
	@Test
	public void willStopUsersIfPasswordIsWrong() throws Exception {
		userBase.containsOnlyThisUser(123, "admin", "secret");
		
		request.setParameter("login", "admin");
		request.setParameter("password", "bad password");
		post("/app/users/authenticate");
		
		assertEquals("nothing in session", null, request.getSession().getAttribute("user_id"));
		assertEquals("no redirection", null, response.redirectLocation);
		
		assertResponseIsHtml();
//		assertResponseContains("Bad login or password");
	}
	
	protected void assertResponseContains(String string) {
		assertThat(response.getBody(), containsString(string));
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
	
	private void assertResponseIsHtml() {
		assertFalse("Empty response body; was expecting html", 0 == response.getBody().length());
		new XmlDocument(response.getBody());
	}

	private void assertRedirectedTo(String path) {
		assertEquals("Redirection", path, response.redirectLocation);
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
