package app.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import app.model.Gym;
import app.model.Payment;
import app.model.PaymentDay;
import app.persistence.GymDAO;

@Service
public class PaymentsService {
	
	@Autowired
	private GymDAO gymDAO;
	
	public PaymentsService() {
		this.gymDAO = new GymDAO();
	}
	
	@Transactional
	public void addPaymentIn(Long idGym,Payment payment) {
		Gym gym = this.gymDAO.getById(idGym);
		gym.addPayment(payment.getDay(),payment);
		this.gymDAO.update(gym);
		
	}
	
	@Transactional
	public void addPaymentOut(Long idGym,Payment payment) {
		Gym gym = this.gymDAO.getById(idGym);
		gym.addPayment(payment.getDay(),payment);
		this.gymDAO.update(gym);
	}
	
	@Transactional
	public byte[] generateReport(Long idGym, LocalDate day) throws DocumentException, IOException {
		Gym gym = this.gymDAO.getById(idGym);
		PaymentDay pDay = gym.getPaymentDay(day);
		Document document = new Document();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, byteArrayOutputStream);
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		PdfPTable table = new PdfPTable(5);
		PdfPCell cell = new PdfPCell(new Phrase("Ingresos"));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(5);
		table.addCell(cell);
		table.addCell("Fecha");
		table.addCell("Nombre");
		table.addCell("Actividad");
		table.addCell("Monto");
		table.addCell("Profesor");
		for(Payment p : pDay.getPaymentsIn()) {
			cell = new PdfPCell(new Phrase(day.toString()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
			cell = new PdfPCell(new Phrase(p.getStudent()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
			cell = new PdfPCell(new Phrase(p.getActivity()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
			cell = new PdfPCell(new Phrase("$" + p.getAmount()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
			cell = new PdfPCell(new Phrase(p.getProfessor()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
		}
		cell = new PdfPCell(new Phrase("Egresos"));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(5);
		table.addCell(cell);
		table.addCell("Fecha");
		table.addCell("Para");
		table.addCell("Motivo");
		table.addCell("Monto");
		table.addCell("Profesor");
		for(Payment p : pDay.getPaymentsOut()) {
			cell = new PdfPCell(new Phrase(day.toString()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
			cell = new PdfPCell(new Phrase(p.getStudent()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
			cell = new PdfPCell(new Phrase(p.getActivity()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
			cell = new PdfPCell(new Phrase("$" + p.getAmount()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
			cell = new PdfPCell(new Phrase(p.getProfessor()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
		}
		document.add(table);
		document.close();
		byteArrayOutputStream.close();
		 byte[] pdfBytes = byteArrayOutputStream.toByteArray();
		 return pdfBytes;

	}
	
	
}
