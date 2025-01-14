package sdms.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
	
	private long id;
	
	// FOREIGN KEYS ######################################################
	private long idCustomer;
	private long idDoctor;
	private long idTreatment;
	
	
	// ###################################################################
	
	private LocalDate date;
	private LocalTime time;
	private int isDone;  //bit default 0," 	 // di default l'appuntamento non è ancora avvenuto nel momento della registrazione
	private String invoiceNumber; // Numero fattura - generalmente ANNO + NUMERO intero
								 // I db non vuole salvarsi le fatture perché sarebbero dati ritondanti 
								 // ( cioè le fatture si possono costruire dai dati già presenti nel db )
								 // si limita a segnare quali appuntamenti sono stati già fatturati
	private Double payment;
	private String notes;		 // eventualmente se serve specificare qualcosa

	public AppointmentDTO() {}
	

	
	public long getId() {
		return this.id;
	}
	public void setId( long id ) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}


	public long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public long getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(long idDoctor) {
		this.idDoctor = idDoctor;
	}

	public long getIdTreatment() {
		return idTreatment;
	}

	public void setIdTreatment(long idTreatment) {
		this.idTreatment = idTreatment;
	}

	public int getIsDone() {
		return isDone;
	}

	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}



	public String getInvoiceNumber() {
		return invoiceNumber;
	}



	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}



	public Double getPayment() {
		return payment;
	}



	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	
	
}
