package at.fh.swenga.project.queryModels;

import java.util.List;

import at.fh.swenga.project.model.ExamApplicationModel;

public class Q_GradedExamWithOutcome {
	
	private ExamApplicationModel examApplication;
	private List<Integer> gradesOverview;
	private Double averageGrade;
	
	public Q_GradedExamWithOutcome(ExamApplicationModel examApplication, List<Integer> gradesOverview,
			Double averageGrade) {
		super();
		this.examApplication = examApplication;
		this.gradesOverview = gradesOverview;
		this.averageGrade = averageGrade;
	}

	public ExamApplicationModel getExamApplication() {
		return examApplication;
	}

	public void setExamApplication(ExamApplicationModel examApplication) {
		this.examApplication = examApplication;
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
