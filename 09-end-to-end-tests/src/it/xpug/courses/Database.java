package it.xpug.courses;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;

public class Database {
	
	private final Connection connection;

	public Database(Connection connection) {
		this.connection = connection;
	}
	
	public ListOfRows select(String sql, Object ... params) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			ListOfRows result = new ListOfRows();
			statement = prepareStatement(sql, params);
			resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			while (resultSet.next()) {
				result.newRow();
				for (int i=0; i<metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnName(i+1);
					result.put(columnName, resultSet.getObject(columnName));
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(statement);
		}
	}

	public void execute(String sql, Object... params) {
		PreparedStatement statement = null;
		try {
			statement = prepareStatement(sql, params);
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(statement);
		}
	}

	private PreparedStatement prepareStatement(String sql, Object... params) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			statement.setObject(i + 1, params[i]);
		}
		return statement;
	}

	private void close(ResultSet resultSet) {
		if (null != resultSet) {
			try {
				resultSet.close();
			} catch (Exception ignored) {}
		}
	}

	private void close(Statement statement) {
		if (null != statement) {
			try {
				statement.close();
			} catch (Exception ignored) {}
		}
	}

}
