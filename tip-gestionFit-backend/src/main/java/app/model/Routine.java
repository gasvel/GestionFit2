package app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Routine {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	public String name;
//	public boolean isTemplate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date creationDate;
	@Enumerated
	public Routine_Type type;
	@ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderColumn
	public List<Exercise> exercises = new ArrayList<Exercise>();
	
	public Routine() {
		
	}
	
	public Routine(String nameR, Routine_Type typeR) {
		this.name = nameR;
		this.creationDate = new Date();
		this.type = typeR;
		this.exercises = new ArrayList<Exercise>();
//		this.isTemplate = true;
	}
	
	
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void addExercise(Exercise newExercise) {
		this.exercises.add(newExercise);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Routine_Type getType() {
		return type;
	}

	public void setType(Routine_Type type) {
		this.type = type;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	

}
