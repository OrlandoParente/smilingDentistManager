package sdms.dto;

public class EmployeeDTO {
	
	private long id;  					// non uso il cod_fiscale come id perché non so se il dipendente è tenuto a rilasciarlo
		
	private String name;
	private String surname;
	private String title;				// e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na,
	private String birthDate;
	private String phoneNumber;
	private String phoneNumber2; 		// Generalmente telefono di casa
	private String eMail;
	
	public EmployeeDTO () {}
	
	public EmployeeDTO(int id, String name, String surname, String title, String birthDate, String phoneNumber,
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


	public String getbirthDate() {
		return birthDate;
	}


	public void setbirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public String getphoneNumber() {
		return phoneNumber;
	}


	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getphoneNumber2() {
		return phoneNumber2;
	}


	public void setphoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}


	public String geteMail() {
		return eMail;
	}


	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	
	
	
	
	
}
