package it.xpug.counterViews;

import it.xpug.html.HtmlDocument;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CounterviewsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Counter counter = new Counter(request.getParameter("value"));
		HtmlDocument document = counter.toHtmlDocument();
		document.renderOn(response.getWriter());
	}
	

}
