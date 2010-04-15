package it.xpug.courses;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

public class NewCourseForm {

	public Element toHtml(String context) {
		return html(
				head(title("New Course"),
				body(
					h1("New Course"),
					form(context, "create", "post",
						text("Titolo: "),	
						textField("title"),
						newLine(),
						submitButton("Create")
				))));
	}

}
