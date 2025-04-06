package sdms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sdms.model.Orthopantomogram;

public interface OrthopantomogramServiceInterface {

	public Orthopantomogram getOrthopantomogramById( Long id );
	
	public List<Orthopantomogram> getOrthopantomogramsByCustomerAndFilename( Long idCustomer, String filename );
	public List<Orthopantomogram> getOrthopantomograms();
	public List<Orthopantomogram> getOrthopantomogramsByCustomer( Long idCustomer );

	public void uploadOrthopantomogram( Long idCustomer, MultipartFile orthopantomogram, String format );
	public void uploadOrthopantomogram( String folderPath, MultipartFile orthopantomogram, String format );
	
	public void uploadOrthopantomogram( Long idCustomer, MultipartFile orthopantomogram, String format, LocalDate date );
	public void uploadOrthopantomogram( String folderPath, MultipartFile orthopantomogram, String format, LocalDate date );
	
	public void putOrthopantomogram( Orthopantomogram orthopantomogram );
	
	public void deleteOrthopantomogram( Long id );
}
