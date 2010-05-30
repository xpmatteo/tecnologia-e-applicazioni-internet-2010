package it.xpug.courses;

import it.xpug.html.Element;

import org.junit.Test;

import static it.xpug.html.Assert.*;

public class CourseFormTest {

	@Test
	public void htmlContainsFormFields() throws Exception {
		Course course = new Course("a title");
		course.setId(123);
		CourseForm form = new CourseForm(course);
		
		Element html = form.toHtml();

		assertContainsTextField("title", "a title", html);
		assertContainsHiddenField("id", "123", html);
	}

}
