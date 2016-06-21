package at.fh.swenga.project.model;

import java.util.Date;

//Objects of this class hold data of a specific query
public class Q_studentExam {
	
	private Integer id;
	private String course;
	private String type;
	private String description;
	private Date date;
	private double ects;
	private String room;
	private Integer enrolled;
	
	
	public Q_studentExam(Integer id, String course, String type, String description, Date date, double ects,
			String room) {
		super();
		this.id = id;
		this.course = course;
		this.type = type;
		this.description = description;
		this.date = date;
		this.ects = ects;
		this.room = room;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public double getEcts() {
		return ects;
	}


	public void setEcts(double ects) {
		this.ects = ects;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public Integer getEnrolled() {
		return enrolled;
	}


	public void setEnrolled(Integer enrolled) {
		this.enrolled = enrolled;
	}
	
	
	

}
