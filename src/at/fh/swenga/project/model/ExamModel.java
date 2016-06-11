package at.fh.swenga.project.model;


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
@Table(name = "Exams")
public class ExamModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String description;

	@Column(nullable = false, length = 20)
	private String type; // final, mid term
	
	@OneToMany(mappedBy = "exam", fetch = FetchType.LAZY)
	private Set<ExamDateModel> examDates;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private CourseModel course;

	public ExamModel(String description, String type) {
		super();
		this.description = description;
		this.type = type;
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

	public CourseModel getCourse() {
		return course;
	}

	public void setCourse(CourseModel course) {
		this.course = course;
	}

}
