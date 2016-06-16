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
@Table(name = "years")
public class YearModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 4) // e.g. 2016
	private String year;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private DegreeProgramModel degreeProgram; // IMA or AIM
	
    @OneToMany(mappedBy="year",fetch=FetchType.LAZY)
    private Set<StudentModel> students;
    
    public YearModel() {
		// TODO Auto-generated constructor stub
	}

	public YearModel(String year) {
		super();
		this.year = year;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public DegreeProgramModel getDegreeProgram() {
		return degreeProgram;
	}

	public void setDegreeProgram(DegreeProgramModel degreeProgram) {
		this.degreeProgram = degreeProgram;
	}

	public Set<StudentModel> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentModel> students) {
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
