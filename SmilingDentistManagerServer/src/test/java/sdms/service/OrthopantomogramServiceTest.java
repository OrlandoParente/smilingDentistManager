package sdms.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

/*
 * 	public Orthopantomogram getOrthopantomogramById( Long id );
	
	public List<Orthopantomogram> getOrthopantomogramsByCustomerAndFilename( Long idCustomer, String filename );
	public List<Orthopantomogram> getOrthopantomograms();
	public List<Orthopantomogram> getOrthopantomogramsByCustomer( Long idCustomer );

	public void uploadOrthopantomogram( Long idCustomer, MultipartFile orthopantomogram, String format );
	public void uploadOrthopantomogram( Long idCustomer,String folderPath, MultipartFile orthopantomogram, String format );
	
	public void uploadOrthopantomogram( Long idCustomer, MultipartFile orthopantomogram, String format, LocalDate date );
	public void uploadOrthopantomogram( Long idCustomer, String folderPath, MultipartFile orthopantomogram, String format, LocalDate date );
	
	public void putOrthopantomogram( Orthopantomogram orthopantomogram );
	
	public void deleteOrthopantomogram( Long id );
 */

class OrthopantomogramServiceTest {

	// enable mochito annotations
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
