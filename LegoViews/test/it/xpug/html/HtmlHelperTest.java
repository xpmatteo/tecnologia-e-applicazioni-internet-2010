package it.xpug.html;

import org.junit.Test;

import static org.junit.Assert.*;
import static it.xpug.html.HtmlHelper.*;

public class HtmlHelperTest {
	
	@Test
	public void buildsOneParagraph() throws Exception {
		assertEquals(new Element("p"), paragraph());
	}
	
	@Test
	public void buildsNestedParagraphsWithoutAnonymousFunctions() throws Exception {
		Element div = div(paragraph(), paragraph());
		assertEquals(new Element("div").add(new Element("p")).add(new Element("p")), div);
	}
	
	@Test
	public void buildsFullUrlFromServletContext() throws Exception {
		Element link = link("/pippo", "pluto", "a link");
		assertEquals("/pippo/pluto", link.getAttribute("href"));
	}
	
	@Test
	public void testname() throws Exception {
	    HtmlDocument html = html(
	        head(
	            title("hello")
	        ),
	        body(
	            div(
	                paragraph("foo"),
	                link("href", "label")
	            )
	        )
	    );

	    HtmlDocument expected = new Element("html")
	    	.add(new Element("head").add(new Element("title").add(new TextNode("hello"))))
	    	.add(new Element("body")
	    		.add(new Element("div")
	    			.add(new Element("p").add(new TextNode("foo")))
	    			.add(new Element("a").add(new TextNode("label")).with("href", "href"))
	    			)
	    		);
		assertEquals(expected , html);
	}	
	
}
