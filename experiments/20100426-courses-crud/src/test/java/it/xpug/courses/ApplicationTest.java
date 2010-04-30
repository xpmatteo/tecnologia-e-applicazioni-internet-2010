package it.xpug.courses;

import java.io.IOException;

import it.xpug.courses.util.FakeServletRequest;
import it.xpug.courses.util.FakeServletResponse;
import it.xpug.html.XmlDocument;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ApplicationTest {
	FakeCourseBase database = new FakeCourseBase();
	FakeServletRequest request = new FakeServletRequest();
	FakeServletResponse response = new FakeServletResponse();
	Application app = new Application(request, response, database);

	@Before
	public void setUp() throws Exception {
		Course course = new Course("foo");
		database.create(course);
	}
	
	@Test
	public void returnsAListOfCourses() throws Exception {
		get("/app/courses/list");
		assertCoursesTableContains(new Course("foo"));
	}
	
	private void assertCoursesTableContains(Course ... courses) {
		String output = response.getOutputAsString();
		XmlDocument document = new XmlDocument(output);
		assertTrue(document.matchesXPath("//table[@id='courses']"));
	}

//	@Test
//	public void updateChangesMyObjectAndRedirects() throws Exception {
//		post("/app/courses/update");
//		request.setParameter("id", "" + database.getLast().getId());
//		request.setParameter("title", "bar");
//		
//		response.expectRedirect("/app/courses/list");
//		
//		assertEquals("foo", database.getLast().getTitle());
//		app.service();
//		assertEquals("bar", database.getLast().getTitle());
//	}
	
	private void get(String path) throws IOException {
		request.method = "GET";
		request.requestUri = path;
		app.service();
	}

	protected void post(String path) throws IOException {
		request.method = "POST";
		request.requestUri = path;
		app.service();
	}
}
