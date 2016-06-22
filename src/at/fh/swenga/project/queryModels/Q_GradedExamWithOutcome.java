package at.fh.swenga.project.queryModels;

import java.util.Date;
import java.util.List;

import at.fh.swenga.project.model.ExamApplicationModel;

public class Q_GradedExamWithOutcome {
	
	private Integer id;
	private String courseName;
	private String examType;
	private Integer attempt;
	private Date date;
	private Integer grade;
	private List<Integer> gradesOverview;
	private Double averageGrade;
	
	public Q_GradedExamWithOutcome(Integer id, String courseName, String examType, Integer attempt, Date date, Integer grade,
			List<Integer> gradesOverview, Double averageGrade) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.examType = examType;
		this.attempt = attempt;
		this.date = date;
		this.grade = grade;
		this.gradesOverview = gradesOverview;
		this.averageGrade = averageGrade;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public Integer getAttempt() {
		return attempt;
	}

	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public List<Integer> getGradesOverview() {
		return gradesOverview;
	}

	public void setGradesOverview(List<Integer> gradesOverview) {
		this.gradesOverview = gradesOverview;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}
	
	
	
	

}
