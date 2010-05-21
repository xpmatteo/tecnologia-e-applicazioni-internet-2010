package it.xpug.courses;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class Database {
	
	private final Connection connection;

	public Database(Connection connection) {
		this.connection = connection;
		
	}
	
	public ListOfRows select(String sql) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			ListOfRows result = new ListOfRows();
			statement = connection.prepareStatement(sql);
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
			statement = connection.prepareStatement(sql);

			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(statement);
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

}
