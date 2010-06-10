package it.xpug.courses.persistence;

import it.xpug.courses.Course;
import it.xpug.courses.persistence.CourseBase;

import org.junit.Test;

import static org.junit.Assert.*;


public class CourseTest {

	CourseBase database = new FakeCourseBase();

	@Test
	public void newCoursesHaveNullId() throws Exception {
		assertNull("id", new Course("pippo").getId());
	}
	
	@Test
	public void courseSavedInDatabaseHaveNonNullId() throws Exception {
		Course course = new Course("pluto");
		database.create(course);
		assertNotNull("null id", course.getId());
	}
	
	@Test
	public void canFindCourseById() throws Exception {
		Course first = new Course("first");
		Course second = new Course("second");
		database.create(first);
		database.create(second);
		
		assertEquals(first, database.findById(first.getId()));
		assertEquals(second, database.findById(second.getId()));
	}
	
	@Test
	public void canFindCourseByIdAsString() throws Exception {
		Course first = new Course("first");
		database.create(first);		
		assertEquals(first, database.findById(first.getId().toString()));
	}
	
	@Test
	public void updateChangesTheGivenElement() throws Exception {
		Course course = new Course("pippo");
		database.create(course);
		Object countBefore = database.count();
		
		course.setTitle("pluto");
		database.update(course);
		
		assertEquals(countBefore, database.count());
		assertEquals("pluto", database.findById(course.getId()).getTitle());
	}
}
