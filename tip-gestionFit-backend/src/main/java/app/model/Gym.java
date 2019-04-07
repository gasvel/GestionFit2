package app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Gym {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	private String name;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="gym",orphanRemoval=true)
	private Set<User> users;
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Class_Calendar calendar;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Exercise> exercises;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Routine> routines;
	
	@OneToMany(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
	private Set<PaymentDay> paymentDays;

	
	
	public Gym() {
		this.users = new HashSet<User>();
		this.exercises = new HashSet<Exercise>();
		this.routines = new HashSet<Routine>();
		this.calendar = new Class_Calendar();
		this.paymentDays = new HashSet<PaymentDay>();

	}
	
	public Gym(String nameG) {
		this.name = nameG;
		this.users = new HashSet<User>();
		this.exercises = new HashSet<Exercise>();
		this.routines = new HashSet<Routine>();
		this.calendar = new Class_Calendar();
		this.paymentDays = new HashSet<PaymentDay>();

	}
	
	

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



	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> admins) {
		this.users = admins;
	}
	

	
	public void addUser(User user) {
		user.setGym(this.id);
		this.users.add(user);
	}
	
	public void addExercise(Exercise exercise) {
		this.exercises.add(exercise);
	}
	
	public void addRoutine(Routine routine) {
		this.routines.add(routine);
	}

	public Class_Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Class_Calendar calendar) {
		this.calendar = calendar;
	}

	public Set<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}

	public Set<Routine> getRoutines() {
		return routines;
	}

	public void setRoutines(Set<Routine> routines) {
		this.routines = routines;
	}

	public Set<PaymentDay> getPaymentDays() {
		return paymentDays;
	}

	public void setPaymentDays(Set<PaymentDay> paymentDays) {
		this.paymentDays = paymentDays;
	}

	public void addPayment(LocalDate day, Payment payment) {
		
		if(!this.paymentDays.isEmpty()) {
			for(Iterator<PaymentDay> it = this.paymentDays.iterator();it.hasNext();) {
				PaymentDay pDay= it.next();
				if(pDay.getDay().equals(day)) {
					pDay.addPayment(payment);
					return;
				}
			}
		}
		//SI HAY MAS DE 31 DIAS DE PAGO , ELIMINAR EL MAS ANTIGUO
		PaymentDay newPDay= new PaymentDay();
		newPDay.setDay(day);
		newPDay.addPayment(payment);
		this.paymentDays.add(newPDay);
	}



	public PaymentDay getPaymentDay(LocalDate day) {
		for(Iterator<PaymentDay> it = this.paymentDays.iterator();it.hasNext();) {
			PaymentDay pDay= it.next();
			if(pDay.getDay().equals(day)) {
				return pDay;
			}
		}
		return null;
	}

	

	
	

}

