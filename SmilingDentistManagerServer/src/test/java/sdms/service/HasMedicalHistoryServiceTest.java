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

// <<<<<<<<<<<<<<<<<<<<<-------------------------- CODICE SOLO DA DECOMMENTARE E CONTROLLARE
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
		
//        HasMedicalHistory hmh1 = new HasMedicalHistory();
//        HasMedicalHistory hmh2 = new HasMedicalHistory();
//        List<HasMedicalHistory> list = Arrays.asList(hmh1, hmh2);
//
//        when(hasMedicalHistoryRepository.findAll()).thenReturn(list);
//
//        List<HasMedicalHistory> result = hasMedicalHistoryService.getHasMedicalHistories();
//        assertEquals(2, result.size());
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
		
//        MedicalHistory mh = new MedicalHistory();
//        HasMedicalHistory hmh = new HasMedicalHistory();
//        List<HasMedicalHistory> list = Arrays.asList(hmh);
//
//        when(hasMedicalHistoryRepository.findByMedicalHistory(mh)).thenReturn(list);
//
//        List<HasMedicalHistory> result = hasMedicalHistoryService.getHasMedicalHistoriesByMedicalHistory(mh);
//        assertEquals(1, result.size());
		
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
		
//        Customer customer = new Customer();
//        HasMedicalHistory hmh = new HasMedicalHistory();
//        List<HasMedicalHistory> list = Arrays.asList(hmh);
//
//        when(hasMedicalHistoryRepository.findByCustomer(customer)).thenReturn(list);
//
//        List<HasMedicalHistory> result = hasMedicalHistoryService.getHasMedicalHistoriesByCustomer(customer);
//        assertEquals(1, result.size());
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
		
//        HasMedicalHistory hmh = new HasMedicalHistory();
//        hasMedicalHistoryService.putHasMedicalHistory(hmh);
//        verify(hasMedicalHistoryRepository, times(1)).save(hmh);
		
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
		
//        HasMedicalHistory hmh = new HasMedicalHistory();
//        hmh.setId(1L);
//
//        when(hasMedicalHistoryRepository.findById(1L)).thenReturn(Optional.of(hmh));
//
//        hasMedicalHistoryService.deleteHasMedicalHistoryById(1L);
//        verify(hasMedicalHistoryRepository, times(1)).delete(hmh);
		
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
		
//        Customer customer = new Customer();
//        customer.setId(1L);
//
//        MedicalHistory mh = new MedicalHistory();
//        mh.setId(2L);
//
//        HasMedicalHistory hmh = new HasMedicalHistory();
//        hmh.setCustomer(customer);
//        hmh.setMedicalHistory(mh);
//        List<HasMedicalHistory> list = Arrays.asList(hmh);
//
//        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
//        when(medicalHistoryRepository.findById(2L)).thenReturn(Optional.of(mh));
//        when(hasMedicalHistoryRepository.findByCustomer(customer)).thenReturn(list);
//
//        hasMedicalHistoryService.deleteHasMedicalHistoryByIdCustomerAndIdMedicalHistory(1L, 2L);
//        verify(hasMedicalHistoryRepository, times(1)).delete(hmh);
		
	}
	
}
