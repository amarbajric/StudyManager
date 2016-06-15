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
@Table(name = "rooms")
public class RoomModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String description; // e.g. EDV224 
	
	@Column(nullable = false)
	private int seats;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	private Set<ExamDateModel> examDates;
	
	public RoomModel() {
		// TODO Auto-generated constructor stub
	}

	public RoomModel(String description, int seats) {
		super();
		this.description = description;
		this.seats = seats;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<ExamDateModel> getExamDates() {
		return examDates;
	}

	public void setExamDates(Set<ExamDateModel> examDates) {
		this.examDates = examDates;
	}
	
	
	
	
}
