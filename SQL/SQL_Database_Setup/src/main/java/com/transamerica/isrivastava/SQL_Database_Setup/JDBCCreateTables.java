package com.transamerica.isrivastava.SQL_Database_Setup;

import java.sql.*;

public class JDBCCreateTables {

	public JDBCCreateTables(String JDBC_DRIVER, String DB_URL, String USER, String PASS) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			
			if(DB_URL.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
				conn = DriverManager.getConnection(DB_URL);
			} else {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			}
			
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query	
			createEmployeeTable(conn, stmt);
			createJiraffeEmployeeTable(conn, stmt);
			createJiraffeTeamTable(conn, stmt);
			createSprintTable(conn, stmt);
			createTeamTable(conn, stmt);
			createTicketTable(conn, stmt);
			
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("---------------Done Creating Tables---------------");
	}
	
	private void createTicketTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating onejira ticket table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE ONEJIRA_TICKETS " + 
					"(ID VARCHAR(255) not NULL, " + 
					" TICKET_KEY VARCHAR(255) not NULL, " +
					" CREATION_DATE VARCHAR(255), "  +
					" RESOLUTION_DATE VARCHAR(255), " +
					" STORY_POINTS INTEGER, " +
					" STATUS VARCHAR(255) not NULL, " +
					" ASSIGNEE VARCHAR(255), " +
					" TEAM VARCHAR(255) not NULL, " +
					" SPRINT VARCHAR(255), " +
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( TICKET_KEY, STATUS ))";

		stmt.executeUpdate(sql);
		System.out.println("Created onejira ticket table in given database...");
	}


	private void createTeamTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating onejira team table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE ONEJIRA_TEAMS " +
					"(ID VARCHAR(255) not NULL, " +
					" TEAM_KEY VARCHAR(255) not NULL, " + 
					" NAME VARCHAR(255) not NULL, " + 
					" LEADER VARCHAR(255), " +
					" CHANNEL VARCHAR(255), " +
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( ID ))";

		stmt.executeUpdate(sql);
		System.out.println("Created onejira team table in given database...");
	}


	private void createSprintTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating onejira sprint table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE ONEJIRA_SPRINTS " + 
					"(NAME VARCHAR(255) not NULL, " +
					" TEAM VARCHAR(255) not NULL, " + 
					" START_DATE VARCHAR(255), " + 
					" END_DATE VARCHAR(255), " +
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( NAME, TEAM ))";

		stmt.executeUpdate(sql);
		System.out.println("Created onejira sprint table in given database...");
	}


	private void createJiraffeTeamTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating jiraffe team table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE JIRAFFE_TEAMS " +
					"(ID VARCHAR(255) not NULL, " +
					" TEAM_KEY VARCHAR(255), " + 
					" NAME VARCHAR(255), " + 
					" POINTS INTEGER, " +
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( ID ))";

		stmt.executeUpdate(sql);
		System.out.println("Created jiraffe team table in given database...");
	}


	private void createJiraffeEmployeeTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating jiraffe employee table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE JIRAFFE_EMPLOYEES " +
					"(NAME VARCHAR(255) not NULL, " +
					" LEVEL VARCHAR(255), " + 
					" ADMIN_POINTS INTEGER, " + 
					" USER_POINTS INTEGER, " +
					" TESTER_POINTS INTEGER, " + 
					" DEVELOPER_POINTS INTEGER, " +
					" TOTAL_POINTS INTEGER, " + 
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( NAME ))";

		stmt.executeUpdate(sql);
		System.out.println("Created jiraffe employee table in given database...");
	}


	public void createEmployeeTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating onejira employee table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE ONEJIRA_EMPLOYEES (" +
					" NAME VARCHAR(255) not NULL, " +
					" USERNAME VARCHAR(255) not NULL, " + 
					" TEAM VARCHAR(255) not NULL, " + 
					" EMAIL VARCHAR(255), " +
					" DEPARTMENT VARCHAR(255), " +
					" MANAGER VARCHAR(255), " + 
					" CITY VARCHAR(255), " + 
					" STATE VARCHAR(255), " +
					" TELEPHONE_NUMBER VARCHAR(255), " +
					" COMPANY VARCHAR(255), " + 						
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( NAME, TEAM ))";

		stmt.executeUpdate(sql);
		System.out.println("Created onejira employee table in given database...");
	}
}
