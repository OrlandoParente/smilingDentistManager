package sdms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "appointment" )
public class Appointment {
	
	public final static int IS_DONE = 1;
	public final static int IS_NOT_DONE = 0;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	// FOREIGN KEYS ######################################################
	
	// --- Li lascio per mantenere la compatibilità con il Client Swing---
	// lascio i corrispettivi setter e getter perchè cosi, jpa mi da problemi 
	// anche senza l'annotazione @Column li mette in conflitto con i rispettivi sotto
	
	// private int idCustomer;
	// private int idDoctor;
	// private int idTreatment;
	
	// -------------------------------------------------------------------
	
	@ManyToOne
	@JoinColumn( name = "id_customer" )
	private Customer customer;
	
	@ManyToOne
	@JoinColumn( name = "id_doctor" )
	private Employee doctor;
	
	@ManyToOne
	@JoinColumn( name = "id_treatment" )
	private Treatment treatment;
	
	// ###################################################################
	
	private String date;
	private String time;
	private int isDone;  //bit default 0," 	 // di default l'appuntamento non è ancora avvenuto nel momento della registrazione
	private String billNumber; // Numero fattura - generalmente ANNO + NUMERO intero
								 // I db non vuole salvarsi le fatture perché sarebbero dati ritondanti 
								 // ( cioè le fatture si possono costruire dai dati già presenti nel db )
								 // si limita a segnare quali appuntamenti sono stati già fatturati
	private String note;		 // eventualmente se serve specificare qualcosa

	public Appointment() {}
	
	// isDone = 0 di default
	public Appointment(long id, String date, String time, int idCustomer, int idDoctor,
			int idTreatment, String billNumber, String note) {
		this(  id, date, time, idCustomer, idDoctor, idTreatment, 0, billNumber, note );
	}
	
	public Appointment(long id, String date, String time, int idCustomer, int idDoctor,
			int idTreatment, int isDone, String billNumber, String note) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		
		// this.idCustomer = idCustomer;
		this.setidCustomer(idCustomer);
		
		// this.idDoctor = idDoctor;
		this.setidDoctor(idDoctor);
		
		// this.idTreatment = idTreatment;
		this.setidTreatment(idTreatment);
		
		this.isDone = isDone;
		this.billNumber = billNumber;
		this.note = note;
	}
	
	
	
	public long getId() {
		return this.id;
	}
	public void setId( long id ) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public long getidCustomer() {
		
		if( this.customer != null )
			return this.customer.getId();
		
		// errore
		return -1;
	}

	public void setidCustomer(long idCustomer) {
		if( this.customer == null ) this.customer = new Customer();
		this.customer.setId(idCustomer);
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer( Customer customer ) {
		this.customer = customer;
	}
	
	
	public long getidDoctor() {
		
		if( this.doctor != null )
			return this.doctor.getId();
		
		// errore
		return -1;
	}
	
	public void setidDoctor(long idDoctor) {
	
		if( this.doctor == null )
			this.doctor = new Employee();
		
		this.doctor.setId(idDoctor);
	}
	
	public Employee getDoctor() {
		return doctor;
	}

	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}

	public long getidTreatment() {
		
		if( this.treatment != null )
			return this.treatment.getId();
		
		// errore
		return -1;
	}
	
	public void setidTreatment(long idTreatment) {
		
		if( this.treatment == null )
			this.treatment = new Treatment();
		
		this.treatment.setId(idTreatment);
	}
	
	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	public int getisDone() {
		return isDone;
	}
	public void setisDone(int isDone) {
		this.isDone = isDone;
	}
	
	public String getbillNumber() {
		return billNumber;
	}
	public void setbillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
		
	
}
