package it.xpug.courses;

import java.io.IOException;

import it.xpug.courses.util.FakeServletRequest;
import it.xpug.courses.util.FakeServletResponse;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CoursesApplicationTest {
	CourseBase database = new FakeCourseBase();
	FakeServletRequest request = new FakeServletRequest();
	FakeServletResponse response = new FakeServletResponse();
	CoursesApplication app = new CoursesApplication(request, response, database);

	@Before
	public void setUp() throws Exception {
		Course course = new Course("foo");
		database.create(course);
	}
	
	@Test
	public void updateChangesMyObjectAndRedirects() throws Exception {
		request.setParameter("id", "" + database.getLast().getId());
		request.setParameter("title", "bar");

		post("/app/courses/update");
		
		assertEquals("bar", database.getLast().getTitle());
		assertEquals("/app/courses/list", response.redirectLocation);
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
