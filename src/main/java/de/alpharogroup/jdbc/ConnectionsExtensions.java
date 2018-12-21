/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;

/**
 * The class {@link ConnectionsExtensions} have convenience methods to create and connect to mysql,
 * H2 or postgresql databases.
 *
 * @author Asterios Raptis
 */
@UtilityClass
public final class ConnectionsExtensions
{

	/** H2-database constants. */
	/** Constant for the drivername from H2-database. */
	public static final String H2_DRIVERNAME = "org.h2.Driver";

	/** Constant for the urlprefix from H2-database. */
	public static final String H2_PREFIX_URL = "jdbc:h2";

	/** MySQL-database constants. */
	/** Constant for the drivername from MySQL-database. */
	public static final String MYSQL_DRIVERNAME = "com.mysql.jdbc.Driver";

	/** Constant for the urlprefix from MySQL-database. */
	public static final String MYSQL_PREFIX_URL = "jdbc:mysql://";

	/** Constant for the default port where the MySQL-database listen. */
	public static final int MYSQL_PORT = 3306;

	/** PostgreSQL-database constants. */
	/** Constant for the drivername from PostgreSQL-database. */
	public static final String POSTGRESQL_DRIVERNAME = "org.postgresql.Driver";

	/** Constant for the urlprefix from PostgreSQL-database. */
	public static final String POSTGRESQL_PREFIX_URL = "jdbc:postgresql://";

	/** Constant for the port where the PostgreSQL-database listen. */
	public static final int POSTGRESQL_PORT = 5432;

	/**
	 * Drops the given PostgreSQL database with the given databaseName if it does exist.
	 *
	 * @param hostname
	 *            the hostname
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located
	 */
	public static void dropPostgreSQLDatabase(final String hostname, final String databaseName,
		final String dbuser, final String dbpasswort) throws SQLException, ClassNotFoundException
	{
		if (existsPostgreSQLDatabase(hostname, databaseName, dbuser, dbpasswort))
		{
			Connection connection = null;
			Statement stmt = null;
			try
			{
				connection = ConnectionsExtensions.getPostgreSQLConnection(hostname, "", dbuser,
					dbpasswort);
				stmt = connection.createStatement();

				final StringBuilder sb = new StringBuilder();
				sb.append("DROP DATABASE ");
				sb.append(databaseName);

				stmt.executeUpdate(sb.toString());
				stmt.close();
				connection.close();
			}
			finally
			{
				if (stmt != null && !stmt.isClosed())
				{
					stmt.close();
				}
				if (connection != null && !connection.isClosed())
				{
					connection.close();
				}
			}
		}
	}

