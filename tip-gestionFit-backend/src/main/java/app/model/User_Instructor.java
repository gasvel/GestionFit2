package app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class User_Instructor extends User_Priv {
	

//	@OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
//	private Set<DayInstructor> classes;
	
	public User_Instructor() {
		super();
//		this.classes = new HashSet<DayInstructor>();
		this.setRole("INSTRUCTOR");

	}

	public User_Instructor(String user, String pass, String name,String email) {
		super(user,pass,name, email);
//		this.classes = new HashSet<DayInstructor>();
		this.setRole("INSTRUCTOR");
	}

//	public Set<DayInstructor> getClasses() {
//		return classes;
//	}
//
//	public void setClasses(Set<DayInstructor> classes) {
//		this.classes = classes;
//	}


	
	
	
	

}
