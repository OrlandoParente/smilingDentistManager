package sdms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "has_professional_role" )
public class HasProfessionalRole {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	// FOREIGN KEYS #######################################################
	
	// --- Li lascio per mantenere la compatibilità con il Client Swing---
	// lascio i corrispettivi setter e getter perchè cosi, jpa mi da problemi 
	// anche senza l'annotazione @Column li mette in conflitto con i rispettivi sotto
	
	// private int idEmployee; 
	// private int idProfessionalRole;
	
	// --------------------------------------------------------------------
	
	@ManyToOne
	@JoinColumn( name = "id_employee" )
	private Employee employee;
	
	@ManyToOne
	@JoinColumn( name = "id_professional_role" )
	private ProfessionalRole professionalRole;
	
	// ####################################################################
	
	public HasProfessionalRole() {}
	
	public HasProfessionalRole(long id, int idEmployee, int idProfessionalRole) {
		super();
		
		this.id = id;
		
		// this.idEmployee = idEmployee;
		this.setIdEmployee(idEmployee);

		//this.idProfessionalRole = idProfessionalRole;
		this.setIdProfessionalRole(idProfessionalRole);
	}
	
	// GETTERS AND SETTERS
	public long getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getIdEmployee() {
		
		if( this.employee != null )
			return this.employee.getId();
		
		// errore
		return -1;
	}
	
	public void setIdEmployee(long idEmployee) {
		
		if(this.employee == null )	
			this.employee = new Employee();
		
		this.employee.setId(idEmployee);
	}
	
	public long getIdProfessionalRole() {
		
		if( this.professionalRole != null )
			return this.professionalRole.getId();
		
		// errore
		return -1;
	}
	
	public void setIdProfessionalRole(long idProfessionalRole) {
		
		if( this.professionalRole == null )
			this.professionalRole = new ProfessionalRole();
		
		this.professionalRole.setId(idProfessionalRole);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ProfessionalRole getProfessionalRole() {
		return professionalRole;
	}

	public void setProfessionalRole(ProfessionalRole professionalRole) {
		this.professionalRole = professionalRole;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	

}
