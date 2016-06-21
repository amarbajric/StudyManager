package at.fh.swenga.project.model;

import java.util.Date;

//Objects of this class hold data of a specific query
public class Q_ProfessorExam {
	
	private int id;
	private Date date;
	private String dateNumber;
	private String description;
	private String type;
	private String room;
	private int applicants;
	
	public Q_ProfessorExam(int id, Date date, String dateNumber, String description, String type, String room,
			int applicants) {
		super();
		this.id = id;
		this.date = date;
		this.dateNumber = dateNumber;
		this.description = description;
		this.type = type;
		this.room = room;
		this.applicants = applicants;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateNumber() {
		return dateNumber;
	}

	public void setDateNumber(String dateNumber) {
		this.dateNumber = dateNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getApplicants() {
		return applicants;
	}

	public void setApplicants(int applicants) {
		this.applicants = applicants;
	}
	
	


	
	
	
	

}
