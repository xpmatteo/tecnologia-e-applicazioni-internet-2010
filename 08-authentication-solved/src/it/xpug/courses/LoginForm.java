package it.xpug.courses;

import static it.xpug.html.HtmlHelper.*;
import it.xpug.html.Element;

public class LoginForm implements PageComponent {

	static final String AUTHENTICATE_ACTION = "/app/users/authenticate";

	public String pageTitle() {
		return "Please login";
	}

	public Element toHtml() {
		return form("action", "method",
				textField("login"),
				passwordField("password", ""));
	}

}
