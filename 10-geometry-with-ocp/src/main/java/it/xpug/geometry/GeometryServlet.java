package it.xpug.geometry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeometryServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PageComponent> pages = new ArrayList<PageComponent>();

		pages.add(new WelcomePage());
		pages.add(new SquareAreaPage(new SquareAreaCalculator(), request.getParameterMap()));
		pages.add(new TriangleAreaPage());
		pages.add(new SphereVolumePage());

		GeometryApplication app = new GeometryApplication(pages);
		app.service(request, response);	
	}
	

}
