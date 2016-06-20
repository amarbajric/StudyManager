package at.fh.swenga.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exam_applications")
public class ExamApplicationModel implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private Integer attempt; // 1st, 2nd, 3rd --> with automated method later on

	@Column
	private Integer grade;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private StudentModel student;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
	private ExamDateModel examDate;
	
	public ExamApplicationModel() {
		// TODO Auto-generated constructor stub
	}	
	
	
	public ExamApplicationModel(Integer attempt, StudentModel student, ExamDateModel examDate) {
		super();
		this.attempt = attempt;
		this.student = student;
		this.examDate = examDate;
	}


	public Integer getAttempt() {
		return attempt;
	}

	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public StudentModel getStudent() {
		return student;
	}

	public void setStudent(StudentModel student) {
		this.student = student;
	}

	public ExamDateModel getExamDate() {
		return examDate;
	}

	public void setExamDate(ExamDateModel examDate) {
		this.examDate = examDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
