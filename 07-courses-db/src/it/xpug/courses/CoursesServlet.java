package it.xpug.courses;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
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
			connection = getConnection(getConfiguration());
			Database database = new Database(connection);
			CourseBase courses = new JdbcCourseBase(database );
			CoursesApplication app = new CoursesApplication(request, response, courses);
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
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("/courses.properties");
		if (null == stream) {
			throw new ServletException("cant't find courses.properties");
		}
		Properties properties = new Properties();
		properties.load(stream);
		return properties;
	}

	private Connection getConnection(Properties properties) throws ClassNotFoundException, SQLException {
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String driver = properties.getProperty("jdbc_driver");
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, username , password );
		connection.setAutoCommit(false);
		return connection;
	}
	

}
