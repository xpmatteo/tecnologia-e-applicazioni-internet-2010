package it.xpug.courses;

import it.xpug.html.Element;

import org.junit.Test;

import static org.junit.Assert.*;
import static it.xpug.html.Assert.*;

@SuppressWarnings("unused")
public class NewCourseFormTest {

	@Test
	public void generatesAForm() throws Exception {
		NewCourseForm form = new NewCourseForm();
		Element html = form.toHtml("/context");
		assertMatches("/html/head/title", html);
		assertMatches("//form[@action='/context/create'][@method='post']", html);
		assertMatches("//form//input[@type='text'][@name='title']", html);
		assertMatches("//form//input[@type='submit']", html);
	}
	
}
