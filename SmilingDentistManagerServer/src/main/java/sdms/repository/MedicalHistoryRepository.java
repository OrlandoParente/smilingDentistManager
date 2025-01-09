package sdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sdms.model.MedicalHistory;
import java.util.List;


@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {


	// CREATE ---------------------------------------------------------------
	
	// UPDATE ---------------------------------------------------------------
	
	// READ -----------------------------------------------------------------
	List<MedicalHistory> findByType( String type );
	List<MedicalHistory> findByCategory(String category);
	
	List<MedicalHistory> findByTypeAndCategory( String type, String category );
	
	@Query("SELECT DISTINCT mh.type FROM MedicalHistory mh")
	List<String> findDistinctTypes();

	@Query("SELECT DISTINCT mh.category FROM MedicalHistory mh")
	List<String> findDistinctCategories();
	
	// DELETE ---------------------------------------------------------------

}
