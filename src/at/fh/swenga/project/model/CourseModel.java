package at.fh.swenga.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class CourseModel implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true, length = 100)
	private String acronym;

	@Column(nullable = false, length = 100)
	private String description;

	@ManyToMany(mappedBy = "courses") // student collection is mapped wit the course collection
	private Set<StudentModel> students = new HashSet<StudentModel>();
	public Set<StudentModel> getStudents() {
		return students;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable = false)
	private Double ectsValue;

	@Column(nullable = false, length = 30)
	private String type; // seminar, practical course, etc.


	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<ExamModel> exams;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private DegreeProgramModel degreeProgram;

	@ManyToMany(cascade = CascadeType.ALL) // Hibernate will update the associated professors when the course gets updated.
	@JoinTable(name = "courses_professors", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
	
	private Set<ProfessorModel> professors = new HashSet<ProfessorModel>(); //there the professors of this course get stored
	
	public Set<ProfessorModel> getProfessors() {
		return professors;
	}

	public void setProfessors(Set<ProfessorModel> professors) {
		this.professors = professors;
	}
	
	public CourseModel() {
		
	}

	public CourseModel(String acronym, String description, Double ectsValue, String type) {
		super();
		this.acronym = acronym;
		this.description = description;
		this.ectsValue = ectsValue;
		this.type = type;
	}

	
	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Set<ExamModel> getExams() {
		return exams;
	}

	public void setExams(Set<ExamModel> exams) {
		this.exams = exams;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getEctsValue() {
		return ectsValue;
	}

	public void setEctsValue(Double ectsValue) {
		this.ectsValue = ectsValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DegreeProgramModel getDegreeProgram() {
		return degreeProgram;
	}

	public void setDegreeProgram(DegreeProgramModel degreeProgram) {
		this.degreeProgram = degreeProgram;
	}

}
