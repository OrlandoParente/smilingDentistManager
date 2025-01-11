package sdms.controller.api;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.dto.CustomerDTO;
import sdms.model.Customer;
import sdms.service.CustomerServiceInterface;
import sdms.util.DateAndTimeManager;

@RestController
public class CustomerRestController {
	
	
	private final Logger LOGGER = LoggerFactory.getLogger( CustomerRestController.class );
	
	// Spring si occupa di associare la giusta implementazione
	@Autowired
	CustomerServiceInterface service;
	
	@Autowired
	private ModelMapper modelMapper;	// @Bean defined into @SpringBootApplication
	
	@Autowired
	private DateAndTimeManager dateAndTimeManager;
	
	@GetMapping("/getMaxIdCustomer")
	public long getMaxIdCustomer() {
		
		return service.getLastCustomerId();
	}
	
	// @RequestMapping("/getCustomers")
	// @ResponseBody
	@GetMapping("/getCustomers") 
	public List<CustomerDTO> getCustomers(){
		 
		 // Stampa di controllo
		 System.out.println("CustomerRestController --> getCustomers ");
		 	 
		 List<CustomerDTO> custumersDTO = new ArrayList<CustomerDTO>();
		 List<Customer> custumers = service.getCustomers();
		 
		 custumersDTO = custumers.stream().
				 				map( customer -> modelMapper.map( customer, CustomerDTO.class ) )
				 				.collect( Collectors.toList() );
		 
		 return custumersDTO;
	 }
	 
	 @GetMapping( value = "/getCustomerById/{id}" )
	 // @ResponseBody
	 public CustomerDTO getCustomerById( @PathVariable long id){
		 
		 // Stampa di controllo
		 System.out.println("CustomerRestController --> getCustomer -> id customer = " + id );
		 
		return modelMapper.map( service.getCustomerById(id) , CustomerDTO.class );
	 }
	
	 @GetMapping("/getCustomersByPartialKeyWordOverAllFields/{keyWord}")
	 // @ResponseBody
	 public List<CustomerDTO> getCustomersByPartialKeyWordOverAllFields( @PathVariable String keyWord ){
		 
		 // Stampa di controllo
		 System.out.println("CustomerRestController --> getCustomersByPartialKeyWordOverAllFields -> keyWord = " + keyWord );
		 
		 List<CustomerDTO> listCustomerDTO = service.getCustomersByPartialKeyWordOverAllFields(keyWord).stream()
				 				.map( customer -> modelMapper.map(customer, CustomerDTO.class) ).toList();
		 
		 return listCustomerDTO;
	 }

	 
	 // POST ------------------------------------------------------------------------------------------------------------

