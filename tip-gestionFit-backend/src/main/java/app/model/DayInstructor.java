package app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class DayInstructor {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	private String day;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<StartEndHour> startEndHours;
	

	
	public DayInstructor() {
		this.startEndHours = new HashSet<StartEndHour>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Set<StartEndHour> getStartEndHours() {
		return startEndHours;
	}

	public void setStartEndHours(Set<StartEndHour> startEndHours) {
		this.startEndHours = startEndHours;
	}

	

	
	

}
