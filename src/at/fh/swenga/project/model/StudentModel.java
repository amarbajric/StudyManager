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
@Table(name = "Students")
public class StudentModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column
	private String mail = firstname + "." + lastname + "@studymanager.com";
	
	@Column/*(columnDefinition="Decimal(4,1) default '0.0'")*/
	private Double ects = 0.0;
	
	@OneToMany(mappedBy="student",fetch=FetchType.LAZY)
    private Set<ExamApplicationModel> applications;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private YearModel year; // e.g. IMA 2016

	public StudentModel(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
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
