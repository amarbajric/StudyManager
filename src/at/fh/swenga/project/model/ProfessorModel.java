package at.fh.swenga.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "professors")
public class ProfessorModel implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(unique = true)
	private String mail; 

	@ManyToMany(mappedBy = "professors", fetch = FetchType.EAGER) // professors collection is mapped wit the course collection
	private Set<CourseModel> courses = new HashSet<CourseModel>();
	public Set<CourseModel> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseModel> courses) {
		this.courses = courses;
	}
	
	public ProfessorModel() {
		// TODO Auto-generated constructor stub
	}

	public ProfessorModel(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = firstname.toLowerCase() + "." + lastname.toLowerCase() + "@studymanager.com";
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
