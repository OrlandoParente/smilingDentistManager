package sdms.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private final static Logger LOGGER = LoggerFactory.getLogger( Appointment.class );
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	// FOREIGN KEYS ######################################################
	
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

	private LocalDate date;
	private LocalTime time;
	private int isDone;  //bit default 0," 	 // di default l'appuntamento non è ancora avvenuto nel momento della registrazione
	private String invoiceNumber; // Numero fattura - generalmente ANNO + NUMERO intero
								 // I db non vuole salvarsi le fatture perché sarebbero dati ritondanti 
								 // ( cioè le fatture si possono costruire dai dati già presenti nel db )
								 // si limita a segnare quali appuntamenti sono stati già fatturati
	private String teeth;		// list of teeth (identificated by a number) separated a comma
	private Double payment;
	private String paymentMethod;
	private String notes;		 // eventualmente se serve specificare qualcosa

	// Empty Constructor 
	public Appointment() {}
	
	// CONVERT STR TEETH TO INTEGER LIST AND VICE-VERSA ------------------------------------
	
	// Useful method for convert the list of teeth involved in the appointment
	// in an list of integers
	public static List<Integer> teethToIntegerList( String strTeeth ){
		
		List<Integer> listOfTeeth = new ArrayList<Integer>();
		
		// Avoid error by empty strTeeth
		if( strTeeth == null || strTeeth.trim().equals("") )
			return listOfTeeth;
		
		// remove blank spaces if there are any 
		strTeeth.replaceAll("\\s", "");
		
		// convert the number and put in the listOfTeeth
		for( String strNum : strTeeth.split(",") ) {
			listOfTeeth.add( Integer.parseInt(strNum) );
		}
		
		LOGGER.info("List of integer teeth " + listOfTeeth);
		
		// return the list of Teeth
		return listOfTeeth;
	}
	
	public static String IntegerListTeethToStr ( List<Integer> teeth ) {
		
		LOGGER.info("Inside IntegerListTeethToStr ");
		
		String strTeeth = "";
		
		// return "" if the list is null or empty
		if( teeth == null || teeth.size() == 0 )
			return strTeeth;
		
		boolean first = true;
		
		for( Integer tooth : teeth ) {
			
			if( ! first ) {
				strTeeth += "," + tooth;
 			} else {	// comma not needed on first tooth 
 				strTeeth = "" + tooth;
 				first = false;
 			}
			
		}
		
		LOGGER.info("String teeth " + strTeeth);
		
		return strTeeth;
	}
	
	// -------------------------------------------------------------------------------------
	
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
	
	public long getidCustomer() {
		
		if( this.customer != null )
			return this.customer.getId();
		
		// errore
		return -1;
	}

	// WARNING: Can bring to error cause you attempt to change an id managed by Hibernate
//	public void setidCustomer(long idCustomer) {
//		
//		if( this.customer == null ) 
//			this.customer = new Customer();
//		
//		this.customer.setId(idCustomer);
//	}
	
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
	
	// WARNING: Can bring to error cause you attempt to change an id managed by Hibernate
//	public void setidDoctor(long idDoctor) {
//	
//		if( this.doctor == null )
//			this.doctor = new Employee();
//		
//		this.doctor.setId(idDoctor);
//	}
	
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
	
	// WARNING: Can bring to error cause you attempt to change an id managed by Hibernate
//	public void setidTreatment(long idTreatment) {
//		
//		if( this.treatment == null )
//			this.treatment = new Treatment();
//		
//		this.treatment.setId(idTreatment);
//	}
	
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
	
	public String getTeeth() {
		return teeth;
	}

	public void setTeeth(String teeth) {
		this.teeth = teeth;
	}
	
	public void setTeeth(List<Integer> teeth) {
		this.teeth = Appointment.IntegerListTeethToStr(teeth);
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
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

	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
		
	
}
