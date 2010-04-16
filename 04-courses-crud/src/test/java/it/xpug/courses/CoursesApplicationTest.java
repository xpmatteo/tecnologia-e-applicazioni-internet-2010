package it.xpug.courses;

import it.xpug.html.Element;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;


public class CoursesApplicationTest {
	private FakeCourseBase courses = new FakeCourseBase();
	private CoursesApplication app = new CoursesApplication(courses);

	@Test
	public void createRedirectsToListPage() throws Exception {		
		Map<String, String[]> params = new HashMap();
		params.put("title", new String[] { "titolo" });
	
		String redirect = app.doPost("/create", params);
		
		assertEquals("/list", redirect);
	}
	
	@Test
	public void createSavesANewCourseToDatabase() throws Exception {
		long countBefore = courses.count();
		Map<String, String[]> params = new HashMap();
		params.put("title", new String[] { "titolo" });
		
		app.doPost("/create", params);
		assertEquals("number of courses after create", countBefore + 1, courses.count());
		assertEquals(new Course("titolo"), courses.getLast());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullTitleRaisesException() throws Exception {
		app.doPost("/create", Collections.EMPTY_MAP);
	}
	
	@Test
	public void returnsAListOfCOursesInList() throws Exception {
		courses.create("primo");
		courses.create("secondo");
		
		Element html = app.doGet("/list");
		
		assertTrue("contains head and title", html.matchesXPath("/html/head/title"));
		assertTrue("contains body and heading", html.matchesXPath("/html/body/h1"));
		assertTrue("contains a table of courses", html.matchesXPath("//table[@id='courses']"));
		assertEquals("number of rows in table", 2, html.numberOfXPathMatches("//table[@id='courses']/tr"));
	}
	
	
}