	 //
	 @PostMapping( value = "/postCustomer" , params = {"name", "surname" })
	 public ResponseEntity<?> postCustomer(   @RequestParam String name, @RequestParam String surname, 	// Mandatory params 
			 					 @RequestParam( defaultValue = "" ) String eMail,
			 					 @RequestParam( defaultValue = "" ) String taxIdCode ,
			 					 @RequestParam( defaultValue = "-1" ) Integer permission,
								 @RequestParam( defaultValue = "" ) String birthCity , 
								 @RequestParam( defaultValue = "" ) String birthCityProvince,
								 @RequestParam( defaultValue = "" ) String birthDate, 
								 @RequestParam( defaultValue = "" ) String residenceStreet, 
								 @RequestParam( defaultValue = "" ) String houseNumber, 
								 @RequestParam( defaultValue = "" ) String residenceCity, 
								 @RequestParam( defaultValue = "" ) String residenceCityCap,
								 @RequestParam( defaultValue = "" ) String residenceProvince, 
								 @RequestParam( defaultValue = "" ) String phoneNumber, 
								 @RequestParam( defaultValue = "" ) String phoneNumber2 ) {

		 // Stampa di controllo
		 System.out.println("CustomerRestController --> getCustomers ");
		 
		 Customer customer = new Customer();
		 
		 try {
		 
			 customer.setName(name);
			 customer.setSurname(surname);
			 
			 // not mandatory fields -----------------------------------------------------------------------
			 
			 // the db save permission = 0 (NO ACCOUNT) by default
			 if( permission != -1 ) customer.setPermission( permission );
			 
			 if( ! taxIdCode.equals("") )	customer.setTaxIdCode(taxIdCode);
			 if( ! birthCity.equals("") )	customer.setBirthCity(birthCity);
			 if( ! birthCityProvince.equals("") )	customer.setBirthCityProvince(birthCityProvince);
			 
			 // can throw DateTimeParseException
			 if( ! birthDate.equals("") )	customer.setBirthDate( dateAndTimeManager.parseDate(birthDate) );
			 
			 if( ! residenceCity.equals("") )	customer.setResidenceStreet(residenceStreet);
			 if( ! houseNumber.equals("") )	customer.setHouseNumber(houseNumber);
			 if( ! residenceProvince.equals("") )	customer.setResidenceProvince(residenceProvince);
			 if( ! residenceCity.equals("") )	customer.setResidenceCity(residenceCity);
			 if( ! residenceCityCap.equals("") )	customer.setResidenceCityCap(residenceCityCap);
			 if( ! phoneNumber.equals("") )	customer.setPhoneNumber(phoneNumber);
			 if( ! phoneNumber2.equals("") )	customer.setPhoneNumber2(phoneNumber2);
			 if( ! eMail.equals("") )	customer.seteMail(eMail);
			 
			// --------------------------------------------------------------------------------------------
			 
			 
			 service.postCustomer(customer);
		 
			 
		 } catch ( DateTimeException dte ) {
			 
			 System.err.println( dte.getMessage() );
			 return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( dte.getMessage() );
			 
		 } catch ( Exception e ) {
			 
			 System.err.println( e.getMessage() );
			 return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e.getMessage() );
		 }
		 
		 return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(customer, CustomerDTO.class) );
		 
	 }
	 
	 
