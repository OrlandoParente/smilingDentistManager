package sdms.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "work_period" )
public class WorkPeriod {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	// FOREIGN KEYS ######################################################
	
	@ManyToOne
	@JoinColumn( name = "id_employee" )
	private Employee employee;
	
	// ###################################################################
	
	private LocalDate startDate;
	private LocalDate endDate;
	private String workingAgreement;
	private String notes;
	
	// Empty Constructor 
	public WorkPeriod() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
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