	/**
	 * Execute the sql script in the given BufferedReader from a file.
	 *
	 * @param bufferedReader
	 *            a BufferedReader from a script file.
	 * @param connection
	 *            the connection
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static void executeSqlScript(final BufferedReader bufferedReader,
		final Connection connection) throws IOException, SQLException
	{
		executeSqlScript(bufferedReader, connection, false);
	}

	/**
	 * Execute the sql script in the given BufferedReader from a file.
	 *
	 * @param bufferedReader
	 *            a BufferedReader from a script file.
	 * @param connection
	 *            the connection
	 * @param log
	 *            the flag if it will be logged.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static void executeSqlScript(final BufferedReader bufferedReader,
		final Connection connection, final boolean log) throws IOException, SQLException
	{
		final StringBuilder sb = new StringBuilder();
		String s;
		while ((s = bufferedReader.readLine()) != null)
		{
			sb.append(s);
		}
		bufferedReader.close();
		final String sqlScript = sb.toString();
		executeSqlScript(connection, sqlScript, log);
	}

	/**
	 * Execute the sql script given as String object.
	 *
	 * @param sqlScript
	 *            The sql script as String object.
	 * @param connection
	 *            the connection
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static void executeSqlScript(final Connection connection, final String sqlScript)
		throws SQLException
	{
		executeSqlScript(connection, sqlScript, false);
	}

	/**
	 * Execute the sql script given as String object.
	 *
	 * @param sqlScript
	 *            The sql script as String object.
	 * @param connection
	 *            the connection
	 * @param log
	 *            the flag if it will be logged.
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static void executeSqlScript(final Connection connection, final String sqlScript,
		final boolean log) throws SQLException
	{
		final String[] inst = sqlScript.split(";");

		final Statement st = connection.createStatement();
		if (log)
		{
			for (final String inst1 : inst)
			{
				if (!inst1.trim().equals(""))
				{
					st.executeUpdate(inst1);
				}
			}
		}
		else
		{
			for (final String inst1 : inst)
			{
				if (!inst1.trim().equals(""))
				{
					st.executeUpdate(inst1);
				}
			}
		}
		st.close();
	}

	/**
	 * Checks if the given database exists in the MySqlDatabase.
	 *
	 * @param hostname
	 *            the hostname
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @return true, if successful
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 */
	public static boolean existsMySqlDatabase(final String hostname, final String databaseName,
		final String dbuser, final String dbpasswort) throws SQLException, ClassNotFoundException
	{
		final List<String> existingDatabases = new ArrayList<>();
		final Connection connection = ConnectionsExtensions.getMySQLConnection(hostname, "", dbuser,
			dbpasswort);
		final DatabaseMetaData meta = connection.getMetaData();
		final ResultSet rs = meta.getCatalogs();
		while (rs.next())
		{
			final String existingDatabaseName = rs.getString("TABLE_CAT");
			existingDatabases.add(existingDatabaseName);
		}
		if (existingDatabases.contains(databaseName))
		{
			return true;
		}
		rs.close();
		if (connection != null && !connection.isClosed())
		{
			connection.close();
		}
		return false;
	}

	/**
	 * Checks if the given database exists in the Postgresql Database.
	 *
	 * @param hostname
	 *            the hostname
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @return true, if successful
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static boolean existsPostgreSQLDatabase(final String hostname, final String databaseName,
		final String dbuser, final String dbpasswort) throws ClassNotFoundException, SQLException
	{
		Connection connection = null;
		try
		{
			connection = ConnectionsExtensions.getPostgreSQLConnection(hostname, databaseName,
				dbuser, dbpasswort);
		}
		catch (final Exception e)
		{
			return false;
		}
		finally
		{
			if (connection != null && !connection.isClosed())
			{
				connection.close();
			}
		}
		return true;
	}

	/**
	 * Gets the H2 connection.
	 *
	 * @param path
	 *            the path
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @return the H2 connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static Connection getH2Connection(final String path, final String databaseName,
		final String dbuser, final String dbpasswort) throws ClassNotFoundException, SQLException
	{
		final String url = H2_PREFIX_URL + ":" + path + databaseName;
		Class.forName(H2_DRIVERNAME);
		return DriverManager.getConnection(url, dbuser, dbpasswort);
	}

	/**
	 * Gets the my sql connection.
	 *
	 * @param hostname
	 *            the hostname
	 * @param portNumber
	 *            the port number
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @return the my sql connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static Connection getMySQLConnection(final String hostname, final int portNumber,
		final String databaseName, final String dbuser, final String dbpasswort)
		throws ClassNotFoundException, SQLException
	{
		final String url = MYSQL_PREFIX_URL + hostname + ":" + portNumber + "/" + databaseName;
		Class.forName(MYSQL_DRIVERNAME);
		return DriverManager.getConnection(url, dbuser, dbpasswort);
	}

	/**
	 * Gets the my sql connection.
	 *
	 * @param hostname
	 *            the hostname
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @return the my sql connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static Connection getMySQLConnection(final String hostname, final String databaseName,
		final String dbuser, final String dbpasswort) throws ClassNotFoundException, SQLException
	{
		return getMySQLConnection(hostname, MYSQL_PORT, databaseName, dbuser, dbpasswort);
	}

	/**
	 * Gets the postgre sql connection.
	 *
	 * @param hostname
	 *            the hostname
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @return the postgre sql connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static Connection getPostgreSQLConnection(final String hostname,
		final String databaseName, final String dbuser, final String dbpasswort)
		throws ClassNotFoundException, SQLException
	{
		return getPostgresSQLConnection(hostname, POSTGRESQL_PORT, databaseName, dbuser,
			dbpasswort);
	}

	/**
	 * Gets the postgres sql connection from the given parameters.
	 *
	 * @param hostname
	 *            the hostname
	 * @param portNumber
	 *            the port number
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @return the postgres sql connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 */
	public static Connection getPostgresSQLConnection(final String hostname, final int portNumber,
		final String databaseName, final String dbuser, final String dbpasswort)
		throws ClassNotFoundException, SQLException
	{
		final StringBuilder sb = new StringBuilder();
		sb.append(POSTGRESQL_PREFIX_URL);
		sb.append(hostname);
		sb.append(":");
		sb.append(portNumber);
		sb.append("/");
		sb.append(databaseName);
		Class.forName(POSTGRESQL_DRIVERNAME);
		return DriverManager.getConnection(sb.toString().trim(), dbuser, dbpasswort);
	}

