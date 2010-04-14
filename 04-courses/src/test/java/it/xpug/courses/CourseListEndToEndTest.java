package it.xpug.courses;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import winstone.Launcher;
import winstone.Logger;

import static org.junit.Assert.*;


public class CourseListEndToEndTest {

	private static final String WAR_PATHNAME = "target/courses.war";
	private WebClient client;
	private HtmlPage page;

	@BeforeClass
	public static void buildAndStartServer() throws Exception {
		buildWar();
		startServer();
	}
	
	@Before
	public void setUp() throws Exception {
		client = new WebClient();
	}

	@Test
	public void run() throws Exception {
		visit("/courses");
		assertEquals(2, numberOfCoursesListed());
		clickOnNewCourseButton();
		insertCourseTitle("Course Title");
		insertCourseDescription("A Description");
		submitCourseForm();
		assertEquals(1, numberOfCoursesListed());
	}
	
	private void insertCourseDescription(String string) {
		throw new RuntimeException("Not yet implemented!");
	}

	private void submitCourseForm() {
		throw new RuntimeException("Not yet implemented!");
	}

	private void insertCourseTitle(String string) {
		throw new RuntimeException("Not yet implemented!");
	}

	private void clickOnNewCourseButton() {
		throw new RuntimeException("Not yet implemented!");
	}

	private int numberOfCoursesListed() {
		HtmlTable table = (HtmlTable) page.getElementById("courses");
		assertNotNull("courses table not found", table);
		return table.getRowCount();
	}

	private void visit(String url) throws IOException, MalformedURLException {
		page = client.getPage("http://localhost:8123" + url);
	}

	private static void startServer() throws IOException {
		Map<String, String> args = new HashMap();
		args.put("debug", "" + Logger.ERROR);
		Launcher.initLogger(args);
		args.put("httpPort", "8123");
		args.put("ajp13Port", "-1");
		args.put("warfile", WAR_PATHNAME); 
		new Launcher(args);
	}

	private static void buildWar() throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("ant war");
		process.waitFor();
		assertEquals(0, process.exitValue());
	}
}
