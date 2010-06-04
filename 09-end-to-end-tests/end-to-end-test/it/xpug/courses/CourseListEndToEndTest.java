package it.xpug.courses;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import winstone.Launcher;
import winstone.Logger;

import static org.junit.Assert.*;


public class CourseListEndToEndTest {

	private static final String WAR_PATHNAME = "target/courses.war";
	private static final String SERVER_PORT = "8123";
	private WebClient client;
	private HtmlPage page;
	private HtmlForm htmlForm;

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
	public void failedLogin() throws Exception {
		loginAs("invalid user", "invalid password");
		assertNotLoggedIn();
	}
	
	@Test
	public void validLogin() throws Exception {
		loginAs("admin", "secret");
		assertLoggedIn();
	}
	
	@Test
	public void insertACourse() throws Exception {
		loginAs("admin", "secret");
		
		visit("/app/courses/list");
		int numberBefore = numberOfCoursesListed();
		clickOnNewCourseButton();
		insertCourseTitle("Course Title");
		insertCourseDescription("A Description");
		submitCourseForm();
		assertEquals(numberBefore+1, numberOfCoursesListed());
	}
	
	private void loginAs(String login, String password) throws Exception {
		visit("/app/users/login");
		HtmlForm loginForm = page.getForms().get(0);
		loginForm.getInputByName("login").setValueAttribute(login);
		loginForm.getInputByName("password").setValueAttribute(password);
		HtmlInput button = loginForm.getInputByName("submit");		
		page = button.click();
	}

	private void assertLoggedIn() {
		String title = page.getTitleText();
		assertTrue("logged in: " + title, title.startsWith("All Courses"));
	}

	private void assertNotLoggedIn() {
		String title = page.getTitleText();
		assertTrue("not logged in: " + title, title.startsWith("Please login"));
	}

	private void clickOnNewCourseButton() throws IOException {
		HtmlAnchor link = page.getAnchorByText("New Course");
		page = link.click();
	}
	
	private void insertCourseDescription(String string) {
		throw new RuntimeException("Not yet implemented!");
	}

	private void submitCourseForm() {
		throw new RuntimeException("Not yet implemented!");
	}

	private void insertCourseTitle(String newTitle) {
		List<HtmlForm> forms = page.getForms();
		assertTrue("new course form not found", forms.size() > 0);
		htmlForm = forms.get(0);
		HtmlInput courseTitleInput = htmlForm.getInputByName("title");
		courseTitleInput.setValueAttribute(newTitle);
	}

	private int numberOfCoursesListed() {
		HtmlTable table = (HtmlTable) page.getElementById("courses");
		assertNotNull("courses table not found", table);
		return table.getRowCount();
	}

	private void visit(String url) throws IOException, MalformedURLException {
		page = client.getPage("http://localhost:" + SERVER_PORT + url);
	}

	private static void startServer() throws IOException {
		Map<String, String> args = new HashMap();
		args.put("debug", "" + Logger.ERROR);
		Launcher.initLogger(args);
		args.put("httpPort", SERVER_PORT);
		args.put("ajp13Port", "-1");
		args.put("warfile", WAR_PATHNAME); 
		new Launcher(args);
	}

	private static void buildWar() throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("ant clean war");
		process.waitFor();
		assertEquals(0, process.exitValue());
	}
	
}
