package sdms.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Orthopantomogram {
	

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@ManyToOne
	@JoinColumn( name="id_customer" )
	private Customer customer;
	
	private LocalDate date;		// Not sure the database needs this
	
	private String filename;	// file fileName 
	private String format;		// png, jpg, DICOM (dcm), etc ...
	private String folder;		// This is for an elastic use of the Rest API. The Web Application use DEFAULT_ORTHOPANTOMOGRAM_FOLDER in FolderManager 
	
	public Orthopantomogram() {
		// Set default values
		this.date = null;
	}

	
	// TO STRING METHOD 
	@Override
	public String toString() {
		return "Orthopantomogram [id=" + id + ", customer=" + customer + ", date=" + date + ", filename=" + filename
				+ ", format=" + format + ", folder=" + folder + "]";
	}

	
	// GETTERS AND SETTERS 
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getFolder() {
		return folder;
	}


	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	
	
}
