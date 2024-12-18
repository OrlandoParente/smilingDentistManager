package sdms.dto;

public class AppointmentDTO {
	
	private long id;
	
	// FOREIGN KEYS ######################################################
	private long idCustomer;
	private long idDoctor;
	private long idTreatment;
	
	
	// ###################################################################
	
	private String date;
	private String time;
	private int isDone;  //bit default 0," 	 // di default l'appuntamento non è ancora avvenuto nel momento della registrazione
	private String billNumber; // Numero fattura - generalmente ANNO + NUMERO intero
								 // I db non vuole salvarsi le fatture perché sarebbero dati ritondanti 
								 // ( cioè le fatture si possono costruire dai dati già presenti nel db )
								 // si limita a segnare quali appuntamenti sono stati già fatturati
	private String note;		 // eventualmente se serve specificare qualcosa

	public AppointmentDTO() {}
	
	// isDone = 0 di default
	public AppointmentDTO(long id, String date, String time, long idCustomer, long idDoctor,
			long idTreatment, String billNumber, String note) {
		this(  id, date, time, idCustomer, idDoctor, idTreatment, 0, billNumber, note );
	}
	
	public AppointmentDTO(long id, String date, String time, long idCustomer, long idDoctor,
			long idTreatment, int isDone, String billNumber, String note) {
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
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
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
	
}
