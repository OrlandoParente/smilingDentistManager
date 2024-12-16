package sdms.dto;

import java.time.LocalDate;

public class EmployeeDTO {
	
	private long id;  					// non uso il cod_fiscale come id perché non so se il dipendente è tenuto a rilasciarlo
		
	private String name;
	private String surname;
	private String title;				// e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na,
	private LocalDate birthDate;
	private String phoneNumber;
	private String phoneNumber2; 		// Generalmente telefono di casa
	private String eMail;
	private String password;
	private int permission;				// il role di Spring Secure
	
	public EmployeeDTO () {}
	
	// We need this? There is already ModelMapper for convert Obj to DTO and vice versa 
	public EmployeeDTO(int id, String name, String surname, String title, LocalDate birthDate, String phoneNumber,
			String phoneNumber2, String eMail, String passowrd, int permission) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.title = title;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.phoneNumber2 = phoneNumber2;
		this.eMail = eMail;
		this.password = passowrd;
		this.permission = permission;
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

	public String geteMail() {
		return eMail;
	}


	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	
	
}
