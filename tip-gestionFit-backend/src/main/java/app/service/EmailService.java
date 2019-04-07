package app.service;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import app.model.Promo;
import app.model.User;
import app.model.User_Student;

@Component
@Service
public class EmailService {
	
	public static String WELCOME = "¡Bienvenido a GestionFit!";
	public static String ROUTINE = "Nueva rutina asignada";
	public static String ASSIST = "Gracias por asistir a la clase";
	public static String NEEDPAY = "Debe volver a pagar clases";
	public static String PROMO = "Nuevas promos disponibles";
	public static String PAID = "Gracias por seguir elegiendonos para tu salud";
	

	
	@Autowired
	JavaMailSender sender ;
	
	
	public void sendEmailToUser(User user,String matter)throws Exception {
		String text = "";
		switch(matter){
			case "¡Bienvenido a GestionFit!" : text = this.welcomeMessage(user);System.out.println("BIENVENIDA");break;
			case "Nueva rutina asignada": text = this.newRoutineAssignedMessage(user);System.out.println("RUTINAA");break;
			case "Gracias por asistir a la clase": text = this.assistClassMessage((User_Student) user);break;
			case "Debe volver a pagar clases": text = this.needPayment(user);break;
			case "Nuevas promos disponibles": text = this.newPromo(user);break;
			case "Gracias por seguir elegiendonos para tu salud": text = this.newPayment(user); break;
		}
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper msgHelper = new MimeMessageHelper(msg,true); 
        msgHelper.setTo(user.getMail());
        msgHelper.setText(buildHtml(text),true);
        msgHelper.setSubject(matter);
        sender.send(msg);
   }

	public void sendPromoToUsers(List<User_Student> users, Promo promo) throws MessagingException, UnsupportedEncodingException{
		ByteArrayResource targetStream = null;
		
		
		for(User_Student user : users){
			MimeMessage msg = sender.createMimeMessage();
	        MimeMessageHelper msgHelper = new MimeMessageHelper(msg,true); 
	        String contentType = "text/plain";
	        msgHelper.addAttachment("promo", targetStream,contentType);
	        
	        msgHelper.setTo(user.getMail());
	        msgHelper.setText(buildHtml(promo.getBody()),true);
	        msgHelper.setSubject(promo.getMatter());
	        sender.send(msg);
		}
		

	}
	

	public static String buildHtml(String text){
		
		String html1 =
				
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>"
		 +		"<html xmlns='http://www.w3.org/1999/xhtml'>"
		 +    	"<head>"
		 +			"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
		 +			"<meta name='viewport' content='width=device-width, initial-scale=1.0'/>"
		 +			"<link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>"
		 +		"</head>"
		 +		"<body>"
		 + "	   		<div padding-top: 20px'> "
		 + "				<table align='center' border='1' cellpadding='0' cellspacing='0' width='600'>"
		 +"						<tr>"
		 +"						<td bgcolor='#59b6f8'>"
		 +"							<img src='https://image.ibb.co/gnMBDn/GFEmail.jpg' alt='GFEmail' width='100%' height='230' style='display: block;'>"
		 +"						</td>"
		 +"						</tr>"
		 +"						<tr>"
		 +"						<td bgcolor='#ffffff'>"
		 +							"<div>"
		 + 								text;
		

		 
		 String html2="							 </div>"
				 +"						</td>"
				 +"						</tr>"
				 +"						<tr>"
				 +"						<td bgcolor='#ee4c50'>"
				 +""
				 +"						</td>"
				 +"						</tr>"
				 +"					</table>"
				 + "				<br> "
				 + "			</div>  "
				 + "	 </body>"
				 +		"</html>";
		 
		 
		

		
		return html1 + html2;
		
	}
	
	
	private String welcomeMessage(User user){
		String WELCOME_MESSAGE = "							<h1 style='text-align: center; font-family: Poppins, sans-serif; font-size:14x'> " + "Bienvenido a GestionFit " + user.getNameAndSurname() + "</h1> "
				 + "							<h1 style='text-align: left; font-family: Poppins, sans-serif; font-size:12px'> Ya puedes ingresar a tu cuenta desde la app."+ " </h1>";
		return WELCOME_MESSAGE;
	}
	
	private String newRoutineAssignedMessage(User user){
		String ROUTINE_MESSAGE = "							<h1 style='text-align: center; font-family: Poppins, sans-serif; font-size:14x'> " + user.getNameAndSurname() + "</h1> "
				 + "							<h1 style='text-align: left; font-family: Poppins, sans-serif; font-size:12px'> Se te asigno una nueva rutina. Ingresa en la app para visualizarla. </h1>";
		return ROUTINE_MESSAGE;
	}
	
	private String assistClassMessage(User_Student user){
		String ASSIST_MESSAGE = "							<h1 style='text-align: center; font-family: Poppins, sans-serif; font-size:14x'> " + "Esperamos que disfrutes tu clase " + user.getNameAndSurname() + "</h1> "
				 + "							<h1 style='text-align: left; font-family: Poppins, sans-serif; font-size:12px'> Todavia te quedan " + user.getRemainingLessons() + " clases, luego tendras que volver a abonar. </h1>";
		return ASSIST_MESSAGE;
	}

	private String needPayment(User user){
		String NEED_PAYMENT_MESSAGE = "							<h1 style='text-align: center; font-family: Poppins, sans-serif; font-size:14x'> " + "Usted " + user.getNameAndSurname() + " se ha quedado sin clases </h1> "
				 + "							<h1 style='text-align: left; font-family: Poppins, sans-serif; font-size:12px'> Si desea continuar asistiendo a las clases por favor vuelva a abonar, esperamos verlo pronto.  </h1>";
		return NEED_PAYMENT_MESSAGE;
	}
	
	private String newPromo(User user){
		String PROMO_MESSAGE = "							<h1 style='text-align: center; font-family: Poppins, sans-serif; font-size:14x'> " + "Buenas " + user.getNameAndSurname() + " se han agregado nuevas promos </h1> "
				 + "							<h1 style='text-align: left; font-family: Poppins, sans-serif; font-size:12px'> Si te interesa que hay de nuevo por favor preguntanos o revisa nuestra seccion de promos.  </h1>";
		return PROMO_MESSAGE;
	}
	
	private String newPayment(User user){
		String PAYMENT_MESSAGE = "							<h1 style='text-align: center; font-family: Poppins, sans-serif; font-size:14x'> " + "Gracias " + user.getNameAndSurname() + " por elegirnos </h1> "
				 + "							<h1 style='text-align: left; font-family: Poppins, sans-serif; font-size:12px'> Usted ha abonado por un mes de clases, decida a cuales quiera asistir y que se divierta.  </h1>";
		return PAYMENT_MESSAGE;
	}
	

}
