package it.xpug.courses;

import static it.xpug.html.HtmlHelper.*;
import it.xpug.html.Element;

public class LoginForm implements PageComponent {

	private final String action;

	public LoginForm(String action) {
		this.action = action;
	}

	public String pageTitle() {
		return "Please login";
	}

	public Element toHtml() {
		return form(action, "post",
				paragraph(
					text("Login:"),
					newLine(),
					textField("login")),
				paragraph(
					text("Password:"),
					newLine(),
					passwordField("password", "")
				),
				paragraph(
					submitButton("OK")
				)
			);
	}

}
