package sdms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sdms.model.Customer;
import sdms.model.Orthopantomogram;

public interface OrthopantomogramServiceInterface {

	public Orthopantomogram getOrthopantomogramById( Long id );
	public Orthopantomogram getOrthopantomogramByFileName( String filename );
	
	public List<Orthopantomogram> getOrthopantomograms();
	public List<Orthopantomogram> getOrthopantomogramsByCustomer( Customer customer );
	
	public void uploadOrthopantomogram( Customer customer, MultipartFile orthopantomogram, String format );
	public void uploadOrthopantomogram( String folderPath, MultipartFile orthopantomogram, String format );
	
	public void putOrthopantomogram( Orthopantomogram orthopantomogram );
	
	public void deleteOrthopantomogram( Long id );
	public void deleteOrthopantomogram( String filename );
}
