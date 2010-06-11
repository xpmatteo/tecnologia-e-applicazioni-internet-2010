package it.xpug.geometry;

import javax.servlet.http.HttpServletRequest;

import it.xpug.html.Element;

public interface PageComponent {
	String pageTitle();
	Element toHtml();
	boolean wantsToHandle(HttpServletRequest request);
}
