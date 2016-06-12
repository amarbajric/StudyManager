package at.fh.swenga.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DegreePrograms")
public class DegreeProgramModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 10) // IMA, AIM
	private String acronym;

	@Column(nullable = false, length = 100)
	private String leader;

	@Column(nullable = false, length = 100)
	private String description;

	@Column(nullable = false, length = 10) // Master or Bachelor
	private String type;

	@OneToMany(mappedBy = "degreeProgram", fetch = FetchType.LAZY)
	private Set<YearModel> years;

	@OneToMany(mappedBy = "degreeProgram", fetch = FetchType.LAZY)
	private Set<CourseModel> courses;
	
	public DegreeProgramModel() {
		// TODO Auto-generated constructor stub
	}
		
	
	public DegreeProgramModel(String acronym, String leader, String description, String type) {
		super();
		this.acronym = acronym;
		this.leader = leader;
		this.description = description;
		this.type = type;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
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

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Set<YearModel> getYears() {
		return years;
	}

	public void setYears(Set<YearModel> years) {
		this.years = years;
	}

	public Set<CourseModel> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseModel> courses) {
		this.courses = courses;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
