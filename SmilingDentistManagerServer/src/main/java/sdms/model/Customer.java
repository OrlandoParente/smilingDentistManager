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
@Table( name = "customer" )
public class Customer {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;	// non uso il cod_fiscale come id perché non so se il cliente è tenuto a rilasciarlo
	
	@OneToMany( mappedBy = "customer" )
	private List<Appointment> appointments;
	
	@OneToMany( mappedBy = "customer" )
	private List<HasMedicalHistory> hasMedicalHistories;
	
	@OneToMany( mappedBy = "customer" )
	private List<Expense> expenses;
	
	private String taxIdCode; 			// codice fiscale
	private String name;
	private String surname;
	private String birthCity;
	private String birthCityProvince;	// preferibilmente la sigla provincia, ma lascio spazio per il nome completo
	private LocalDate birthDate;
	private String residenceStreet;
	private String houseNumber;
	private String residenceCity;
	private String residenceCityCap;
	private String residenceProvince;   
	private String phoneNumber;
	private String phoneNumber2; 		// Generalmente telefono di casa
	private String language;
	private String eMail;
	private String password;
	private int permission;				// Role di Spring Security
	
	// for use LocalDate in Containing method of JpaRepository
	private String birthDateString;
	
	// Empty Constructor 
	public Customer() {}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", appointments=" + appointments + ", medicalHistories= [ medical histories ... ]" /* + hasMedicalHistories */
				+ ", expenses=" + expenses + ", taxIdCode=" + taxIdCode + ", name=" + name + ", surname=" + surname
				+ ", birthCity=" + birthCity + ", birthCityProvince=" + birthCityProvince + ", birthDate=" + birthDate
				+ ", residenceStreet=" + residenceStreet + ", houseNumber=" + houseNumber + ", residenceCity="
				+ residenceCity + ", residenceCityCap=" + residenceCityCap + ", residenceProvince=" + residenceProvince
				+ ", phoneNumber=" + phoneNumber + ", phoneNumber2=" + phoneNumber2 + ", eMail=" + eMail + ", password="
				+ password + ", permission=" + permission + "]";
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaxIdCode() {
		return taxIdCode;
	}
	public void setTaxIdCode(String taxIdCode) {
		this.taxIdCode = taxIdCode;
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
	public String getBirthCity() {
		return birthCity;
	}
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}
	public String getBirthCityProvince() {
		return birthCityProvince;
	}
	public void setBirthCityProvince(String birthCityProvince) {
		this.birthCityProvince = birthCityProvince;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	// for use LocalDate in Containing method of JpaRepository
	public String getBirthDateString() {
		return this.birthDateString;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		// for use LocalDate in Containing method of JpaRepository
		this.birthDateString = birthDate != null ? birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))  : null;
	}
	
	public String getResidenceStreet() {
		return residenceStreet;
	}
	public void setResidenceStreet(String residenceStreet) {
		this.residenceStreet = residenceStreet;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getResidenceCity() {
		return residenceCity;
	}
	public void setResidenceCity(String residenceCity) {
		this.residenceCity = residenceCity;
	}
	public String getResidenceCityCap() {
		return residenceCityCap;
	}
	public void setResidenceCityCap(String residenceCityCap) {
		this.residenceCityCap = residenceCityCap;
	}
	public String getResidenceProvince() {
		return residenceProvince;
	}
	public void setResidenceProvince(String residenceProvince) {
		this.residenceProvince = residenceProvince;
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

	public List<HasMedicalHistory> getHasMedicalHistories() {
		return hasMedicalHistories;
	}

	public void setHasMedicalHistories(List<HasMedicalHistory> hasMedicalHistories) {
		this.hasMedicalHistories = hasMedicalHistories;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
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
