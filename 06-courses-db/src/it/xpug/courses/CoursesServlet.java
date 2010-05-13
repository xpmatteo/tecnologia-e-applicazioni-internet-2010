package it.xpug.courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class CoursesServlet extends HttpServlet {
	private FakeCourseBase database = new FakeCourseBase();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			Connection connection = getConnectionFromJndi();
			
			Statement statement = connection.createStatement();
			statement.execute("insert into courses (name) values ('foobar')");			
			ResultSet resultSet = statement.executeQuery("select name from courses");
			PrintWriter writer = response.getWriter();
			write(writer, "hello");
			while (resultSet.next()) {
				write(writer, resultSet.getString("name"));
			}
			write(writer, "goodbye");
			
			connection.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private Connection getConnectionFromDriverManager() throws SQLException, ClassNotFoundException {
    	Class.forName("org.hsqldb.jdbcDriver");
		return DriverManager.getConnection("jdbc:hsqldb:db/databases/courses_development", "sa", "");
	}
	
	private Connection getConnectionFromJndi() throws SQLException, NamingException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/courses_database");
		return ds.getConnection();
	}

	private void write(PrintWriter writer, String message) {
		writer.print("<p>" + message + "</p>");
	}
}
