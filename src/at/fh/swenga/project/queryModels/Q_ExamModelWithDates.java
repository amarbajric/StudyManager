package at.fh.swenga.project.queryModels;

import java.util.List;

//Objects of this class hold data of a specific query
public class Q_ExamModelWithDates {
	
	 private Integer id;	// ID of the exam
	 private String courseDescription; //e.g. Software Engineering Basics
	 private String examDescription; //e.g. final/midterm
	 private List<Q_ExamDateModelWithRoom> examDates; //e.g. [("01.01.2015","1.termin","G.AP147.221"),("03.03.2015","2.termin","G.AP147.221"),...]

	public Q_ExamModelWithDates(Integer id, String courseDescription, String examDescription,
			List<Q_ExamDateModelWithRoom> examDates) {
		super();
		this.id = id;
		this.courseDescription = courseDescription;
		this.examDescription = examDescription;
		this.examDates = examDates;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getExamDescription() {
		return examDescription;
	}

	public void setExamDescription(String examDescription) {
		this.examDescription = examDescription;
	}

	public List<Q_ExamDateModelWithRoom> getExamDates() {
		return examDates;
	}

	public void setExamDates(List<Q_ExamDateModelWithRoom> examDates) {
		this.examDates = examDates;
	}


	 
	 

}
