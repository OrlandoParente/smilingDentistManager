package sdms.dto;

public class ExpenseDTO {

	private long id;
	
	// FOREIGN KEYS ######################################################
	private long idCustomer;
	private long idEmployee;
	private long idDentalMaterial;
	
	// ###################################################################
	
	private String date;
	private String description;
	private double amount;
	private String tag;
	
	public ExpenseDTO() {}

	
	public ExpenseDTO(long id, long idCustomer, long idEmployee, long idDentalMaterial, String date, String description,
			double amount, String tag) {
		super();
		this.id = id;
		this.idCustomer = idCustomer;
		this.idEmployee = idEmployee;
		this.idDentalMaterial = idDentalMaterial;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.tag = tag;
	}


	public Long getIdCustomer() {
		return idCustomer;
	}


	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}


	public Long getIdEmployee() {
		return idEmployee;
	}


	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
	}


	public Long getIdDentalMaterial() {
		return idDentalMaterial;
	}


	public void setIdDentalMaterial(Long idDentalMaterial) {
		this.idDentalMaterial = idDentalMaterial;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
