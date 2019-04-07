package app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class DaysAssisted {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	private String nameStudent;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date day;
	
	public DaysAssisted(){
		
	}
	
	public DaysAssisted(String nameS, Date dateS){
		this.nameStudent = nameS;
		this.day = dateS;
	}

	public String getNameStudent() {
		return nameStudent;
	}

	public void setNameStudent(String nameStudent) {
		this.nameStudent = nameStudent;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}
	
}
