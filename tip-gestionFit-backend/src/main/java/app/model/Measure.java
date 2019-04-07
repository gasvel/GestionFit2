package app.model;

import java.util.ArrayList;
import java.util.List;

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
public class Measure {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	public String name;
	@OneToMany(fetch=FetchType.EAGER,cascade= {CascadeType.ALL})
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Measurement> measures;
	
	public Measure(){
		
	}
	
	public Measure(String newName){
		this.name = newName;
		this.measures = new ArrayList<Measurement>();
	}
	
	public void addMeasurement(Measurement newMeasurement){
		this.measures.add(newMeasurement);
	}
}
