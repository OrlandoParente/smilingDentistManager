package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.HasMedicalHistory;
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
	
	@Test
	public void testPostHasMedicalHistory() {
		
		// Simulate the database ----------------------------
		HasMedicalHistory hasMedicalHistory = new HasMedicalHistory();
		Long idHMH = 1L;
		hasMedicalHistory.setId(idHMH);
		// --------------------------------------------------
							
		// test ---------------------------------------------
		hasMedicalHistoryService.postHasMedicalHistory(hasMedicalHistory);		
		// --------------------------------------------------
							
		// check --------------------------------------------
		verify( hasMedicalHistoryRepository, times(1) ).save( hasMedicalHistory );
		// --------------------------------------------------
	}
	
	// READ
	// HasMedicalHistory getHasMedicalHistoryById(long id);
	
	@Test
	public void testGetHasMedicalHistoryById () {
		
		// Simulate the database ----------------------------
		HasMedicalHistory hasMedicalHistory = new HasMedicalHistory();
		Long idHMH = 1L;
		hasMedicalHistory.setId(idHMH);
		
		when( hasMedicalHistoryRepository.findById(idHMH) ).thenReturn( Optional.of(hasMedicalHistory) );
		// --------------------------------------------------
							
		// test ---------------------------------------------
		HasMedicalHistory result = hasMedicalHistoryService.getHasMedicalHistoryById(idHMH);
		// --------------------------------------------------
							
		// check --------------------------------------------
		assertEquals(hasMedicalHistory.getId(), result.getId());
		// --------------------------------------------------
	}
	
	// List<HasMedicalHistory> getHasMedicalHistories();
	
//	@Test
	public void testGetHasMedicalHistories() {
		
		// Simulate the database ----------------------------
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		
		// --------------------------------------------------
							
		// check --------------------------------------------
		
		// --------------------------------------------------
	}
	
	// List<HasMedicalHistory> getHasMedicalHistoriesByMedicalHistory( MedicalHistory medicalHistory );
	
//	@Test
	public void testGetHasMedicalHistoriesByMedicalHistory() {
		
		// Simulate the database ----------------------------
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		
		// --------------------------------------------------
							
		// check --------------------------------------------
		
		// --------------------------------------------------
	}
	
	// List<HasMedicalHistory> getHasMedicalHistoriesByCustomer(Customer customer);
	
//	@Test
	public void testGetHasMedicalHistoriesByCustomer() {
		
		// Simulate the database ----------------------------
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		
		// --------------------------------------------------
							
		// check --------------------------------------------
		
		// --------------------------------------------------		
	}
	
	// UPDATE
	// void putHasMedicalHistory( HasMedicalHistory hasMedicalHistory );
	
//	@Test
	public void testPutHasMedicalHistory() {
		
		// Simulate the database ----------------------------
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		
		// --------------------------------------------------
							
		// check --------------------------------------------
		
		// --------------------------------------------------
		
	}
	
	// DELETE
	// void deleteHasMedicalHistoryById( long id );
	
//	@Test
	public void testDeleteHasMedicalHistoryById() {
		
		// Simulate the database ----------------------------
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		
		// --------------------------------------------------
							
		// check --------------------------------------------
		
		// --------------------------------------------------
		
	}
	
	// void deleteHasMedicalHistoryByIdCustomerAndIdMedicalHistory( long idCustomer, long idMedicalHistory );

//	@Test
	public void testDeleteHasMedicalHistoryByIdCustomerAndIdMedicalHistory() {
		
		// Simulate the database ----------------------------
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		
		// --------------------------------------------------
							
		// check --------------------------------------------
		
		// --------------------------------------------------
		
	}
	
}
