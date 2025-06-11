package sdms.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.DentalMaterial;
import sdms.repository.DentalMaterialRepository;

/*
 * 	
	// CREATE ----------------------------------------------------
	public void postDentalMaterial( DentalMaterial dentalMaterial );
	
	// READ ------------------------------------------------------
	public DentalMaterial getDentalMaterialById( long id );
	public List<DentalMaterial> getDentalMaterials();
	
	// UPDATE ----------------------------------------------------
	public void putDentalMaterial( DentalMaterial dentalMaterial );
	public void increaseDentalMaterialQuantity( long idDentalMaterial, int quantity );
	public void decreaseDentalMaterialQuantity( long idDentalMaterial, int quantity );
	
	// DELETE ----------------------------------------------------
	public void deleteDentalMaterial( long id );
	
}
 */

class DentalMaterialServiceTest {

	@Mock
	private DentalMaterialRepository dentalMaterialRepository;
	
	@InjectMocks
	private DentalMaterialService dentalMaterialService;
	
	// enable mochito annotations
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	// CREATE ----------------------------------------------------
	// public void postDentalMaterial( DentalMaterial dentalMaterial );
	
	@Test
	public void testPostDentalMaterial() {
		
		// Simulate the database ----------------------------
		DentalMaterial dentalMaterial = new DentalMaterial();
		Long id = 1L;
		dentalMaterial.setId(id);
		// --------------------------------------------------
							
		// test ---------------------------------------------
		dentalMaterialService.postDentalMaterial(dentalMaterial);
		// --------------------------------------------------
							
		// check --------------------------------------------
		verify( dentalMaterialRepository, times(1) ).save( dentalMaterial );
		// --------------------------------------------------
	}
	
	// READ ------------------------------------------------------
	// public DentalMaterial getDentalMaterialById( long id );
	
	
	public void testGetDentalMaterial() {
	
		
	}
	
	
	// public List<DentalMaterial> getDentalMaterials();
	
	
	// UPDATE ----------------------------------------------------
	// public void putDentalMaterial( DentalMaterial dentalMaterial );
	
	
	// public void increaseDentalMaterialQuantity( long idDentalMaterial, int quantity );
	
	
	// public void decreaseDentalMaterialQuantity( long idDentalMaterial, int quantity );
	
	// DELETE ----------------------------------------------------
	// public void deleteDentalMaterial( long id );

}
