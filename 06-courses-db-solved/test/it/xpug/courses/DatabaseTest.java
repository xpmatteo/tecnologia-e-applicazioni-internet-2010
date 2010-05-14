package it.xpug.courses;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

	@Test
	public void connectionIsReturned() throws Exception {

		try {
			execute("drop table prova");
		} catch (Exception ignored) {}

		String sql = "" + "create table prova (" + "id integer identity," + "name varchar(255)," + "primary key(id))";

		execute(sql);

		execute("insert into prova (name) values (?)", "pippo");

		ListOfRows rows = executeQuery("select * from prova");
		assertEquals(1, rows.size());
		assertEquals("pippo", rows.get(0).get("name"));
	}

	private ListOfRows executeQuery(String string) throws Exception {
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
		} finally {
			close(resultSet);
			close(connection);
			close(statement);
		}
	}

	private void execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);

			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}
			statement.execute();
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
		return DriverManager.getConnection("jdbc:hsqldb:bin/test", "sa", "");
	}
}
