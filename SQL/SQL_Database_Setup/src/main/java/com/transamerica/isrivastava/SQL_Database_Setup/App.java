package com.transamerica.isrivastava.SQL_Database_Setup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Incorrect arguments passed. Please pass path to properties file as the only argument.");
			return;
		}

		File file = new File(args[0]);

		System.out.println("Properties: ");
		String st;
		String user = "";
		String OneJira_UNC = "";
		String Jiraffe_UNC = "";
		String OneJira_SD_File = "";
		String Jiraffe_SD_File = "";
		String SQL_Server_Driver = "";
		String SQL_Server_URL = "";
		String SQL_Server_Username = "";
		String SQL_Server_Password = "";
		String Create_Tables = "";
		String Insert_Rows = "";
		String SQL_Normalized_Server_URL = "";
		String Create_Normalized_Tables = "";
		String Insert_Rows_Normalized_Table = "";
		int i = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				if (st.contains("SQL_Server_Username")) {
					SQL_Server_Username = st.split(": ")[1];
					System.out.println("	SQL Server Username: " + SQL_Server_Username);
					i++;
					continue;
				} else if (st.contains("Username")) {
					user = st.split(": ")[1];
					System.out.println("	User: " + user);
					i++;
					continue;
				} else if (st.contains("OneJira_UNC_FilePath")) {
					OneJira_UNC = st.split(": ")[1];
					System.out.println("	OneJira UNC File Path: " + OneJira_UNC);
					i++;
					continue;
				} else if (st.contains("Jiraffe_UNC_FilePath")) {
					Jiraffe_UNC = st.split(": ")[1];
					System.out.println("	Jiraffe UNC File Path: " + Jiraffe_UNC);
					i++;
					continue;
				} else if (st.contains("OneJira_StartDate__FilePath")) {
					OneJira_SD_File = st.split(": ")[1];
					System.out.println("	OneJira Start Date File Path: " + OneJira_SD_File);
					i++;
					continue;
				} else if (st.contains("Jiraffe_StartDate_FilePath")) {
					Jiraffe_SD_File = st.split(": ")[1];
					System.out.println("	Jiraffe Start Date File Path: " + Jiraffe_SD_File);
					i++;
					continue;
				} else if (st.contains("SQL_Server_Driver")) {
					SQL_Server_Driver = st.split(": ")[1];
					System.out.println("	SQL Server Driver: " + SQL_Server_Driver);
					i++;
					continue;
				} else if (st.contains("SQL_Server_URL")) {
					SQL_Server_URL = st.split(": ")[1];
					System.out.println("	SQL Server URL: " + SQL_Server_URL);
					i++;
					continue;
				} else if (st.contains("SQL_Server_Password")) {
					SQL_Server_Password = st.split(": ")[1];
					//System.out.println("	SQL Server Password: " + SQL_Server_Password);
					i++;
					continue;
				} else if (st.contains("Create_Tables")) {
					Create_Tables = st.split(": ")[1];
					System.out.println("	Create_Tables: " + Create_Tables);
					i++;
					continue;
				} else if (st.contains("Insert_Rows_Normalized_Table")) {
					Insert_Rows_Normalized_Table = st.split(": ")[1];
					System.out.println("	Insert Rows Normalized Table: " + Insert_Rows_Normalized_Table);
					i++;
					continue;
				} else if (st.contains("Insert_Rows")) {
					Insert_Rows = st.split(": ")[1];
					System.out.println("	Insert Rows: " + Insert_Rows);
					i++;
					continue;
				} else if (st.contains("SQL_Normalized_Server_URL")) {
					SQL_Normalized_Server_URL = st.split(": ")[1];
					System.out.println("	SQL Normalized Server URL: " + SQL_Normalized_Server_URL);
					i++;
					continue;
				} else if (st.contains("Create_Normalized_Tables")) {
					Create_Normalized_Tables = st.split(": ")[1];
					System.out.println("	Create Normalized Tables: " + Create_Normalized_Tables);
					i++;
					continue;
				} 
			}
			br.close();
		} catch (IOException e) {
			System.out.println("ERROR >>>>> CANNOT READ PROPERTIES FILE");
			e.printStackTrace();
		}
		
		if (i != 14) {
			System.out.println("\nIncorrect Number of Properties Passed. ");
			return;
		}
		
		ReadJson rj = null;
		
		if(Create_Tables.equals("true")) {
			JDBCCreateTables create = new JDBCCreateTables(SQL_Server_Driver, SQL_Server_URL, SQL_Server_Username, SQL_Server_Password);
		}

		if(Insert_Rows.equals("true")) {
			rj = new ReadJson(user, OneJira_UNC, Jiraffe_UNC, OneJira_SD_File, Jiraffe_SD_File);
			JDBCInsertRows insert = new JDBCInsertRows(rj, SQL_Server_Driver, SQL_Server_URL, SQL_Server_Username, SQL_Server_Password);
		}
		
		if(Create_Normalized_Tables.equals("true")) {
			NormalizedJDBCCreateTables create = new NormalizedJDBCCreateTables(SQL_Server_Driver, SQL_Normalized_Server_URL, SQL_Server_Username, SQL_Server_Password);
		}

		if(Insert_Rows_Normalized_Table.equals("true")) {
			if(rj == null) {
				rj = new ReadJson(user, OneJira_UNC, Jiraffe_UNC, OneJira_SD_File, Jiraffe_SD_File);
			}
			NormalizedJDBCInsertRows insert = new NormalizedJDBCInsertRows(rj, SQL_Server_Driver, SQL_Normalized_Server_URL, SQL_Server_Username, SQL_Server_Password);
		}
	}
	
	
	public void printRows(ReadJson rj) {
		System.out.println(
				"Team Table =======================================================================================");
		for (Team t : rj.getTeamTable()) {
			System.out.println(t.toString());
		}
		System.out.println(
				"Employee Table ===================================================================================");
		for (Employee t : rj.getEmployeeTable()) {
			System.out.println(t.toString());
		}
		System.out.println(
				"Ticket Team Table ===============================================================================");
		for (Ticket t : rj.getTicketTable()) {
			System.out.println(t.toString());
		}
		System.out.println(
				"Sprint Team Table ===============================================================================");
		for (Sprint t : rj.getSprintTable()) {
			System.out.println(t.toString());
		}
		System.out.println(
				"Jiraffe Employee Table ===========================================================================");
		for (JiraffeEmployee t : rj.getJiraffeEmployeeTable()) {
			System.out.println(t.toString());
		}
		System.out.println(
				"Jiraffe Team Table ===============================================================================");
		for (JiraffeTeam t : rj.getJiraffeTeamTable()) {
			System.out.println(t.toString());
		}
	}
}
