package com.transamerica.isrivastava.SQL_Database_Setup;

public class Sprint {
	private String Name;
	private String TeamName;
	private String StartDate;
	private String EndDate;
	private String Date;
	
	private String DBUsername;
	private String DBDate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((TeamName == null) ? 0 : TeamName.hashCode());
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
		Sprint other = (Sprint) obj;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (TeamName == null) {
			if (other.TeamName != null)
				return false;
		} else if (!TeamName.equals(other.TeamName))
			return false;
		return true;
	}

	public Sprint() {
		this.Name = "";
		this.TeamName = "";
		this.StartDate = "";
		this.EndDate = "";
		this.DBUsername = "";
		this.DBDate = "";
	}
	
	public String toString() {
		return "Name: " + this.Name + " TeamName: " + this.TeamName + " Start Date: " + this.StartDate + " End Date: " +
				this.EndDate + " Date: "+ this.Date + " DBUsername: " + this.DBUsername + " DBDate: " + this.DBDate;
	}
	
	public void setName (String t) { this.Name = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setTeamName (String t) { this.TeamName = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setStartDate (String t) { this.StartDate = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setEndDate (String t) { this.EndDate = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setDate (String t) { this.Date = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setDBUsername (String t) { this.DBUsername = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setDBDate (String t) { this.DBDate = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	
	public String getName () { return this.Name; }
	public String getTeamName () { return this.TeamName; }
	public String getStartDate () { return this.StartDate; }
	public String getEndDate () { return this.EndDate; }
	public String getDate () { return this.Date; }
	public String getDBUsername () { return this.DBUsername; }
	public String getDBDate () { return this.DBDate; }
}
