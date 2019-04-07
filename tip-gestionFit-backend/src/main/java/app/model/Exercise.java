package app.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Exercise {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	@Enumerated
	public Exercise_Type type;
	public String name;
	public String description;
//	public boolean isTemplate;
	
	public Exercise() {
		
	}
	
	public Exercise(String nom,String desc,Exercise_Type typeE) {
		this.type = typeE;
		this.name = nom;
		this.description = desc;
//		this.isTemplate = true;
	}

	public Exercise_Type getType() {
		return type;
	}

	public void setType(Exercise_Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
