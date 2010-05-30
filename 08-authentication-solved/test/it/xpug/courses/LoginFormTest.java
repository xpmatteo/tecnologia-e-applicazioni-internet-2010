package it.xpug.courses;

import it.xpug.html.Element;

import org.junit.Test;

import static org.junit.Assert.*;

import static it.xpug.html.Assert.*;


public class LoginFormTest {
	private final LoginForm form = new LoginForm();

	@Test
	public void returnsATitle() throws Exception {
		assertEquals("Please login", form.pageTitle());
	}
	
	@Test
	public void returnsALoginForm() throws Exception {
		Element html = form.toHtml();
		
		assertContainsTextField("login", "", html);
		assertContainsPasswordField("password", "", html);
	}
	
	@Test
	public void mayContainAnErrorMessage() throws Exception {
		
	}
	
	@Test
	public void populatesFormFromParameters() throws Exception {
		
	}
}
