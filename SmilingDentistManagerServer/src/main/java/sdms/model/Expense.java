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
@Table( name = "expense" )
public class Expense {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	// FOREIGN KEYS ######################################################
	
	@ManyToOne
	@JoinColumn( name = "id_customer" )
	private Customer customer;
	
	@ManyToOne
	@JoinColumn( name = "id_employee" )
	private Employee employee;
	
	@ManyToOne
	@JoinColumn( name = "id_dental_material" )
	private DentalMaterial dentalMaterial;
	
	// ###################################################################
	
	private LocalDate date;
	private String description;
	private double amount;
	private String tag;
	
	// Empty Constructor 
	public Expense() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public DentalMaterial getDentalMaterial() {
		return dentalMaterial;
	}

	public void setDentalMaterial(DentalMaterial dentalMaterial) {
		this.dentalMaterial = dentalMaterial;
	}
	
}
