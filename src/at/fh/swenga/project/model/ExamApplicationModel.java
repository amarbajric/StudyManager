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
	private int attempt; // 1st, 2nd, 3rd --> with automated method later on

	@Column
	private int grade = 0; // at application grade is 0 because teacher overwrites it
					// later on when the exam is corrected

	@ManyToOne(cascade = CascadeType.PERSIST)
	private StudentModel student;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private ExamDateModel examDate;
	
	public ExamApplicationModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ExamApplicationModel(int attempt) {
		super();
		this.attempt = attempt;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
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
