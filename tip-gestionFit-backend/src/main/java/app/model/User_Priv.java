package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User_Priv extends User {
	
	@Column(unique=true)
	private String username;
	public String password;
	
	public User_Priv(){
		
	}
	
	public User_Priv(String user,String pass,String name, String email){
		super(name,email);
		this.username = user;
		this.password = pass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
