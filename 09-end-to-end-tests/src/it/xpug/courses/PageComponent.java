package it.xpug.courses;

import it.xpug.html.Element;

public interface PageComponent {
	String pageTitle();
	Element toHtml();
}
