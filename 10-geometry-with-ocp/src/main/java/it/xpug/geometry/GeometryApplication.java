package it.xpug.geometry;

import it.xpug.html.Element;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeometryApplication {

	private final List<PageComponent> components;
	
	public GeometryApplication(List<PageComponent> components) {
		this.components = components;
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		for (PageComponent component : components) {
			if (component.wantsToHandle(request)) {
				renderHtml(response, component);
				return;
			}
		}		
		response.sendError(404);
	}

	private void renderHtml(HttpServletResponse response, PageComponent component) throws IOException {
		Layout layout = new Layout(component);
		Element html = layout.toHtml();
		html.renderOn(response.getWriter());
	}
}
