package it.xpug.courses;

import it.xpug.html.Element;

import org.junit.Test;

import static org.junit.Assert.*;

import static it.xpug.html.Assert.*;


public class LoginFormTest {
	private final LoginForm loginForm = new LoginForm("some action");

	@Test
	public void returnsATitle() throws Exception {
		assertEquals("Please login", loginForm.pageTitle());
	}
		
	@Test
	public void returnsALoginForm() throws Exception {
		Element html = loginForm.toHtml();

		assertEquals("some action", html.getAttribute("action"));
		assertEquals("post", html.getAttribute("method"));
		
		assertContainsTextField("login", "", html);
		assertContainsPasswordField("password", "", html);
		assertContainsSubmitButton("password", html);
	}
	
	@Test
	public void mayContainAnErrorMessage() throws Exception {
		
	}
	
	@Test
	public void populatesFormFromParameters() throws Exception {
		
	}
}
