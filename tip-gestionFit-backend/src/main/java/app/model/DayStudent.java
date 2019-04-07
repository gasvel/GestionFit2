package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DayStudent {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	private String day;
	private int startHour;
	private int endHour;
	private String activity;
	
	public DayStudent(){
		
	}
	
	public DayStudent(String dayD,int start,int end) {
		this.day = dayD;
		this.startHour = start;
		this.endHour = end;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int start) {
		this.startHour = start;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int end) {
		this.endHour = end;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	
	
	
	
}
