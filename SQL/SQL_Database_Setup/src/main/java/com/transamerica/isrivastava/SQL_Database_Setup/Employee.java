package com.transamerica.isrivastava.SQL_Database_Setup;

public class Employee {
	
	private String Display_Name;
	private String Username;
	private String Team_Name;
	private String Email;
	private String Department_Name;
	private String Manager;
	private String City;
	private String State;
	private String TelephoneNumber;
	private String Company;
	private String Date;
	
	private String DBUsername;
	private String DBDate;
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Display_Name == null) ? 0 : Display_Name.hashCode());
		result = prime * result + ((Team_Name == null) ? 0 : Team_Name.hashCode());
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
		Employee other = (Employee) obj;
		if (Display_Name == null) {
			if (other.Display_Name != null)
				return false;
		} else if (!Display_Name.equals(other.Display_Name))
			return false;
		if (Team_Name == null) {
			if (other.Team_Name != null)
				return false;
		} else if (!Team_Name.equals(other.Team_Name))
			return false;
		return true;
	}

	public Employee() {
		this.Display_Name = "";
		this.Username = "";
		this.Team_Name = "";
		this.Email = "";
		this.Department_Name = "";
		this.Manager = "";
		this.City = "";
		this.State = "";
		this.TelephoneNumber = "";
		this.Company = "";
		this.Date = "";
		this.DBUsername = "";
		this.DBDate = "";
	}
	
	public String toString() {
		return "Display_Name: " + this.Display_Name + " Username: " + this.Username + " Team_Name: " + this.Team_Name +
				" Email: " + this.Email + " Department_Name: " + this.Department_Name + " Manager: " + this.Manager +
				" City: " + this.City + " State: " + this.State + " Telephone_Number: " + this.TelephoneNumber + " Company: " + this.Company +
				" Date: " + this.Date + " DBUsername: " + this.DBUsername + " DBDate: " + this.DBDate;
	}
	
	public void setDisplayName(String t) { this.Display_Name = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setUsername(String t) { this.Username = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setTeamName(String t) { this.Team_Name = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setEmail(String t) { this.Email = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setDepartmentName(String t) { this.Department_Name = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setManager(String t) { this.Manager = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setCity(String t) { this.City = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setState(String t) { this.State = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setTelephoneNumber(String t) { this.TelephoneNumber = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setCompany(String t) { this.Company = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t;}
	public void setDate(String t) { this.Date = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setDBUsername (String t) { this.DBUsername = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	public void setDBDate (String t) { this.DBDate = (t != null && t.contains(" ")) ? t.replace(" ", "-") : t; }
	
	public String getDisplayName() { return this.Display_Name; }
	public String getUsername() { return this.Username; }
	public String getTeamName() { return this.Team_Name; }
	public String getEmail() { return this.Email; }
	public String getDepartmentName() { return this.Department_Name; }
	public String getManager() { return this.Manager; }
	public String getCity() { return this.City; }
	public String getState() { return this.State; }
	public String getTelephoneNumber() { return this.TelephoneNumber; }	
	public String getCompany() { return this.Company; }	
	public String getDate() { return this.Date; }
	public String getDBUsername () { return this.DBUsername; }
	public String getDBDate () { return this.DBDate; }
}
