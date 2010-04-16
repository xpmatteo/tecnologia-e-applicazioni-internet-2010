package it.xpug.courses;

import it.xpug.html.Element;

import org.junit.Test;

import static org.junit.Assert.*;
import static it.xpug.html.Assert.*;

public class CourseListPageTest {

	@Test
	public void returnsAPage() throws Exception {
		CourseListPage page = new CourseListPage(new CourseList());
		
		String context = "/context";
		assertNotNull("no courses table", page.toHtml(context).findElementById("courses"));
		assertMatches("/html/head/title", page.toHtml(context));
		assertMatches("//table[@id='courses']", page.toHtml(context));
		assertMatches("//a[@href='/context/new']", page.toHtml(context));
	}
}
