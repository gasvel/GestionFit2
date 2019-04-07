package app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Class_Day {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate day;
	private int startHour;
	private int endHour;
	@OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<StudentAssist> students;
	private String instructorName;
	private String activity;

	
	public Class_Day(){
		this.students= new HashSet<StudentAssist>();		
	}
	
	public Class_Day(LocalDate date){
		this.day = date;
		this.students= new HashSet<StudentAssist>();

	}
	
	public Class_Day(LocalDate date,int start,int end, String instName) {
		this.day = date;
		this.startHour = start;
		this.endHour = end;
		this.students= new HashSet<StudentAssist>();
		this.instructorName = instName;
	}
	
	public Class_Day(LocalDate date,int start,int end, String name, long id, String instName) {
		this.day = date;
		this.startHour = start;
		this.endHour = end;
		this.students= new HashSet<StudentAssist>();
		this.students.add(new StudentAssist(name,id));
		this.instructorName = instName;
	}
	

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public Set<StudentAssist> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentAssist> students) {
		this.students = students;
	}

	public boolean hasStudent(Long id) {
		for(StudentAssist stu : this.students) {
			if(stu.getStudentId() == id) {
				return true;
			}
		}
		return false;
	}

	public void addStudent(String name, long id) {
		this.students.add(new StudentAssist(name,id));
		
	}

	public void assisted(Long id) {
		for(StudentAssist stu : this.students) {
			if(stu.getStudentId() ==id) {
				stu.setAssisted(true);
			}
		}
		
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}


	
	
	

}
