package at.fh.swenga.project.queryModels;

import java.util.Date;

//Objects of this class hold data of a specific query 
public class Q_ExamDateModelWithRoom implements Comparable<Q_ExamDateModelWithRoom>{ //sortable by date
	
	private Integer id; //ID of the examdate
	private Date date;	//date of the exam
	private String description;	//description of the exam e.g. 1.date
	private String room; //room in which the exam takes place
	
	public Q_ExamDateModelWithRoom(Integer id, Date date, String description, String room) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
		this.room = room;
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public int compareTo(Q_ExamDateModelWithRoom o) {
		return getDate().compareTo(o.getDate());
	}
	
	

}
