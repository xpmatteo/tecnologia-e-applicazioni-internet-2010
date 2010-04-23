package it.xpug.courses;

import it.xpug.courses.util.FakeServletRequest;
import it.xpug.courses.util.FakeServletResponse;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CoursesApplicationTest {
	FakeCourseBase database = new FakeCourseBase();
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
		post("/app/courses/update");
		request.setParameter("id", "" + database.getLast().getId());
		request.setParameter("title", "bar");
		
		response.expectRedirect("/app/courses/list");
		
		assertEquals("foo", database.getLast().getTitle());
		app.service();
		assertEquals("bar", database.getLast().getTitle());
	}
	
	private void post(String path) {
		request.method = "POST";
		request.requestUri = path;
	}
}
