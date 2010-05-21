package it.xpug.courses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class JdbcCourseBaseTest {

	private String url = "jdbc:hsqldb:file:./db/databases/courses_test;shutdown=true";
	private String username = "sa";
	private String password = "";
	private CourseBase base;
	
	@Before
	public void setUp() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection connection = DriverManager.getConnection(url, username, password);
		base = new JdbcCourseBase(new Database(connection ));
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
