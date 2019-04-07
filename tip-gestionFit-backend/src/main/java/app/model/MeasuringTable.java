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
public class MeasuringTable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Measure> measures = new ArrayList<Measure>();
	
	public MeasuringTable(){
		this.measures.add(new Measure("Chest"));
		this.measures.add(new Measure("Arm"));
		this.measures.add(new Measure("Waist"));
		this.measures.add(new Measure("Abdomen"));
		this.measures.add(new Measure("Glute"));
		this.measures.add(new Measure("Thigh"));
		this.measures.add(new Measure("Calf muscle"));
	}
	
	public void addNewMeasurement(List<Measurement> newMeasurement){
		for(int i = 0; i < this.measures.size(); i++){
			this.measures.get(i).addMeasurement(newMeasurement.get(i));
		}
	}
}
