package sdms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sdms.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// CREATE ---------------------------------------------------------------
	
	
	// UPDATE ---------------------------------------------------------------	
	
	// READ -----------------------------------------------------------------
	@Query("SELECT MAX(c.id) FROM Customer c")
	Long findMaxId();
	
	Optional<Customer> findByEMail(String eMail);
	
	// We can use the follow query instead of the JpaInteface =======================================================
															  
	//	return conn.createStatement().executeQuery("SELECT* FROM customer WHERE tax_id_code LIKE '%"+ key_word + "%' "
	//	+ "OR name LIKE '%"+ key_word + "%' OR surname OR birth_city LIKE '%"+ key_word + "%' "
	//	+ "OR birth_city_province LIKE '%"+ key_word + "%' "
	//	+ "OR birth_date LIKE '%"+ key_word + "%' OR residence_street LIKE '%"+ key_word + "%'"
	//	+ "OR house_number LIKE '%"+ key_word + "%'"
	//	+ "OR residence_city LIKE '%"+ key_word + "%' OR residence_city_cap LIKE '%"+ key_word + "%'"
	//	+ "OR residence_province LIKE '%"+ key_word + "%' OR phone_number LIKE '%"+ key_word + "%'"
	//	+ "OR phone_number_2 LIKE '%"+ key_word + "%' OR e_mail LIKE '%"+ key_word + "%'");
		
	//	ResultSet getCustomersByPartialKeyWordOverAllFields( String key_word ) throws SQLException;
	
	@Query("SELECT c FROM Customer c WHERE c.taxIdCode LIKE %:keyWord% "
			+ " OR c.name LIKE %:keyWord% OR c.surname LIKE %:keyWord% "
			+ " OR c.birthCity LIKE %:keyWord% OR c.birthCityProvince LIKE %:keyWord% "
			+ " OR c.birthDate LIKE %:keyWord%  OR c.residenceStreet LIKE %:keyWord% "
			+ " OR c.houseNumber LIKE %:keyWord% OR c.residenceCity LIKE %:keyWord% "
			+ " OR c.residenceCityCap LIKE %:keyWord% OR c.residenceProvince LIKE %:keyWord% "
			+ " OR c.phoneNumber LIKE %:keyWord% OR c.phoneNumber2 LIKE %:keyWord%  OR c.eMail LIKE %:keyWord%")
	public List<Customer> findCustomersByPartialKeyWordOverAllFields( @Param("keyWord") String keyWord );
																	  
	// ==============================================================================================================
	
	//
	public List<Customer> findCustomerByTaxIdCodeContainingOrNameContainingOrSurnameContainingOrBirthCityContainingOrBirthCityProvinceContainingOrBirthDateContainingOrResidenceStreetContainingOrHouseNumberContainingOrResidenceCityContainingOrResidenceCityCapContainingOrResidenceProvinceContainingOrPhoneNumberContainingOrPhoneNumber2ContainingOrEMailContaining(
							String taxIdCode, String name, String surname, String birthCity, String birthCityProvince, String birthDate,
							String residenceStreet, String houseNumber, String residenceCity, String residenceCityCap,
							String residenceProvince, String phoneNumber, String phoneNumber2, String eMail );
		
	
	// DELETE ---------------------------------------------------------------
	
}
