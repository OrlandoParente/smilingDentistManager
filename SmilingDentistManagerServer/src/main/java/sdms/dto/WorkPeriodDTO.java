package sdms.dto;

import java.time.LocalDate;

public class WorkPeriodDTO {

	// primary key
	private Long id;

	// foreign key
	private long idEmployee;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private String workingAgreement;
	private String notes;
	
	
	public WorkPeriodDTO() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public long getIdEmployee() {
		return idEmployee;
	}


	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public String getWorkingAgreement() {
		return workingAgreement;
	}


	public void setWorkingAgreement(String workingAgreement) {
		this.workingAgreement = workingAgreement;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
