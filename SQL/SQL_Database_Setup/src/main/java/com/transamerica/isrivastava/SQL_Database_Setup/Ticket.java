package com.transamerica.isrivastava.SQL_Database_Setup;

public class Ticket {
	private String Ticket_ID;
	private String Ticket_Key;
	private String Created_Date;
	private String Resolution_Date;
	private int Story_Points;
	private String Status;
	private String Assignee;
	private String Team_Name;
	private String Sprint_Name;
	private String Date;
	
	private String DBUsername;
	private String DBDate;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Status == null) ? 0 : Status.hashCode());
		result = prime * result + ((Team_Name == null) ? 0 : Team_Name.hashCode());
		result = prime * result + ((Ticket_ID == null) ? 0 : Ticket_ID.hashCode());
		result = prime * result + ((Ticket_Key == null) ? 0 : Ticket_Key.hashCode());
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
		Ticket other = (Ticket) obj;
		if (Status == null) {
			if (other.Status != null)
				return false;
		} else if (!Status.equals(other.Status))
			return false;
		if (Team_Name == null) {
			if (other.Team_Name != null)
				return false;
		} else if (!Team_Name.equals(other.Team_Name))
			return false;
		if (Ticket_ID == null) {
			if (other.Ticket_ID != null)
				return false;
		} else if (!Ticket_ID.equals(other.Ticket_ID))
			return false;
		if (Ticket_Key == null) {
			if (other.Ticket_Key != null)
				return false;
		} else if (!Ticket_Key.equals(other.Ticket_Key))
			return false;
		return true;
	}

	public Ticket() {
		this.Ticket_ID = "";
		this.Ticket_Key = "";
		this.Created_Date = "";
		this.Resolution_Date = "";
		this.Story_Points = 0;
		this.Status = "";
		this.Assignee = "";
		this.Team_Name = "";
		this.Sprint_Name = "";
		this.Date = "";
		this.DBUsername = "";
		this.DBDate = "";
	}
	
	public String toString() {
		return "Ticket ID: " + this.Ticket_ID +
				" Ticket Key: " + this.Ticket_Key +
				" Created Date: " + this.Created_Date +
				" Resolution Date: " + this.Resolution_Date +
				" Story Points " + this.Story_Points +
				" Status: " + this.Status + 
				" Assignee: " + this.Assignee + 
				" Team Name: " + this.Team_Name + 
				" Sprint Name: " + this.Sprint_Name +
				" Date: " + this.Date +
				" DBUsername: " + this.DBUsername +
				" DBDate: " + this.DBDate;
	}
	
	public void setTicketID (String t) { this.Ticket_ID = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setTicketKey (String t) { this.Ticket_Key = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setCreatedDate (String t) { this.Created_Date = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setResolutionDate (String t) { this.Resolution_Date = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setStoryPoints (int t) { this.Story_Points = t; }
	public void setStatus (String t) { this.Status = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setAssignee (String t) { this.Assignee = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setTeamName (String t) { this.Team_Name = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setSprintName (String t) { this.Sprint_Name = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setDate (String t) { this.Date = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setDBUsername (String t) { this.DBUsername = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setDBDate (String t) { this.DBDate = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	
	public String getTicketID () { return this.Ticket_ID; }
	public String getTicketKey () { return this.Ticket_Key; }
	public String getCreatedDate () { return this.Created_Date; }
	public String getResolutionDate () { return this.Resolution_Date; }
	public int getStoryPoints () { return this.Story_Points; }
	public String getStatus () { return this.Status; }
	public String getAssignee () { return this.Assignee; }
	public String getTeamName () { return this.Team_Name; }
	public String getSprintName () { return this.Sprint_Name; }
	public String getDate () { return this.Date; }
	public String getDBUsername () { return this.DBUsername; }
	public String getDBDate () { return this.DBDate; }
}
