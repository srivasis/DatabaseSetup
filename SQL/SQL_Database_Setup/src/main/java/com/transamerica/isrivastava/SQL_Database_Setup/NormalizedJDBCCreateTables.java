package com.transamerica.isrivastava.SQL_Database_Setup;
import java.sql.*;

public class NormalizedJDBCCreateTables {

	public NormalizedJDBCCreateTables(String JDBC_DRIVER, String DB_URL, String USER, String PASS) {
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
			createTicketTable(conn, stmt);
			createTicketToTeamTable(conn, stmt);
			createTicketToSprintTable(conn, stmt);
			createTicketToEmployeeTable(conn, stmt);
			createTeamTable(conn, stmt);
			createTeamToLeaderTable(conn, stmt);
			createSprintTable(conn, stmt);
			createEmployeeTable(conn, stmt);
			createEmployeeToDepartmentTable(conn, stmt);
			createJiraffeEmployeeTable(conn, stmt);
			createJiraffeTeamTable(conn, stmt);
			
			
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
		System.out.println("Creating ticket table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE TICKETS " + 
					"(ID VARCHAR(255) not NULL, " + 
					" TICKET_KEY VARCHAR(255) not NULL, " +
					" CREATION_DATE VARCHAR(255), "  +
					" RESOLUTION_DATE VARCHAR(255), " +
					" STORY_POINTS INTEGER, " +
					" STATUS VARCHAR(255) not NULL, " +
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( TICKET_KEY, STATUS ))";

		stmt.executeUpdate(sql);
		System.out.println("Created ticket table in given database...");
	}
	
	private void createTicketToTeamTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating ticket to team table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE TICKET_TO_TEAM " + 
					"(" + 
					" TICKET_KEY VARCHAR(255) not NULL, " +
					" TICKET_STATUS VARCHAR(255) not NULL, " +
					" TEAM_ID VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( TICKET_KEY, TICKET_STATUS ))";

		stmt.executeUpdate(sql);
		System.out.println("Created ticket to team table in given database...");
	}
	
	private void createTicketToSprintTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating ticket to sprint table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE TICKET_TO_SPRINT " + 
					"(" + 
					" TICKET_KEY VARCHAR(255) not NULL, " +
					" TICKET_STATUS VARCHAR(255) not NULL, " +
					" SPRINT_NAME VARCHAR(255) not NULL, " +
					" TEAM_ID VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( TICKET_KEY, TICKET_STATUS ))";

		stmt.executeUpdate(sql);
		System.out.println("Created ticket to sprint table in given database...");
	}
	
	private void createTicketToEmployeeTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating ticket to employee table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE TICKET_TO_EMPLOYEE " + 
					"(" + 
					" TICKET_KEY VARCHAR(255) not NULL, " +
					" TICKET_STATUS VARCHAR(255) not NULL, " +
					" EMPLOYEE_NAME VARCHAR(255) not NULL, " +
					" EMPOYEE_TEAM_ID VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( TICKET_KEY, TICKET_STATUS ))";

		stmt.executeUpdate(sql);
		System.out.println("Created ticket to employee table in given database...");
	}

	private void createTeamTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating team table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE TEAMS " +
					"(ID VARCHAR(255) not NULL, " +
					" TEAM_KEY VARCHAR(255) not NULL, " + 
					" NAME VARCHAR(255) not NULL, " + 
					" CHANNEL VARCHAR(255), " +
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( ID ))";

		stmt.executeUpdate(sql);
		System.out.println("Created team table in given database...");
	}
	
	private void createTeamToLeaderTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating team to leader table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE TEAM_TO_LEADER " +
					"(TEAM_ID VARCHAR(255) not NULL, " +
					" TEAM_LEADER VARCHAR(255) not NULL, " + 
					" PRIMARY KEY ( TEAM_ID ))";

		stmt.executeUpdate(sql);
		System.out.println("Created team to leader table in given database...");
	}

	private void createSprintTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating sprint table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE SPRINTS " + 
					"(NAME VARCHAR(255) not NULL, " +
					" TEAM VARCHAR(255) not NULL, " + 
					" START_DATE VARCHAR(255), " + 
					" END_DATE VARCHAR(255), " +
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( NAME, TEAM ))";

		stmt.executeUpdate(sql);
		System.out.println("Created sprint table in given database...");
	}
	
	public void createEmployeeTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating employee table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE EMPLOYEES (" +
					" NAME VARCHAR(255) not NULL, " +
					" USERNAME VARCHAR(255) not NULL, " + 
					" TEAM VARCHAR(255) not NULL, " + 
					" EMAIL VARCHAR(255), " +
					" MANAGER VARCHAR(255), " + 
					" CITY VARCHAR(255), " + 
					" STATE VARCHAR(255), " +
					" TELEPHONE_NUMBER VARCHAR(255), " + 						
					" UNC_DATE VARCHAR(255) not NULL, " +
					" DB_USERNAME VARCHAR(255) not NULL, " +
					" DB_DATE VARCHAR(255) not NULL, " +
					" PRIMARY KEY ( NAME, TEAM ))";

		stmt.executeUpdate(sql);
		System.out.println("Created employee table in given database...");
	}
	
	public void createEmployeeToDepartmentTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating employee to department table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE EMPLOYEE_TO_DEPARTMENT (" +
					" NAME VARCHAR(255) not NULL, " +
					" TEAM VARCHAR(255) not NULL, " +
					" DEPARTMENT VARCHAR(255), " +
					" COMPANY VARCHAR(255), " +
					" PRIMARY KEY ( NAME, TEAM ))";

		stmt.executeUpdate(sql);
		System.out.println("Created employee to department table in given database...");
	}

	private void createJiraffeTeamTable(Connection conn, Statement stmt) throws SQLException {
		System.out.println("Creating jiraffe team table in given database...");
		stmt = conn.createStatement();

		String sql = "CREATE TABLE JIRAFFE_TEAMS " +
					"(ID VARCHAR(255) not NULL, " +
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
}
