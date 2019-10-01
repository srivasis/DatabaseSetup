package com.transamerica.isrivastava.SQL_Database_Setup;

public class JiraffeEmployee {
	private String Employee_Name;
	private String Level;
	private int Admin_Points;
	private int User_Points;
	private int Tester_Points;
	private int Developer_Points;
	private int Total_Points;
	private String Date;

	private String DBUsername;
	private String DBDate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Employee_Name == null) ? 0 : Employee_Name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JiraffeEmployee other = (JiraffeEmployee) obj;
		if (Employee_Name == null) {
			if (other.Employee_Name != null)
				return false;
		} else if (!Employee_Name.equals(other.Employee_Name))
			return false;
		return true;
	}

	public JiraffeEmployee() {
		this.Employee_Name = "";
		this.Level = "";
		this.Admin_Points = 0;
		this.User_Points = 0;
		this.Tester_Points = 0;
		this.Developer_Points = 0;
		this.Total_Points = 0;
		this.Date = "";
		this.DBUsername = "";
		this.DBDate = "";
	}

	public String toString() {
		return "Employee_Name: " + this.Employee_Name + " Level: " + this.Level + " Total_Points: " + this.Total_Points
				+ " Date: " + this.Date + " DBUsername: " + this.DBUsername + " DBDate: " + this.DBDate;
	}

	void setEmployeeName(String t) {
		this.Employee_Name = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;
	}

	void setLevel(String t) {
		this.Level = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;
	}

	void setAdminPoints(int points) {
		this.Admin_Points = points;
	}

	void setUserPoints(int points) {
		this.User_Points = points;
	}

	void setTesterPoints(int points) {
		this.Tester_Points = points;
	}

	void setDeveloperPoints(int points) {
		this.Developer_Points = points;
	}

	void setTotalPoints(int points) {
		this.Total_Points = points;
	}

	void setDate(String t) {
		this.Date = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;
	}

	public void setDBUsername(String t) {
		this.DBUsername = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;
	}

	public void setDBDate(String t) {
		this.DBDate = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;
	}

	String getEmployeeName() {
		return this.Employee_Name;
	}

	String getLevel() {
		return this.Level;
	}

	int getAdminPoints() {
		return this.Admin_Points;
	}

	int getUserPoints() {
		return this.User_Points;
	}

	int getTesterPoints() {
		return this.Tester_Points;
	}

	int getDeveloperPoints() {
		return this.Developer_Points;
	}

	int getTotalPoints() {
		return this.Total_Points;
	}

	String getDate() {
		return this.Date;
	}

	public String getDBUsername() {
		return this.DBUsername;
	}

	public String getDBDate() {
		return this.DBDate;
	}
}
