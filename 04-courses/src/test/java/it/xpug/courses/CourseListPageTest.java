package it.xpug.courses;

import org.junit.Test;

import static org.junit.Assert.*;


public class CourseListPageTest {

	@Test
	public void returnsAPage() throws Exception {
		CourseListPage page = new CourseListPage(new CourseList());
		
		assertNotNull("no courses table", page.toHtml().findElementById("courses"));
//		assertMatches("/html/head/title", page.toHtml());
//		assertMatches("/html/body/", page.toHtml());
	}
}
