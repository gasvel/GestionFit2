package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentAssist {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	private Long studentId;
	private String studentName;
	private boolean assisted;
	
	public StudentAssist() {
		
	}

	public StudentAssist(String name, long id) {
		this.studentName = name;
		this.studentId = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public boolean isAssisted() {
		return assisted;
	}

	public void setAssisted(boolean assisted) {
		this.assisted = assisted;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	
	
	
	

}