//	 // Inserimento dei soli parametri essenziali del cliente
//	 @PostMapping( value = "/postCustomer" , params = {"name","surname","phoneNumber"}  )
//	 public void postEssentialCustomer( @RequestParam("name") String name, @RequestParam("surname") String surname, 
//			 						@RequestParam("phoneNumber") String phoneNumber ) {
//
//		 // Stampa di controllo
//		 System.out.println("CustomerRestController --> getCustomers ");
//
//		 Customer customer = new Customer();
//		 customer.setName(name);
//		 customer.setSurname(surname);
//		 customer.setPhoneNumber(phoneNumber);
//		 
//		 service.postCustomer(customer);
//		 
//	 }
// 
//	 
//	 //
//	 @PostMapping( value = "/postCustomer" , params = {"taxIdCode", "name", "surname", "birthCity" , "birthCityProvince", 
//			 "birthDate","residenceStreet", "houseNumber", "residenceCity", "residenceCityCap" ,"residenceProvince", 
//			 "phoneNumber" , "phoneNumber2", "eMail" })
//	 public void postCustomer(  @RequestParam("taxIdCode") String taxIdCode , @RequestParam("name") String name, 
//			 @RequestParam("surname") String surname, @RequestParam("birthCity") String birthCity , 
//			 @RequestParam("birthCityProvince") String birthCityProvince,
//			 @RequestParam("birthDate") String birthDate, @RequestParam("residenceStreet") String residenceStreet, 
//			 @RequestParam("houseNumber") String houseNumber, @RequestParam("residenceCity") String residenceCity, 
//			 @RequestParam("residenceCityCap") String residenceCityCap,
//			 @RequestParam("residenceProvince") String residenceProvince, @RequestParam("phoneNumber") String phoneNumber, 
//			 @RequestParam("phoneNumber2") String phoneNumber2,  @RequestParam("eMail") String EMail) {
//
//		 // Stampa di controllo
//		 System.out.println("CustomerRestController --> getCustomers ");
//		 
//		 Customer customer = new Customer();
//		 customer.setTaxIdCode(taxIdCode);
//		 customer.setName(name);
//		 customer.setSurname(surname);
//		 customer.setBirthCity(birthCity);
//		 customer.setBirthCityProvince(birthCityProvince);
//		 customer.setBirthDate( dateAndTimeManager.parseDate(birthDate) );
//		 customer.setResidenceStreet(residenceStreet);
//		 customer.setHouseNumber(houseNumber);
//		 customer.setResidenceProvince(residenceProvince);
//		 customer.setResidenceCity(residenceCity);
//		 customer.setResidenceCityCap(residenceCityCap);
//		 customer.setPhoneNumber(phoneNumber);
//		 customer.setPhoneNumber2(phoneNumber2);
//		 customer.seteMail(EMail);
//		 
//		 service.postCustomer(customer);
//		 
//	 }
//	 
	// -----------------------------------------------------------------------------------------------------------------
	 
	// PUT --------------------------------------------------------------------------------------------------------------

	 @PutMapping( value = "/putCustomer" , params = {"id"})
	 public ResponseEntity<?> putCustomerById(  @RequestParam("id") long id, 
			 						 @RequestParam( defaultValue = "" ) String name, 
			 						 @RequestParam( defaultValue = "" ) String surname,
									 @RequestParam( defaultValue = "" ) String taxIdCode , 
									 @RequestParam( defaultValue = "-1" ) Integer permission, 
									 @RequestParam( defaultValue = "" ) String birthCity , 
									 @RequestParam( defaultValue = "" ) String birthCityProvince,
									 @RequestParam( defaultValue = "" ) String birthDate, 
									 @RequestParam( defaultValue = "" ) String residenceStreet, 
									 @RequestParam( defaultValue = "" ) String houseNumber, 
									 @RequestParam( defaultValue = "" ) String residenceCity, 
									 @RequestParam( defaultValue = "" ) String residenceCityCap,
									 @RequestParam( defaultValue = "" ) String residenceProvince, 
									 @RequestParam( defaultValue = "sdms_nothing-nessun-val-passato" ) String phoneNumber, 
									 @RequestParam( defaultValue = "sdms_nothing-nessun-val-passato" ) String phoneNumber2,  
									 @RequestParam( defaultValue = "sdms_nothing-nessun-val-passato" ) String eMail) {

		 // Stampa di controllo
		 System.out.println("CustomerRestController --> putCustomerById  ");
		 
		 Customer customer = service.getCustomerById(id);
		 
		 if( customer == null )
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Customer with id = " + id + " not found in the database ");
		 
		 try {
		 
			 if( ! name.equals("") ) customer.setName(name);
			 if( ! surname.equals("") ) customer.setSurname(surname);
			 
			 // the db save permission = 0 (NO ACCOUNT) by default
			 if( permission != -1 ) customer.setPermission( permission );
			 
			 if( ! taxIdCode.equals("") )	customer.setTaxIdCode(taxIdCode);
			 if( ! birthCity.equals("") )	customer.setBirthCity(birthCity);
			 if( ! birthCityProvince.equals("") )	customer.setBirthCityProvince(birthCityProvince);
			 
			 // can throw DateTimeParseException
			 if( ! birthDate.equals("") )	customer.setBirthDate( dateAndTimeManager.parseDate(birthDate) );
			 
			 if( ! residenceCity.equals("") )	customer.setResidenceStreet(residenceStreet);
			 if( ! houseNumber.equals("") )	customer.setHouseNumber(houseNumber);
			 if( ! residenceProvince.equals("") )	customer.setResidenceProvince(residenceProvince);
			 if( ! residenceCity.equals("") )	customer.setResidenceCity(residenceCity);
			 if( ! residenceCityCap.equals("") )	customer.setResidenceCityCap(residenceCityCap);
			 
			 // I avoid to compare with "" for allow the possibility of set this field as empty
			 if( ! phoneNumber.equals("sdms_nothing-nessun-val-passato") )	customer.setPhoneNumber(phoneNumber);
			// I avoid to compare with "" for allow the possibility of set this field as empty
			 if( ! phoneNumber2.equals("sdms_nothing-nessun-val-passato") )	customer.setPhoneNumber2(phoneNumber2);
			// I avoid to compare with "" for allow the possibility of set this field as empty
			 if( ! eMail.equals("sdms_nothing-nessun-val-passato") )	customer.seteMail(eMail);
			 
			// --------------------------------------------------------------------------------------------
			 
			 
			 service.postCustomer(customer);
		 
			 
		 } catch ( DateTimeException dte ) {
			 
			 System.err.println( dte.getMessage() );
			 return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( dte.getMessage() );
			 
		 } catch ( Exception e ) {
			 
			 System.err.println( e.getMessage() );
			 return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e.getMessage() );
		 }
		 
		 return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(customer, CustomerDTO.class) );

	 }	 
	
	 
	 
	 
