package it.xpug.courses;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import winstone.Launcher;
import winstone.Logger;

import static org.junit.Assert.*;


public class CourseListEndToEndTest {

	private static final String VALID_USER = "donald";
	private static final String VALID_USER_PASSWORD = "duck";
	private static final String WAR_PATHNAME = "target/courses.war";
	private static final String SERVER_PORT = "8123";
	private WebClient client;
	private HtmlPage page;

	@BeforeClass
	public static void buildAndStartServer() throws Exception {
		buildWar();
		startServer();
	}
	
	@Before
	public void setUp() throws Exception {
		deleteAllUsers();
		saveUser(VALID_USER, VALID_USER_PASSWORD);
		client = new WebClient();
	}

	@Test
	public void failedLogin() throws Exception {
		loginAs("invalid user", "invalid password");
		assertNotLoggedIn();
	}
	
	@Test
	public void validLogin() throws Exception {
		loginAs(VALID_USER, VALID_USER_PASSWORD);
		assertLoggedIn();
	}
	
	@Test
	public void insertACourse() throws Exception {
		loginAs(VALID_USER, VALID_USER_PASSWORD);
		
		visit("/app/courses/list");
		int numberBefore = numberOfCoursesListed();
		clickOnNewCourseButton();
		insertCourseTitle("Course Title");
		insertCourseDescription("A Description");
		submitCourseForm();
		assertEquals(numberBefore+1, numberOfCoursesListed());
	}
	
	private void submitCourseForm() {
		throw new RuntimeException("Not yet implemented!");
	}

	private void insertCourseDescription(String string) {
		throw new RuntimeException("Not yet implemented!");
	}

	private void loginAs(String login, String password) throws Exception {
		visit("/app/users/login");
		getInputByName("login").setValueAttribute(login);
		getInputByName("password").setValueAttribute(password);
		page = getSubmitButton().click();		
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
	
	private HtmlInput getInputByName(String name) {
		try {
			return getFirstForm().getInputByName(name);
		} catch (ElementNotFoundException e) {
			fail("can't find input element for " + name);
			return null;
		}
	}
	
	private HtmlElement getSubmitButton() {
		List<?> elements = page.getByXPath("//form//input[@type='submit']");
		if (0 == elements.size()) 
			fail("no input button found");
		return (HtmlElement) elements.get(0);
	}



	private void insertCourseTitle(String newTitle) {
		getInputByName("title").setValueAttribute(newTitle);
	}

	private HtmlForm getFirstForm() {
		List<HtmlForm> forms = page.getForms();
		assertTrue("form not found", forms.size() > 0);
		return forms.get(0);
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
	
	private void saveUser(String login, String password) throws Exception {
		String encryptedPassword = User.encrypt(password);
		execute("insert into users (login, encrypted_password) values (?, ?)", login, encryptedPassword);
	}

	private void deleteAllUsers() throws Exception {
		execute("delete from users");
	}

	private void execute(String sql, Object ... params) throws Exception {
		Connection connection = Database.getConnection(new Configuration());
		new Database(connection).execute(sql, params);
		connection.commit();
		connection.close();
	}
}
