package it.xpug.courses;

import it.xpug.courses.util.TestDatabase;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class JdbcCourseBaseTest {

	private CourseBase base;
	
	@Before
	public void setUp() throws Exception {
		base = new JdbcCourseBase(new TestDatabase());
		base.deleteAll();
	}
	
	@Test
	public void countReturnsNumberOfRows() throws Exception {
		assertEquals(0, base.count());
		base.create(new Course("pippo"));
		assertEquals(1, base.count());
	}
	
	@Test
	public void createUseAllFieldsFromCourse() throws Exception {
		Course original = new Course("pippo");
		base.create(original);
		List<Course> courses = base.findAll();
		assertEquals(1, courses.size());
		assertEquals(original, courses.get(0));
	}
}
