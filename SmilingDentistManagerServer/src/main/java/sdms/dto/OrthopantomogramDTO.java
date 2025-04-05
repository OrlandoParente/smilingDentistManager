package sdms.dto;

import java.time.LocalDate;

public class OrthopantomogramDTO {

	private Long id;
	
	private Long idCustomer;
	
	private LocalDate date;		// Not sure the database needs this
	
	private String filename;	// file fileName 
	private String format;		// png, jpg, DICOM (dcm), etc ...
	private String folder;		// This is for an elastic use of the Rest API. The Web Application use DEFAULT_ORTHOPANTOMOGRAM_FOLDER in FolderManager 
	
	public OrthopantomogramDTO() {
	
	}

	
	// TO STRING METHOD 
	@Override
	public String toString() {
		return "Orthopantomogram [id=" + id + ", idCustomer=" + idCustomer + ", date=" + date + ", filename=" + filename
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


	public Long getIdCustomer() {
		return idCustomer;
	}


	public void setIdCustomer( Long idCustomer ) {
		this.idCustomer = idCustomer;
	}


	public String getFilename() {
		return filename;
	}


	public void setFileName(String filename) {
		this.filename = filename;
	}


	public String getFolder() {
		return folder;
	}


	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	
	
}
