package app.model;

import javax.persistence.Entity;

@Entity
public class User_Admin extends User_Priv {
	

	
	public User_Admin(){
		super();
		this.setRole("ADMIN");
	}
	
	public User_Admin(String user, String pass, String name,String email) {
		super(user,pass,name, email);

		this.setRole("ADMIN");
	}


	
	
	

}
