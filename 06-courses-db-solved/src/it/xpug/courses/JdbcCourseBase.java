package it.xpug.courses;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class JdbcCourseBase implements CourseBase {

	private final String url;
	private final String username;
	private final String password;

	public JdbcCourseBase(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public long count() {
		ListOfRows rows = executeQuery("select count(*) as rowcount from courses");
		return (Integer) rows.get(0).get("rowcount");
	}

	public void create(Course course) {
		throw new RuntimeException("Not yet implemented!");
	}

	public List<Course> findAll() {
		throw new RuntimeException("Not yet implemented!");
	}

	public Course findById(Integer id) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Course findById(String id) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Course getLast() {
		throw new RuntimeException("Not yet implemented!");
	}

	public void update(Course course) {
		throw new RuntimeException("Not yet implemented!");
	}

	public void deleteAll() {
		execute("delete from courses");
	}

	private ListOfRows executeQuery(String string) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			ListOfRows result = new ListOfRows();
			connection = getConnection();
			statement = connection.prepareStatement(string);
			resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			while (resultSet.next()) {
				HashMap<String, Object> row = new HashMap<String, Object>();
				result.newRow();
				for (int i=0; i<metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnName(i+1);
					result.put(columnName, resultSet.getObject(columnName));
				}
			}
			return result;
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(connection);
			close(statement);
		}
	}

	private void execute(String sql, Object... params) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);

			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(statement);
			close(connection);			
		}
	}

	private void close(Object statement) {
		if (null != statement) {
			try {
				Method method = statement.getClass().getMethod("close");
				method.invoke(statement);
			} catch (Exception e1) {}
		}
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");
		return DriverManager.getConnection(url, username, password);
	}
}

