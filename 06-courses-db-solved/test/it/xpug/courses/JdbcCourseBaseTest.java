package it.xpug.courses;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class JdbcCourseBaseTest {

	private String url = "jdbc:hsqldb:file:./db/databases/courses_test;shutdown=true";
	private String username = "sa";
	private String password = "";
	private final CourseBase base = new JdbcCourseBase(url, username, password);
	
	@Before
	public void setUp() throws Exception {
		base.deleteAll();
	}
	
	@Test
	public void countReturnsNumberOfRows() throws Exception {
		assertEquals(0, base.count());
		base.create(new Course("pippo"));
		assertEquals(1, base.count());
	}
}
