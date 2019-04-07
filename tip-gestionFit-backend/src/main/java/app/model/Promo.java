package app.model;

public class Promo {
	
	private String matter;
	private String body;
	
	public Promo(){
		
	}
	
	public Promo(String matterS, String bodyS){
		this.matter = matterS;
		this.body = bodyS;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


	
	

}
