package sdms.dto;

public class HasMedicalHistoryDTO {
	
	private long id;
	
	// FOREIGN KEYS ######################################################
	
	private long idCustomer;
	private long idMedicalHistory;
	
	// ###################################################################
	
	private String notes;

	public HasMedicalHistoryDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public long getIdMedicalHistory() {
		return idMedicalHistory;
	}

	public void setIdMedicalHistory(long idMedicalHistory) {
		this.idMedicalHistory = idMedicalHistory;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
