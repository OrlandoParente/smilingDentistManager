package sdms.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import sdms.util.UserRoleManager;

@Entity
@Table( name = "employee" )
public class Employee {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;  					// non uso il cod_fiscale come id perché non so se il dipendente è tenuto a rilasciarlo
	
	@OneToMany( mappedBy = "doctor" )
	private List<Appointment> appointments;
	
	@OneToMany( mappedBy = "employee" )
	private List<Expense> expenses;
	
	@OneToMany( mappedBy = "employee" )
	private List<HasProfessionalRole> hasProfessionalRoles;
	
	@OneToMany( mappedBy = "employee" )
	List<WorkPeriod> workPeriods;
	
	private String name;
	private String surname;
	private String title;				// e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na,
	private LocalDate birthDate;
	private Double salary;
	private String phoneNumber;
	private String phoneNumber2; 		// Generalmente telefono di casa
	private String language;
	private String eMail;
	private String password;
	private int permission;
	
	
	// for use LocalDate in Containing method of JpaRepository
	private String birthDateString;
	
	// Empty Constructor 
	public Employee () {}
	

	// GETTERS AND SETTERS
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		// for use LocalDate in Containing method of JpaRepository
		this.birthDateString = birthDate != null ? birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
	}
	
	// for use LocalDate in Containing method of JpaRepository
	public String getBirthDateString() {
		return this.birthDateString;
	}
	


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getPhoneNumber2() {
		return phoneNumber2;
	}


	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}


	public String geteMail() {
		return eMail;
	}


	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<HasProfessionalRole> getHasProfessionalRoles() {
		return hasProfessionalRoles;
	}

	public void setHasProfessionalRoles(List<HasProfessionalRole> hasProfessionalRoles) {
		this.hasProfessionalRoles = hasProfessionalRoles;
	}
	
	public List<WorkPeriod> getWorkPeriods() {
		return workPeriods;
	}

	public void setWorkPeriods(List<WorkPeriod> workPeriods) {
		this.workPeriods = workPeriods;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	public Double getSalary() {
		return salary;
	}


	public void setSalary(Double salary) {
		this.salary = salary;
	}

	

	// ----------------------------------------------------------------------------------

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getRole() {
		return UserRoleManager.getRoleFromPermission( this.getPermission() );
	}
	
	public void setRole( String role ) {
		this.setPermission( UserRoleManager.getPermissionFromRole(role) );
	}	
	
	// ----------------------------------------------------------------------------------
	
	
}
