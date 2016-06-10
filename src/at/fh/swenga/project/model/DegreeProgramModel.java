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
@Table(name = "DegreeProgram")
public class DegreeProgramModel {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 10) //IMA, AIM
	String shortcut;
	
	@Column(nullable = false, length = 100) 
	String leader;
	
	@Column(nullable = false, length = 100) 
	String description;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	DegreeProgramTypeModel type;

	public DegreeProgramModel(String shortcut, String leader, String description, DegreeProgramTypeModel type) {
		super();
		this.shortcut = shortcut;
		this.leader = leader;
		this.description = description;
		this.type = type;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
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

	public DegreeProgramTypeModel getType() {
		return type;
	}

	public void setType(DegreeProgramTypeModel type) {
		this.type = type;
	}
	
	

}
