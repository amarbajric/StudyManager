package at.fh.swenga.project.model;

import java.util.Date;

public class Q_studentExam {
	
	private String course;
	private String type;
	private String description;
	private Date date;
	private double ects;
	
	public Q_studentExam(String course, String type, String description, Date date, double ects) {
		super();
		this.course = course;
		this.type = type;
		this.description = description;
		this.date = date;
		this.ects = ects;
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
	
	
	

}
