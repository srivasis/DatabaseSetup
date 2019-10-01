package com.transamerica.isrivastava.SQL_Database_Setup;

public class JiraffeTeam {
	private String ID;
	private String Key;
	private String Name;
	private int Points;
	private String Date;

	private String DBUsername;
	private String DBDate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((Key == null) ? 0 : Key.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
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
		JiraffeTeam other = (JiraffeTeam) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (Key == null) {
			if (other.Key != null)
				return false;
		} else if (!Key.equals(other.Key))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		return true;
	}

	public JiraffeTeam() {
		this.ID = "";
		this.Key = "";
		this.Name = "";
		this.Points = 0;
		this.Date = "";
		this.DBUsername = "";
		this.DBDate = "";
	}

	public String toString() {
		return "ID: " + this.ID + " Key: " + this.Key + " Name: " + this.Name + " Points: " + this.Points + " Date: "
				+ this.Date + " DBUsername: " + this.DBUsername + " DBDate: " + this.DBDate;
	}

	void setID(String t) {
		this.ID = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;
	}

	void setKey(String t) {
		this.Key = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;
	}

	void setName(String t) {
		this.Name = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;
	}

	void setPoints(int points) {
		this.Points = points;
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

	String getID() {
		return this.ID;
	}

	String getKey() {
		return this.Key;
	}

	String getName() {
		return this.Name;
	}

	int getPoints() {
		return this.Points;
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
