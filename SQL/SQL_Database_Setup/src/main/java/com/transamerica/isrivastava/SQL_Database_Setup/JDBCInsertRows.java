package com.transamerica.isrivastava.SQL_Database_Setup;
import java.sql.*;
import java.util.HashSet;

public class JDBCInsertRows {

	public JDBCInsertRows(ReadJson rj, String JDBC_DRIVER, String DB_URL, String USER, String PASS) {
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
			insertRecordsIntoOneJiraTicketsTable(rj.getTicketTable(), conn, stmt);
			insertRecordsIntoOneJiraTeamsTable(rj.getTeamTable(), conn, stmt);
			insertRecordsIntoOneJiraSprintsTable(rj.getSprintTable(), conn, stmt);
			insertRecordsIntoJiraffeTeamsTable(rj.getJiraffeTeamTable(), conn, stmt);
			insertRecordsIntoJiraffeEmployees(rj.getJiraffeEmployeeTable(), conn, stmt);
			insertRecordsIntoOneJiraEmployeesTable(rj.getEmployeeTable(), conn, stmt);

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
		System.out.println("----------------DONE INSERTING RECORDS INTO TABLES----------------");
	}
	
	public void insertRecordsIntoOneJiraTicketsTable(HashSet<Ticket> tickets, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the OneJira Tickets table...");
		stmt = conn.createStatement();

		for(Ticket e : tickets) {
			String sql = "INSERT INTO ONEJIRA_TICKETS (ID ,TICKET_KEY ,CREATION_DATE ,RESOLUTION_DATE ,STORY_POINTS ,STATUS ,ASSIGNEE ,TEAM ,SPRINT ,UNC_DATE ,DB_USERNAME ,DB_DATE) " + 
						"VALUES ('"+ 
						e.getTicketID() + "', '" +
						e.getTicketKey() + "', '" +
						e.getCreatedDate() + "', '" +
						e.getResolutionDate() + "', " +
						e.getStoryPoints() + ", '" +
						e.getStatus() + "', '" +
						e.getAssignee() + "', '" +
						e.getTeamName() + "', '" +
						e.getSprintName() + "', '" +
						e.getDate() + "', '" +
						e.getDBUsername() + "', '" +
						e.getDBDate() +
						"')";	
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the OneJira Tickets table...");
	}
	
	public void insertRecordsIntoOneJiraTeamsTable(HashSet<Team> teams, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the OneJira Teams table...");
		stmt = conn.createStatement();

		for(Team e : teams) {
			String sql = "INSERT INTO ONEJIRA_TEAMS ( ID ,TEAM_KEY ,NAME ,LEADER ,CHANNEL ,UNC_DATE ,DB_USERNAME ,DB_DATE) " + 
						"VALUES ('"+ 
						e.getID() + "', '" +
						e.getKey() + "', '" +
						e.getName() + "', '" +
						e.getLead() + "', '" +
						e.getChannel() + "', '" +
						e.getDate() + "', '" +
						e.getDBUsername() + "', '" +
						e.getDBDate() +
						"')";
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the OneJira Teams table...");
	}
	
	public void insertRecordsIntoOneJiraSprintsTable(HashSet<Sprint> sprints, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the OneJira Sprints table...");
		stmt = conn.createStatement();

		for(Sprint e : sprints) {
			String sql = "INSERT INTO ONEJIRA_SPRINTS ( NAME ,TEAM ,START_DATE ,END_DATE ,UNC_DATE ,DB_USERNAME , DB_DATE) " + 
						"VALUES ('"+ 
						e.getName() + "', '" +
						e.getTeamName() + "', '" +
						e.getStartDate() + "', '" +
						e.getEndDate() + "', '" +
						e.getDate() + "', '" +
						e.getDBUsername() + "', '" +
						e.getDBDate() +
						"')";
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the OneJira Sprints table...");
	}
	
	public void insertRecordsIntoJiraffeTeamsTable(HashSet<JiraffeTeam> teams, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the Jiraffe Teams table...");
		stmt = conn.createStatement();

		for(JiraffeTeam e : teams) {
			String sql = "INSERT INTO JIRAFFE_TEAMS (ID ,TEAM_KEY ,NAME ,POINTS ,UNC_DATE ,DB_USERNAME ,DB_DATE) " + 
						"VALUES ('"+ 
						e.getID() + "', '" +
						e.getKey() + "', '" +
						e.getName() + "', " +
						e.getPoints() + ", '" +
						e.getDate() + "', '" +
						e.getDBUsername() + "', '" +
						e.getDBDate() +
						"')";
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Jiraffe Teams table...");
	}
	
	public void insertRecordsIntoJiraffeEmployees(HashSet<JiraffeEmployee> employees, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the Jiraffe Employees table...");
		stmt = conn.createStatement();

		for(JiraffeEmployee e : employees) {
			String sql = "INSERT INTO JIRAFFE_EMPLOYEES (NAME ,LEVEL ,ADMIN_POINTS ,USER_POINTS ,TESTER_POINTS ,DEVELOPER_POINTS ,TOTAL_POINTS ,UNC_DATE ,DB_USERNAME ,DB_DATE) " + 
						"VALUES ('"+ 
						e.getEmployeeName() + "', '" +
						e.getLevel() + "', " +
						e.getAdminPoints() + ", " +
						e.getUserPoints() + ", " +
						e.getTesterPoints() + ", " +
						e.getDeveloperPoints() + ", " +
						e.getTotalPoints() + ", '" +
						e.getDate() + "', '" +
						e.getDBUsername() + "', '" +
						e.getDBDate() +
						"')";
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Jiraffe Employees table...");
	}
	
	public void insertRecordsIntoOneJiraEmployeesTable(HashSet<Employee> employees, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the OneJira Employees table...");
		stmt = conn.createStatement();

		for(Employee e : employees) {
			String sql = "INSERT INTO ONEJIRA_EMPLOYEES (NAME ,USERNAME ,TEAM ,EMAIL ,DEPARTMENT ,MANAGER ,CITY ,STATE ,TELEPHONE_NUMBER ,COMPANY ,UNC_DATE ,DB_USERNAME ,DB_DATE) " + 
						"VALUES ('"+ 
						e.getDisplayName() + "', '" +
						e.getUsername() + "', '" +
						e.getTeamName() + "', '" +
						e.getEmail() + "', '" +
						e.getDepartmentName() + "', '" +
						e.getManager() + "', '" +
						e.getCity() + "', '" +
						e.getState() + "', '" +
						e.getTelephoneNumber() + "', '" +
						e.getCompany() + "', '" +
						e.getDate() + "', '" +
						e.getDBUsername() + "', '" +
						e.getDBDate() +
						"')";
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the OneJira Employees table...");
	}

}
