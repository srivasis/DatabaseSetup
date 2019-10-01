package com.transamerica.isrivastava.SQL_Database_Setup;

public class Team {
	private String ID;
	private String Key;
	private String Name;
	private String Lead;
	private String Channel;
	private String Date;
	
	private String DBUsername;
	private String DBDate;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
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
		Team other = (Team) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	public Team() {
		this.ID = "";
		this.Key = "";
		this.Name = "";
		this.Lead = "";
		this.Channel = "";
		this.Date = "";
		this.DBUsername = "";
		this.DBDate = "";
	}
	
	public String toString() {
		return "ID: " + this.ID + " Key: " + this.Key + " Name: " + this.Name + " Leader: " + this.Lead 
				+ " Channel: " + this.Channel + " Date: " + this.Date + " DBUsername: " + this.DBUsername + " DBDate: " + this.DBDate;
	}
	
	public void setID(String t) { this.ID = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;; }
	public void setKey(String t) { this.Key = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;; }
	public void setName(String t) { this.Name = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setLead(String t) { this.Lead = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setChannel(String t) { this.Channel = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;; }
	public void setDate(String t) { this.Date = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;; }
	public void setDBUsername (String t) { this.DBUsername = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;}
	public void setDBDate (String t) { this.DBDate = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;}
	
	public String getID() { return this.ID; };
	public String getKey() { return this.Key; };
	public String getName() { return this.Name; };
	public String getLead() { return this.Lead; };
	public String getChannel() { return this.Channel; };
	public String getDate() { return this.Date; }
	public String getDBUsername () { return this.DBUsername; }
	public String getDBDate () { return this.DBDate; }
}
