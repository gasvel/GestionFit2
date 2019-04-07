package app.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Payment {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate day;
	
	private String student;
	
	private String activity;
	
	private float amount;
	
	private String professor;
	
	private boolean inOrOut;
	
	public Payment() {
		
	}
	
	public Payment(String stu, String act, float amo, String prof,boolean inOut) {
		this.student = stu;
		this.activity = act;
		this.amount = amo;
		this.professor = prof;
		this.inOrOut = inOut;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public boolean isInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(boolean inOrOut) {
		this.inOrOut = inOrOut;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}
	
	
	
	
	
}
