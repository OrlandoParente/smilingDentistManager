package sdms.model;

// @Entity
public class Customer {
	
	// @Id
	// @GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;						// non uso il cod_fiscale come id perché non so se il cliente è tenuto a rilasciarlo
	private String taxIdCode; 		// codice fiscale
	private String name;
	private String surname;
	private String birthCity;
	private String birthCityProvince;	// preferibilmente la sigla provincia, ma lascio spazio per il nome completo
	private String birthDate;
	private String residenceStreet;
	private String houseNumber;
	private String residenceCity;
	private String residenceCityCap;
	private String residenceProvince;   
	private String phoneNumber;
	private String phoneNumber2; 		// Generalmente telefono di casa
	private String eMail;
	public Customer(int id, String taxIdCode, String name, String surname, String birthCity, String birthCityProvince,
			String birthDate, String residenceStreet, String houseNumber, String residenceCity, String residenceCityCap,
			String residenceProvince, String phoneNumber, String phoneNumber2, String eMail) {
		super();
		this.id = id;
		this.taxIdCode = taxIdCode;
		this.name = name;
		this.surname = surname;
		this.birthCity = birthCity;
		this.birthCityProvince = birthCityProvince;
		this.birthDate = birthDate;
		this.residenceStreet = residenceStreet;
		this.houseNumber = houseNumber;
		this.residenceCity = residenceCity;
		this.residenceCityCap = residenceCityCap;
		this.residenceProvince = residenceProvince;
		this.phoneNumber = phoneNumber;
		this.phoneNumber2 = phoneNumber2;
		this.eMail = eMail;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", taxIdCode=" + taxIdCode + ", name=" + name + ", surname=" + surname
				+ ", birthCity=" + birthCity + ", birthCityProvince=" + birthCityProvince + ", birthDate=" + birthDate
				+ ", residenceStreet=" + residenceStreet + ", houseNumber=" + houseNumber + ", residenceCity="
				+ residenceCity + ", residenceCityCap=" + residenceCityCap + ", residenceProvince=" + residenceProvince
				+ ", phoneNumber=" + phoneNumber + ", phoneNumber2=" + phoneNumber2 + ", eMail=" + eMail + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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
	
	

	
	
}
