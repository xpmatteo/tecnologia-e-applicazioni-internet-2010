package it.xpug.courses;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CoursesServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		try {
			connection = Database.getConnection(getConfiguration());
			Database database = new Database(connection);
			CourseBase courses = new JdbcCourseBase(database);
			UserBase userBase = new JdbcUserBase(database);
			CoursesApplication app = new CoursesApplication(request, response, courses, userBase);
			app.service();	
			connection.commit();
		} catch (Exception e) {
			rollback(connection);
			throw new ServletException(e);
		} finally {
			close(connection);
		}
	}

	private void rollback(Connection connection) {
		try {
			if (null != connection)
				connection.rollback();
		} catch (SQLException ignore) {}
	}

	private void close(Connection connection) {
		try {
			if (null != connection)
				connection.close();
		} catch (SQLException ignore) {}
	}

	private Properties getConfiguration() throws IOException, ServletException {
		InputStream stream = this.getClass().getResourceAsStream("/courses.properties");
		if (null == stream) {
			throw new ServletException("cant't find courses.properties");
		}
		Properties properties = new Properties();
		properties.load(stream);
		return properties;
	}

}
