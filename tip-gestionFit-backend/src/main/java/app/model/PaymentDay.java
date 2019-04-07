package app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PaymentDay {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate day;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Payment> payments;
	
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	private Set<Payment> paymentsOut;
	
	public PaymentDay() {
		this.payments = new HashSet<Payment>();
//		this.paymentsOut = new HashSet<Payment>();

	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public List<Payment> getPaymentsIn() {
		List<Payment> paysIn = new ArrayList<Payment>();
		for(Payment p : this.payments) {
			if(p.isInOrOut()) {
				paysIn.add(p);
			}
		}
		return paysIn;
	}

	public List<Payment> getPaymentsOut() {
		List<Payment> paysOut = new ArrayList<Payment>();
		for(Payment p : this.payments) {
			if(!p.isInOrOut()) {
				paysOut.add(p);
			}
		}
		return paysOut;
	}


	public void addPayment(Payment payment) {
		this.payments.add(payment);
		
	}

	

}
