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


@Entity
public class Class_Calendar {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	@javax.persistence.OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Class_Day> classes= new HashSet<Class_Day>();
	
	public Class_Calendar(){
		this.classes= new HashSet<Class_Day>();
	}
	
	public void clearCalendar(){
		
		for(Class_Day classD : this.classes){
			if(classD.getDay().isBefore(this.firstClass().plusDays(15))){
				this.classes.remove(classD);
			}
			
		}

		
	}
	

	public LocalDate firstClass(){
		LocalDate first = this.classes.iterator().next().getDay();
		for(Class_Day classD : this.classes){
			if(classD.getDay().isBefore(first)){
				first = classD.getDay();
			}
			
		}
		return first;
		
	}

	

	
	public Class_Day getClassDay(LocalDate date){
		
		for(Class_Day classD : this.classes){
			if(classD.getDay().isEqual(date)){
				return classD;
				
			}
		}
		
		return null;
	}

	public Set<Class_Day> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class_Day> classes) {
		this.classes = classes;
	}

	public void addClass(LocalDate plusDays,int startHour, int endHour, String name, long id,String instName) {
		for(Class_Day cd : this.classes) {
			if(cd.getDay().isEqual(plusDays) 
			  && cd.getStartHour() == (startHour) 
			  && !cd.hasStudent(id)) {
				cd.addStudent(name,id);
				return;
			}
			
		}
		this.classes.add(new Class_Day(plusDays, startHour, endHour, name,id,instName));
		
	}

	public void markAssist(Long id) {
		LocalDate today = LocalDate.now();
		System.out.println("HOOOOOY " + today);
		for(Class_Day cd : this.classes) {
			if(cd.getDay().getMonth() == today.getMonth() && cd.getDay().getDayOfMonth() == today.getDayOfMonth() 
			  && cd.hasStudent(id)) {
				cd.assisted(id);
				return;
			}
			
		}
		
	}
	
	
}
