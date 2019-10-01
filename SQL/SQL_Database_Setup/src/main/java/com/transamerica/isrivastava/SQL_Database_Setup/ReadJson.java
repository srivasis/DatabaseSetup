package com.transamerica.isrivastava.SQL_Database_Setup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJson {
	private ArrayList<String> filePaths;
	private ArrayList<String> jiraffeFilePaths;

	private String DBUsername;
	private String DBDate;

	private HashSet<Team> TeamTable;
	private HashSet<Ticket> TicketTable;
	private HashSet<Sprint> SprintTable;

	private HashSet<JiraffeEmployee> JiraffeEmployeeTable;
	private HashSet<JiraffeTeam> JiraffeTeamTable;
	private HashSet<Employee> EmployeeTable;

	public ReadJson(String DBUsername, String onejiraPath, String jiraffePath, String onejiraStartDateFP,
			String jirStartDateFP) {
		FilePaths fps = new FilePaths(onejiraPath, onejiraStartDateFP);
		JiraffeFilePaths jfps = new JiraffeFilePaths(jiraffePath, jirStartDateFP);

		this.filePaths = fps.getFilePaths();
		this.jiraffeFilePaths = jfps.getFilePaths();

		this.DBUsername = DBUsername;
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		this.DBDate = ts.toString().split(":")[0];

		this.TeamTable = new HashSet<>();
		this.TicketTable = new HashSet<>();
		this.SprintTable = new HashSet<>();

		this.JiraffeEmployeeTable = new HashSet<>();
		this.JiraffeTeamTable = new HashSet<>();
		this.EmployeeTable = new HashSet<>();

		jiraffeTableData();
		onejiraTableData();
	}

	public HashSet<Team> getTeamTable() {
		return this.TeamTable;
	}

	public HashSet<Ticket> getTicketTable() {
		return this.TicketTable;
	}

	public HashSet<Sprint> getSprintTable() {
		return this.SprintTable;
	}

	public HashSet<JiraffeEmployee> getJiraffeEmployeeTable() {
		return this.JiraffeEmployeeTable;
	}

	public HashSet<JiraffeTeam> getJiraffeTeamTable() {
		return this.JiraffeTeamTable;
	}

	public HashSet<Employee> getEmployeeTable() {
		return this.EmployeeTable;
	}

	public void jiraffeTableData() {
		for (String fp : this.jiraffeFilePaths) {
			System.out.println("Reading Jiraffe "+ fp);
			JSONParser jsonParser = new JSONParser();
			try (FileReader reader = new FileReader(fp)) {
				String date = fp.split("\\\\")[8];
				JSONObject json = (JSONObject) jsonParser.parse(reader);
				JSONArray teams = (JSONArray) json.get("TeamList");

				for (int i = 0; i < teams.size(); i++) {
					JSONObject team = (JSONObject) teams.get(i);
					String teamID = "Prjct" + Long.toString((long) team.get("TeamID"));
					String teamKey = (String) team.get("TeamNameShort");
					String teamName = (String) team.get("TeamNameLong");
					int teamPoints = 0;

					JSONArray users = (JSONArray) team.get("UserList");
					for (int j = 0; j < users.size(); j++) {
						JSONObject employee = (JSONObject) users.get(j);
						String level = (String) employee.get("Level");
						int devPoints = (int) ((long) employee.get("DeveloperPts"));
						int userPoints = (int) ((long) employee.get("UserPts"));
						int adminPoints = (int) ((long) employee.get("AdminPts"));
						int testerPoints = (int) ((long) employee.get("TesterPts"));
						int totalPoints = (int) ((long) employee.get("TotalPts"));
						JSONObject employeeInfo = (JSONObject) employee.get("UserADEntity");
						String employeeName = (String) employeeInfo.get("NameLastFirst");
						if (employeeName.contains(",")) {
							employeeName = employeeName.split(", ")[1] + " " + employeeName.split(", ")[0];
							employeeName = (employeeName.contains("'")) ? employeeName.replace("'", "") : employeeName;
						}
						String email = (String) employeeInfo.get("EmailAddress");
						String username = (String) employeeInfo.get("ActiveDirectoryUserName");
						String department = (String) employeeInfo.get("DepartmentName");
						String companyName = (String) employeeInfo.get("CompanyName");
						String telephoneNumber = (String) employeeInfo.get("TelephoneNumber");
						String manager = (String) employeeInfo.get("Manager");
						// (((String)employeeInfo.get("Manager")).contains(",")) ? ((String)
						// employeeInfo.get("Manager")).split(",")[1].substring(1) + " " + ((String)
						// employeeInfo.get("Manager")).split("=")[1].split("\\\\")[0] : "";
						String city = (String) employeeInfo.get("City");
						String state = (String) employeeInfo.get("State");

						JiraffeEmployee je = new JiraffeEmployee();
						je.setEmployeeName(employeeName);
						je.setLevel(level);
						je.setDeveloperPoints(devPoints);
						je.setUserPoints(userPoints);
						je.setAdminPoints(adminPoints);
						je.setTesterPoints(testerPoints);
						je.setTotalPoints(totalPoints);
						je.setDate(date);
						je.setDBUsername(this.DBUsername);
						je.setDBDate(this.DBDate);
						if (this.JiraffeEmployeeTable.contains(je)) {
							for (JiraffeEmployee x : this.JiraffeEmployeeTable) {
								if (x.equals(je)) {
									if (x.getTotalPoints() < je.getTotalPoints()) {
										this.JiraffeEmployeeTable.remove(x);
										this.JiraffeEmployeeTable.add(je);
									}
									break;
								}
							}
						} else {
							this.JiraffeEmployeeTable.add(je);
						}

						Employee e = new Employee();
						e.setCity(city);
						e.setDepartmentName(department);
						e.setDisplayName(employeeName);
						e.setEmail(email);
						e.setManager(manager);
						e.setState(state);
						e.setTeamName(teamName);
						e.setTelephoneNumber(telephoneNumber);
						e.setUsername(username);
						e.setCompany(companyName);
						e.setDate(date);
						e.setDBUsername(this.DBUsername);
						e.setDBDate(this.DBDate);
						this.EmployeeTable.add(e);

						teamPoints += totalPoints;
					}

					JiraffeTeam jt = new JiraffeTeam();
					jt.setDate(date);
					jt.setID(teamID);
					jt.setKey(teamKey);
					jt.setName(teamName);
					jt.setPoints(teamPoints);
					jt.setDBUsername(this.DBUsername);
					jt.setDBDate(this.DBDate);
					if (this.JiraffeTeamTable.contains(jt)) {
						for (JiraffeTeam j : this.JiraffeTeamTable) {
							if (j.equals(jt)) {
								if (j.getPoints() < jt.getPoints()) {
									this.JiraffeTeamTable.remove(j);
									this.JiraffeTeamTable.add(jt);
								}
								break;
							}
						}

					} else {
						this.JiraffeTeamTable.add(jt);
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public void onejiraTableData() {
		for (String fp : this.filePaths) {
			if (!fp.contains("Projects")) {
				System.out.println("Reading OneJira "+ fp);
				try (FileReader reader = new FileReader(fp)) {
					String date = fp.split("\\\\")[8];
					String team = fp.split("\\\\")[9];
					JSONParser jsonParser = new JSONParser();
					JSONObject json = (JSONObject) jsonParser.parse(reader);

					if (!fp.contains("userstory")) {
						String teamID = "Prjct" + (String) json.get("id");
						String teamKey = (String) json.get("key");
						String teamLead = ((String) ((JSONObject) json.get("lead")).get("displayName") != null
								&& ((String) ((JSONObject) json.get("lead")).get("displayName")).contains("'"))
										? ((String) ((JSONObject) json.get("lead")).get("displayName")).replace("'", "")
										: (String) ((JSONObject) json.get("lead")).get("displayName");
						String teamName = (String) json.get("name");
						String teamChannel = (String) json.get("projectTypeKey");

						Team t = new Team();
						t.setChannel(teamChannel);
						t.setDate(date);
						t.setID(teamID);
						t.setKey(teamKey);
						t.setLead(teamLead);
						t.setName(teamName);
						t.setDBUsername(this.DBUsername);
						t.setDBDate(this.DBDate);
						if(this.TeamTable.contains(t)) {
							for(Team tem : this.TeamTable) {
								if(compareDates(tem.getDate(), t.getDate())) {
									this.TeamTable.remove(tem);
									this.TeamTable.add(t);
								}
								break;
							}
						} else {
							this.TeamTable.add(t);	
						}
					} else {
						JSONArray tickets = (JSONArray) json.get("issues");
						for (int i = 0; i < tickets.size(); i++) {
							String Ticket_ID = (String) ((JSONObject) tickets.get(i)).get("id");
							String Ticket_Key = (String) ((JSONObject) tickets.get(i)).get("key");
							String Created_Date = ((String) ((JSONObject) ((JSONObject) tickets.get(i)).get("fields"))
									.get("created")) != null && (((String) ((JSONObject) ((JSONObject) tickets.get(i)).get("fields"))
									.get("created")).contains("T"))
											? ((String) ((JSONObject) ((JSONObject) tickets.get(i)).get("fields"))
													.get("created")).split("T")[0]
											: (String) ((JSONObject) ((JSONObject) tickets.get(i)).get("fields"))
													.get("created");
							String Resolution_Date = (((JSONObject) ((JSONObject) tickets.get(i)).get("fields"))
									.get("resolutiondate") != null
									&& ((String) ((JSONObject) ((JSONObject) tickets.get(i)).get("fields"))
											.get("resolutiondate")).contains("T"))
													? ((String) ((JSONObject) ((JSONObject) tickets.get(i))
															.get("fields")).get("resolutiondate")).split("T")[0]
													: (String) ((JSONObject) ((JSONObject) tickets.get(i))
															.get("fields")).get("resolutiondate");
							int Story_Points = 0;
							if (((JSONObject) ((JSONObject) tickets.get(i)).get("fields"))
									.get("customfield_10006") != null) {
								Story_Points = (int) ((double) ((JSONObject) ((JSONObject) tickets.get(i))
										.get("fields")).get("customfield_10006"));
							}
							String Status = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) tickets
									.get(i)).get("fields")).get("status")).get("statusCategory")).get("name");
							String Assignee = "";
							if (((JSONObject) ((JSONObject) tickets.get(i)).get("fields")).get("assignee") != null) {
								Assignee = (String) ((JSONObject) ((JSONObject) ((JSONObject) tickets.get(i))
										.get("fields")).get("assignee")).get("displayName");
								if (Assignee.contains(",")) {
									Assignee = Assignee.split(", ")[1] + " " + Assignee.split(", ")[0];
									Assignee = (Assignee.contains("'")) ? Assignee.replace("'", "") : Assignee;
								}
							}
							String Sprint_Name = "";
							if (((JSONObject) ((JSONObject) tickets.get(i)).get("fields"))
									.get("customfield_10004") != null) {
								Sprint_Name = ((String) ((JSONArray) ((JSONObject) ((JSONObject) tickets.get(i))
										.get("fields")).get("customfield_10004")).get(0)).split(",")[3].split("=")[1];
								String Sprint_StartDate = ((String) ((JSONArray) ((JSONObject) ((JSONObject) tickets
										.get(i)).get("fields")).get("customfield_10004")).get(0)).split(",")[4]
												.split("=")[1];
								String Sprint_EndDate = ((String) ((JSONArray) ((JSONObject) ((JSONObject) tickets
										.get(i)).get("fields")).get("customfield_10004")).get(0)).split(",")[5]
												.split("=")[1];

								Sprint s = new Sprint();
								s.setDate(date);
								s.setDBDate(this.DBDate);
								s.setDBUsername(this.DBUsername);
								s.setEndDate(Sprint_EndDate);
								s.setName(Sprint_Name);
								s.setStartDate(Sprint_StartDate);
								s.setTeamName(team);
								if (!s.getStartDate().equals("<null>") && !s.getEndDate().equals("<null>")) {
									this.SprintTable.add(s);
								}
							}

							Ticket t = new Ticket();
							t.setAssignee(Assignee);
							t.setCreatedDate(Created_Date);
							t.setDate(date);
							t.setResolutionDate(Resolution_Date);
							t.setSprintName(Sprint_Name);
							t.setStatus(Status);
							t.setStoryPoints(Story_Points);
							t.setTeamName(team);
							t.setTicketID(Ticket_ID);
							t.setTicketKey(Ticket_Key);
							t.setDBUsername(this.DBUsername);
							t.setDBDate(this.DBDate);
							
							if(this.TicketTable.contains(t)) {
								for(Ticket tic : this.TicketTable) {
									if(tic.equals(t)) {
										if(compareDates(tic.getDate(), t.getDate())) {
											this.TicketTable.add(t);
										}
										break;
									}
								}
							}else {
								this.TicketTable.add(t);
							}
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (org.json.simple.parser.ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public boolean compareDates(String d1, String d2) {
		//d1 < d2 -> true
		if(Integer.parseInt(d1.split("-")[2]) < Integer.parseInt(d2.split("-")[2])) {
			return true;
		}
		else if(Integer.parseInt(d1.split("-")[1]) < Integer.parseInt(d2.split("-")[1])) {
			return true;
		}
		else if(Integer.parseInt(d1.split("-")[0]) < Integer.parseInt(d2.split("-")[0])) {
			return true;
		}
		return false;
	}
}