	/**
	 * Creates a database with the given databaseName (and sets the characterset to utf8 and the
	 * collate to utf8_general_ci) if it does not exist.
	 *
	 * @param hostname
	 *            the hostname
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 */
	public static void newMySqlDatabase(final String hostname, final String databaseName,
		final String dbuser, final String dbpasswort) throws SQLException, ClassNotFoundException
	{
		newMySqlDatabase(hostname, databaseName, dbuser, dbpasswort, "utf8", "utf8_general_ci");
	}

	/**
	 * Creates the a mySql database with the given databaseName if it does not exist.
	 *
	 * @param hostname
	 *            the hostname
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @param characterSet
	 *            the character set
	 * @param collate
	 *            the collate
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 */
	public static void newMySqlDatabase(final String hostname, final String databaseName,
		final String dbuser, final String dbpasswort, final String characterSet,
		final String collate) throws SQLException, ClassNotFoundException
	{
		if (!existsMySqlDatabase(hostname, databaseName, dbuser, dbpasswort))
		{
			final Connection connection = ConnectionsExtensions.getMySQLConnection(hostname, "",
				dbuser, dbpasswort);
			final Statement stmt = connection.createStatement();

			final String sql = "CREATE DATABASE " + databaseName + " DEFAULT CHARACTER SET "
				+ characterSet + " COLLATE " + collate;
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
		}
	}

	/**
	 * Creates the a PostgreSQL database with the given databaseName if it does not exist.
	 *
	 * @param hostname
	 *            the hostname
	 * @param databaseName
	 *            the database name
	 * @param dbuser
	 *            the dbuser
	 * @param dbpasswort
	 *            the dbpasswort
	 * @param characterSet
	 *            the character set
	 * @param collate
	 *            the collate
	 * @throws SQLException
	 *             is thrown if a database access error occurs or this method is called on a closed
	 *             connection
	 * @throws ClassNotFoundException
	 *             is thrown if the Class was not found or could not be located.
	 */
	public static void newPostgreSQLDatabase(final String hostname, final String databaseName,
		final String dbuser, final String dbpasswort, final String characterSet,
		final String collate) throws SQLException, ClassNotFoundException
	{
		if (!existsPostgreSQLDatabase(hostname, databaseName, dbuser, dbpasswort))
		{
			final Connection connection = ConnectionsExtensions.getPostgreSQLConnection(hostname,
				"", dbuser, dbpasswort);
			final Statement stmt = connection.createStatement();

			final StringBuilder sb = new StringBuilder();
			sb.append("CREATE DATABASE ");
			sb.append(databaseName);
			if (characterSet != null && !characterSet.isEmpty())
			{
				sb.append(" DEFAULT CHARACTER SET ");
				sb.append(characterSet);
				if (collate != null && !collate.isEmpty())
				{
					sb.append(" COLLATE ");
					sb.append(collate);
				}
			}
			stmt.executeUpdate(sb.toString());
			stmt.close();
			connection.close();
		}
	}

}
