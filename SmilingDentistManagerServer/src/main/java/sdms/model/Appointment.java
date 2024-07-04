package sdms.model;

public class Appointment {
	
	long id;
	private String date;
	private String time;
	private int idCustomer;
	private int idDoctor;
	private int idTreatment;
	private int isDone;  //bit default 0," 	 // di default l'appuntamento non è ancora avvenuto nel momento della registrazione
	private String billNumber; // Numero fattura - generalmente ANNO + NUMERO intero
								 // I db non vuole salvarsi le fatture perché sarebbero dati ritondanti 
								 // ( cioè le fatture si possono costruire dai dati già presenti nel db )
								 // si limita a segnare quali appuntamenti sono stati già fatturati
	private String note;		 // eventualmente se serve specificare qualcosa

	
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
		this.idCustomer = idCustomer;
		this.idDoctor = idDoctor;
		this.idTreatment = idTreatment;
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
	
	public int getidCustomer() {
		return idCustomer;
	}
	public void setidCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	
	public int getidDoctor() {
		return idDoctor;
	}
	public void setidDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}
	
	public int getidTreatment() {
		return idTreatment;
	}
	public void setidTreatment(int idTreatment) {
		this.idTreatment = idTreatment;
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
