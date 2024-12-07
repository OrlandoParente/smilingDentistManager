package sdms.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	
	private String name;
	private String surname;
	private String title;				// e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na,
	private String birthDate;
	private String phoneNumber;
	private String phoneNumber2; 		// Generalmente telefono di casa
	private String eMail;
	
	public Employee () {}
	
	public Employee(int id, String name, String surname, String title, String birthDate, String phoneNumber,
			String phoneNumber2, String eMail) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.title = title;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.phoneNumber2 = phoneNumber2;
		this.eMail = eMail;
	}

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


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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

	
	
	
	
	
}
