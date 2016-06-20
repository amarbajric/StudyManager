package at.fh.swenga.project.model;

import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name = "students")
public class StudentModel implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column(unique = true)
	private String mail;
	
	@Column/*(columnDefinition="Decimal(4,1) default '0.0'")*/
	private Double ects = 0.0;
	
	@OneToMany(mappedBy="student",fetch=FetchType.EAGER , cascade = CascadeType.PERSIST)
    private Set<ExamApplicationModel> applications;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private YearModel year; // e.g. IMA 2016
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "students_courses", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<CourseModel> courses = new HashSet<CourseModel>();
	
	public Set<CourseModel> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseModel> courses) {
		this.courses = courses;
	}
	
	public StudentModel() {
		// TODO Auto-generated constructor stub
	}
	
	public StudentModel(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = firstname.toLowerCase() + "." + lastname.toLowerCase() + "@studymanager.com";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Double getEcts() {
		return ects;
	}

	public void setEcts(Double ects) {
		this.ects = ects;
	}

	public YearModel getYear() {
		return year;
	}

	public void setYear(YearModel year) {
		this.year = year;
	}

	public Set<ExamApplicationModel> getApplications() {
		return applications;
	}

	public void setApplications(Set<ExamApplicationModel> applications) {
		this.applications = applications;
	}


	
	
}
