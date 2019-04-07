package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;


@Entity(name="GymDTO")
@Table(name="Gym")
@Immutable
public class GymDTO {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	private String name;
	
	public GymDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
