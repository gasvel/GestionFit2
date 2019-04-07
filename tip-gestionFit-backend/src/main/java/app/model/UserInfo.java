package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="\"user\"")
public class UserInfo {
	@Id
	private Long id;
	
	private String nameAndSurname;
	private String mail;

	
	public UserInfo() {
		
	}


	public String getNameAndSurname() {
		return nameAndSurname;
	}


	public void setNameAndSurname(String nameAndSurname) {
		this.nameAndSurname = nameAndSurname;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	
	

}
