package it.xpug.courses;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import winstone.Launcher;
import winstone.Logger;

import static org.junit.Assert.*;


public class CourseListTest {

	private static final String WAR_PATHNAME = "target/courses.war";

	@BeforeClass
	public static void setUp() throws Exception {
		buildWar();
		startServer();
	}

	@Test
	public void run() throws Exception {
		WebClient client = new WebClient();
		HtmlPage page = client.getPage("http://localhost:8123/");
		assertEquals("pippo", page.getTitleText());
	}

	private static void startServer() throws IOException {
		Map<String, String> args = new HashMap();
		args.put("debug", "" + Logger.ERROR);
//		Launcher.initLogger(args);
		args.put("httpPort", "8123");
		args.put("warfile", WAR_PATHNAME); 
		new Launcher(args);
	}

	private static void buildWar() throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("ant war");
		process.waitFor();
		assertEquals(0, process.exitValue());
	}
}
