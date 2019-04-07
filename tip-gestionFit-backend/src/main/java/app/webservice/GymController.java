package app.webservice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import app.model.Class_Calendar;
import app.model.Gym;
import app.model.GymDTO;
import app.model.Payment;
import app.service.GymService;
import app.service.PaymentsService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class GymController {

	@Autowired
	private GymService gymServ = new GymService();
	
	@Autowired
	private PaymentsService paymentServ = new PaymentsService();
	

	@GetMapping(value = "/gyms", produces = "application/json")   
	public List<GymDTO> getGyms() {
		return this.gymServ.getAll();

	}

	@GetMapping(value = "/gym/{idGym}", produces = "application/json")   
	public Gym getGym(@PathVariable("idGym") long id) {
		return this.gymServ.getGym(id);

	}
	
	@PostMapping(value= "/gym",produces= "application/json")
	public ResponseEntity<Void> registerGym(@RequestBody Gym gym){
		this.gymServ.save(gym);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/calendar/{idGym}", produces = "application/json")   
	public Class_Calendar getCalendar(@PathVariable("idGym") long id) throws Exception{
		return this.gymServ.getCalendar(id);

	}
	
	@GetMapping(value="/payments/{idGym}")
	public ResponseEntity<byte[]> getPaymentReport(@PathVariable("idGym") long id) throws DocumentException, IOException {
		LocalDate day = LocalDate.now();
		byte[] document =this.paymentServ.generateReport(id, day);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    // Here you have to set the actual filename of your pdf
	    String filename = day.toString() + "Reporte.pdf";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<>(document, headers, HttpStatus.OK);
	    return response;
		
	}
	
	@PostMapping(value="payments/new/in/{idGym}")
	public ResponseEntity<Void> newPaymentIn(@PathVariable("idGym") long id,@RequestBody Payment paymentIn){
		this.paymentServ.addPaymentIn(id, paymentIn);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value="payments/new/out/{idGym}")
	public ResponseEntity<Void> newPaymentOut(@PathVariable("idGym") long id,@RequestBody Payment paymentIn){
		this.paymentServ.addPaymentOut(id, paymentIn);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
