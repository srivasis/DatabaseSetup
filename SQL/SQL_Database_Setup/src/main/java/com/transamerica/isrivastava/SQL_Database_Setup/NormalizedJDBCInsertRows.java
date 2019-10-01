package com.transamerica.isrivastava.SQL_Database_Setup;
import java.sql.*;
import java.util.HashSet;

public class NormalizedJDBCInsertRows {

	public NormalizedJDBCInsertRows(ReadJson rj, String JDBC_DRIVER, String DB_URL, String USER, String PASS) {
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
			insertIntoTicketTable(rj.getTicketTable(), conn, stmt);
			insertIntoTicketToTeamTable(rj.getTicketTable(), conn, stmt);
			insertIntoTicketToSprintTable(rj.getTicketTable(), conn, stmt);
			insertIntoTicketToEmployeeTable(rj.getTicketTable(), conn, stmt);
			insertIntoTeamTable(rj.getTeamTable(), conn, stmt);
			insertIntoTeamToLeaderTable(rj.getTeamTable(), conn, stmt);
			insertIntoSprintTable(rj.getSprintTable(), conn, stmt);
			insertIntoEmployeeTable(rj.getEmployeeTable(), conn, stmt);
			insertIntoEmployeeToDepartmentTable(rj.getEmployeeTable(), conn, stmt);
			insertIntoJiraffeEmployeeTable(rj.getJiraffeEmployeeTable(), conn, stmt);
			insertIntoJiraffeTeamTable(rj.getJiraffeTeamTable(), conn, stmt);
			
			
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
	
	private void insertIntoTicketTable(HashSet<Ticket> tickets, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the Tickets table...");
		stmt = conn.createStatement();

		for(Ticket e : tickets) {
			String sql = "INSERT INTO TICKETS (ID ,TICKET_KEY ,CREATION_DATE ,RESOLUTION_DATE ,STORY_POINTS ,STATUS ,UNC_DATE ,DB_USERNAME ,DB_DATE) " + 
						"VALUES ('"+ 
						e.getTicketID() + "', '" +
						e.getTicketKey() + "', '" +
						e.getCreatedDate() + "', '" +
						e.getResolutionDate() + "', " +
						e.getStoryPoints() + ", '" +
						e.getStatus() + "', '" +
						e.getDate() + "', '" +
						e.getDBUsername() + "', '" +
						e.getDBDate() +
						"')";	
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Tickets table...");
	}
	
	private void insertIntoTicketToTeamTable(HashSet<Ticket> tickets, Connection conn, Statement stmt) throws SQLException {		
		System.out.println("Inserting records into the Ticket to Team table...");
		stmt = conn.createStatement();

		for(Ticket e : tickets) {
			String sql = "INSERT INTO TICKET_TO_TEAM (TICKET_KEY ,TICKET_STATUS ,TEAM_ID) " + 
						"VALUES ('"+ 
						e.getTicketKey() + "', '" +
						e.getStatus() + "', '" +
						e.getTeamName() +
						"')";	
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Ticket To Team table...");
	}
	
	private void insertIntoTicketToSprintTable(HashSet<Ticket> tickets, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the Ticket to Sprint table...");
		stmt = conn.createStatement();

		for(Ticket e : tickets) {
			String sql = "INSERT INTO TICKET_TO_SPRINT (TICKET_KEY ,TICKET_STATUS ,SPRINT_NAME, TEAM_ID) " + 
						"VALUES ('"+ 
						e.getTicketKey() + "', '" +
						e.getStatus() + "', '" +
						e.getSprintName() + "', '" +
						e.getTeamName() +
						"')";	
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Ticket To Sprint table...");
	}
	
	private void insertIntoTicketToEmployeeTable(HashSet<Ticket> tickets, Connection conn, Statement stmt) throws SQLException {		
		System.out.println("Inserting records into the Ticket to Employee table...");
		stmt = conn.createStatement();

		for(Ticket e : tickets) {
			String sql = "INSERT INTO TICKET_TO_EMPLOYEE (TICKET_KEY ,TICKET_STATUS ,EMPLOYEE_NAME, EMPOYEE_TEAM_ID) " + 
						"VALUES ('"+ 
						e.getTicketKey() + "', '" +
						e.getStatus() + "', '" +
						e.getAssignee() + "', '" +
						e.getTeamName() +
						"')";	
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Ticket To Employee table...");
	}

	private void insertIntoTeamTable(HashSet<Team> teams, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the Teams table...");
		stmt = conn.createStatement();

		for(Team e : teams) {
			String sql = "INSERT INTO TEAMS ( ID ,TEAM_KEY ,NAME ,CHANNEL ,UNC_DATE ,DB_USERNAME ,DB_DATE) " + 
						"VALUES ('"+ 
						e.getID() + "', '" +
						e.getKey() + "', '" +
						e.getName() + "', '" +
						e.getChannel() + "', '" +
						e.getDate() + "', '" +
						e.getDBUsername() + "', '" +
						e.getDBDate() +
						"')";
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Teams table...");		
	}
	
	private void insertIntoTeamToLeaderTable(HashSet<Team> teams, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the Teams table...");
		stmt = conn.createStatement();

		for(Team e : teams) {
			String sql = "INSERT INTO TEAM_TO_LEADER ( TEAM_ID ,TEAM_LEADER ) " + 
						"VALUES ('"+ 
						e.getID() + "', '" +
						e.getLead() +
						"')";
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Teams table...");		
	}

	private void insertIntoSprintTable(HashSet<Sprint> sprints, Connection conn, Statement stmt) throws SQLException {	
		System.out.println("Inserting records into the Sprints table...");
		stmt = conn.createStatement();

		for(Sprint e : sprints) {
			String sql = "INSERT INTO SPRINTS ( NAME ,TEAM ,START_DATE ,END_DATE ,UNC_DATE ,DB_USERNAME , DB_DATE) " + 
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
	
		System.out.println("Inserted records into the Sprints table...");
	}
	
	public void insertIntoEmployeeTable(HashSet<Employee> employees, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the Employees table...");
		stmt = conn.createStatement();

		for(Employee e : employees) {
			String sql = "INSERT INTO EMPLOYEES (NAME ,USERNAME ,TEAM ,EMAIL ,MANAGER ,CITY ,STATE ,TELEPHONE_NUMBER ,UNC_DATE ,DB_USERNAME ,DB_DATE) " + 
						"VALUES ('"+ 
						e.getDisplayName() + "', '" +
						e.getUsername() + "', '" +
						e.getTeamName() + "', '" +
						e.getEmail() + "', '" +
						e.getManager() + "', '" +
						e.getCity() + "', '" +
						e.getState() + "', '" +
						e.getTelephoneNumber() + "', '" +
						e.getDate() + "', '" +
						e.getDBUsername() + "', '" +
						e.getDBDate() +
						"')";
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Employees table...");
	}
	
	public void insertIntoEmployeeToDepartmentTable(HashSet<Employee> employees, Connection conn, Statement stmt) throws SQLException {
		System.out.println("Inserting records into the Employee to Department table...");
		stmt = conn.createStatement();

		for(Employee e : employees) {
			String sql = "INSERT INTO EMPLOYEE_TO_DEPARTMENT (NAME ,TEAM, DEPARTMENT, COMPANY) " + 
						"VALUES ('"+ 
						e.getDisplayName() + "', '" +
						e.getTeamName() + "', '" +
						e.getDepartmentName() + "', '" +
						e.getCompany() +
						"')";
			stmt.executeUpdate(sql);
		}
	
		System.out.println("Inserted records into the Employee to Department table...");
	}
	
	
	private void insertIntoJiraffeTeamTable(HashSet<JiraffeTeam> teams, Connection conn, Statement stmt) throws SQLException {	
		System.out.println("Inserting records into the Jiraffe Teams table...");
		stmt = conn.createStatement();

		for(JiraffeTeam e : teams) {
			String sql = "INSERT INTO JIRAFFE_TEAMS (ID ,NAME ,POINTS ,UNC_DATE ,DB_USERNAME ,DB_DATE) " + 
						"VALUES ('"+ 
						e.getID() + "', '" +
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


	private void insertIntoJiraffeEmployeeTable(HashSet<JiraffeEmployee> employees, Connection conn, Statement stmt) throws SQLException {
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
}
