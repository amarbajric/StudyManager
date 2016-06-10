package at.fh.swenga.project.model;


import javax.persistence.*;



@Entity
@Table(name = "DegreeProgramType")
public class DegreeProgramTypeModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 10) //MSc or BSc
	String shortcut;
	
	@Column(nullable = false, length = 100)
	String description;
	
	

}
