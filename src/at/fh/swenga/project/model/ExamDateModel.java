package at.fh.swenga.project.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exam_dates")
public class ExamDateModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private Date date; // date and time of the exam
	
	@Column
	private String description; // first date or second date

	@ManyToOne(cascade = CascadeType.MERGE)
	private RoomModel room;

	@ManyToOne(cascade = CascadeType.MERGE)
	private ExamModel exam;
	
	@OneToMany(mappedBy = "examDate", fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
	private Set<ExamApplicationModel> examApplications;

	public ExamDateModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ExamDateModel(Date date, String description, RoomModel room, ExamModel exam) {
		super();
		this.date = date;
		this.description = description;
		this.room = room;
		this.exam = exam;
	}
	

	public ExamDateModel(Date date) {
		super();
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RoomModel getRoom() {
		return room;
	}

	public void setRoom(RoomModel room) {
		this.room = room;
	}

	public ExamModel getExam() {
		return exam;
	}

	public void setExam(ExamModel exam) {
		this.exam = exam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<ExamApplicationModel> getExamApplications() {
		return examApplications;
	}


	public void setExamApplications(Set<ExamApplicationModel> examApplications) {
		this.examApplications = examApplications;
	}
	
	

}
