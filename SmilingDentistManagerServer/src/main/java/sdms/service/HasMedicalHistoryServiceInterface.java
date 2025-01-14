package sdms.service;

import java.util.List;
import sdms.model.Customer;
import sdms.model.HasMedicalHistory;
import sdms.model.MedicalHistory;

public interface HasMedicalHistoryServiceInterface {
	
	// CREATE
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
}
