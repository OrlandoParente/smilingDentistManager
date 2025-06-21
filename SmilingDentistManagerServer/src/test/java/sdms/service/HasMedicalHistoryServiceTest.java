package sdms.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sdms.repository.HasMedicalHistoryRepository;

/*
 * // CREATE
	void postHasMedicalHistory( HasMedicalHistory hasMedicalHistory );
	
	// READ
	HasMedicalHistory getHasMedicalHistoryById(long id);
	
	List<HasMedicalHistory> getHasMedicalHistories();
	List<HasMedicalHistory> getHasMedicalHistoriesByMedicalHistory( MedicalHistory medicalHistory );
	List<HasMedicalHistory> getHasMedicalHistoriesByCustomer(Customer customer);
	
	// UPDATE
	void putHasMedicalHistory( HasMedicalHistory hasMedicalHistory );
	
	// DELETE
	void deleteHasMedicalHistoryById( long id );
	void deleteHasMedicalHistoryByIdCustomerAndIdMedicalHistory( long idCustomer, long idMedicalHistory );
 */

class HasMedicalHistoryServiceTest {

	@Mock
	HasMedicalHistoryRepository hasMedicalHistoryRepository;
	
	@InjectMocks
	HasMedicalHistoryService hasMedicalHistoryService;
	
	// enable mochito annotations
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	// CREATE
	// void postHasMedicalHistory( HasMedicalHistory hasMedicalHistory );
	
//	@Test
	public void testPostHasMedicalHistory() {
		
		// Simulate the database ----------------------------
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		
		// --------------------------------------------------
							
		// check --------------------------------------------
		
		// --------------------------------------------------
	}
	
	// READ
	// HasMedicalHistory getHasMedicalHistoryById(long id);
	
//	@Test
	public void testGetHasMedicalHistoryById () {
		
		// Simulate the database ----------------------------
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		
		// --------------------------------------------------
							
		// check --------------------------------------------
		
		// --------------------------------------------------
	}
	
	// List<HasMedicalHistory> getHasMedicalHistories();
	
//	@Test
	public void testGetHasMedicalHistories() {
		
	}
	
	// List<HasMedicalHistory> getHasMedicalHistoriesByMedicalHistory( MedicalHistory medicalHistory );
	
//	@Test
	public void testGetHasMedicalHistoriesByMedicalHistory() {
		
	}
	
	// List<HasMedicalHistory> getHasMedicalHistoriesByCustomer(Customer customer);
	
//	@Test
	public void testGetHasMedicalHistoriesByCustomer() {
		
	}
	
	// UPDATE
	// void putHasMedicalHistory( HasMedicalHistory hasMedicalHistory );
	
//	@Test
	public void testPutHasMedicalHistory() {
		
	}
	
	// DELETE
	// void deleteHasMedicalHistoryById( long id );
	
//	@Test
	public void testDeleteHasMedicalHistoryById() {
		
	}
	
	// void deleteHasMedicalHistoryByIdCustomerAndIdMedicalHistory( long idCustomer, long idMedicalHistory );

//	@Test
	public void testDeleteHasMedicalHistoryByIdCustomerAndIdMedicalHistory() {
		
	}
	
}
