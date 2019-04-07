package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StartEndHour {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	private int startHour;
	private int endHour;
	
	public StartEndHour() {
		
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
	
	

}