//	 // Aggiorna tutti i valori del Customer associato all'id passato
//	 @PutMapping( value = "/putCustomerById" , params = {"id","taxIdCode", "name", "surname", "birthCity" , "birthCityProvince", 
//			 "birthDate","residenceStreet", "houseNumber", "residenceCity", "residenceCityCap" ,"residenceProvince", 
//			 "phoneNumber" , "phoneNumber2", "eMail" })
//	 public void putCustomerById(  @RequestParam("id") long id, @RequestParam("taxIdCode") String taxIdCode , 
//			 @RequestParam("name") String name, @RequestParam("surname") String surname, 
//			 @RequestParam("birthCity") String birthCity , @RequestParam("birthCityProvince") String birthCityProvince,
//			 @RequestParam("birthDate") String birthDate, @RequestParam("residenceStreet") String residenceStreet, 
//			 @RequestParam("houseNumber") String houseNumber, @RequestParam("residenceCity") String residenceCity, 
//			 @RequestParam("residenceCityCap") String residenceCityCap,
//			 @RequestParam("residenceProvince") String residenceProvince, @RequestParam("phoneNumber") String phoneNumber, 
//			 @RequestParam("phoneNumber2") String phoneNumber2,  @RequestParam("eMail") String eMail) {
//
//		 // Stampa di controllo
//		 System.out.println("CustomerRestController --> putCustomerById  ");
//		 
//		 Customer customer = new Customer();
//		 customer.setId(id);
//		 customer.setTaxIdCode(taxIdCode);
//		 customer.setName(name);
//		 customer.setSurname(surname);
//		 customer.setBirthCity(birthCity);
//		 customer.setBirthCityProvince(birthCityProvince);
//		 customer.setBirthDate( dateAndTimeManager.parseDate(birthDate) );
//		 customer.setResidenceStreet(residenceStreet);
//		 customer.setHouseNumber(houseNumber);
//		 customer.setResidenceProvince(residenceProvince);
//		 customer.setResidenceCity(residenceCity);
//		 customer.setResidenceCityCap(residenceCityCap);
//		 customer.setPhoneNumber(phoneNumber);
//		 customer.setPhoneNumber2(phoneNumber2);
//		 customer.seteMail(eMail);
//		 
//		 service.putCustomer(customer);
//	 }	 
//	
//	 
	// -----------------------------------------------------------------------------------------------------------------
	 
	 // Delete Customer by Id
	 @DeleteMapping( value="/deleteCustomer" , params = { "id" } )
	 public void deleteCustomerById( @RequestParam("id") long id ) {
		  
		 service.deleteCustomer(id);
		 
	 }
}
