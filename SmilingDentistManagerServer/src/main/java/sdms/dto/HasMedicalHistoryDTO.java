package sdms.dto;

public class HasMedicalHistoryDTO {
	
	private Long id;
	
	// FOREIGN KEYS ######################################################
	
	private long idCustomer;
	private long idMedicalHistory;
	
	// ###################################################################
	
	private String notes;

	public HasMedicalHistoryDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
