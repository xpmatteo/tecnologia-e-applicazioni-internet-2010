package it.xpug.courses;
import it.xpug.html.Element;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class CourseListTest {
	private final CourseList list = new CourseList();
	private final Course course0 = new Course("Course 0");
	private final Course course1 = new Course("Course 1");

	@Before
	public void setUp() throws Exception {
		list.add(course0);
		list.add(course1);
	}
	
	@Test
	public void containsAListOfCourses() throws Exception {
		assertEquals(2, list.size());
	}
	
	@Test
	public void showsATableOfCourses() throws Exception {
		Element html = list.toHtml();
		assertEquals("courses", html.getAttribute("id"));
		assertEquals("table", html.getName());
		
		List<Element> rows = html.getElements();
		assertEquals(2, rows.size());
		
		assertRenders(course0, rows.get(0));
		assertRenders(course1, rows.get(1));
	}

	private void assertRenders(Course course, Element element) {
		assertEquals(course.toTableRow(), element);
	}
	


}
