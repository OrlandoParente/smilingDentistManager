package sdms.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.dto.MedicalHistoryDTO;
import sdms.model.Customer;
import sdms.model.MedicalHistory;
import sdms.service.CustomerServiceInterface;
import sdms.service.MedicalHistoryServiceInterface;

//Gestione Medical History (Anamnesi)
@RestController
public class MedicalHistoryRestController {

	@Autowired
	private MedicalHistoryServiceInterface service;
	
	@Autowired
	private CustomerServiceInterface customerService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/getMedicalsHistoryByCustomer/{idCustomer}")
	List<MedicalHistoryDTO> getMedicalsHistoryByCustomer( @PathVariable long idCustomer ){
		
		// check message
		System.out.println("MedicalHistoryRestController --> getMedicalsHistoryByCustomer ");
		
		List<MedicalHistory> listMedicalHistory = service.getMedicalsHistoryByCustomerId(idCustomer);
		
		return listMedicalHistory.stream().map( mh -> modelMapper.map( mh, MedicalHistoryDTO.class) ).toList();
	}
	

	@GetMapping("/getMedicalHistoryById/{id}")
	public MedicalHistoryDTO getMedicalHistoryById( @PathVariable long id ) {
		
		// check message
		System.out.println("MedicalHistoryRestController --> getMedicalsHistoryById ");
		
		return modelMapper.map( service.getMedicalHistoryById(id), MedicalHistoryDTO.class );
		
	}
	
	
	// type = "generale" o "odontoiatrica" 
	@PostMapping( value="/postMedicalHistory", params= {"idCustomer", "type", "name"} )
	public void postMedicalHistory(  @RequestParam("idCustomer") long idCustomer, 
									 	@RequestParam("type") String type , @RequestParam("name") String name ) {
	
		// check message
		System.out.println("MedicalHistoryRestController --> postMedicalHistory ");
		
		MedicalHistory medicalHistory = new MedicalHistory();
		Customer customer = customerService.getCustomerById(idCustomer);
		
		medicalHistory.setCustomer( customer );
		medicalHistory.setType(type);
		medicalHistory.setName(name);
		
		service.postMedicalHistory(medicalHistory);
		
	}
	

	@PostMapping( value="/postMedicalHistory", params= {"idCustomer", "type", "name", "description"} )
	public void postMedicalHistory(  @RequestParam("idCustomer") long idCustomer, 
									 	@RequestParam("type") String type , @RequestParam("name") String name,
									 	@RequestParam("description") String description ) {
	
		// check message
		System.out.println("MedicalHistoryRestController --> postMedicalHistory ");
		
		MedicalHistory medicalHistory = new MedicalHistory();
		Customer customer = customerService.getCustomerById(idCustomer);
		
		medicalHistory.setCustomer( customer );
		medicalHistory.setType(type);
		medicalHistory.setName(name);
		medicalHistory.setDescription(description);
		
		service.postMedicalHistory(medicalHistory);
	}
	
	
	@PutMapping( value="/putMedicalHistory", params= {"idCustomer", "type", "name"} )
	public void putMedicalHistory(  @RequestParam("idCustomer") long idCustomer, 
									 	@RequestParam("type") String type , @RequestParam("name") String name ) {
	
		// check message
		System.out.println("MedicalHistoryRestController --> postMedicalHistory ");
		
		
		MedicalHistory medicalHistory = new MedicalHistory();
		Customer customer = customerService.getCustomerById(idCustomer);
		
		medicalHistory.setCustomer( customer );
		medicalHistory.setType(type);
		medicalHistory.setName(name);
		
		service.postMedicalHistory(medicalHistory);
		
	}
	
	@PutMapping( value="/putMedicalHistory", params= {"idCustomer", "type", "name", "description"} )
	public void putMedicalHistory(  @RequestParam("idCustomer") long idCustomer, 
									 	@RequestParam("type") String type , @RequestParam("name") String name,
									 	@RequestParam("description") String description ) {
	
		// check message
		System.out.println("MedicalHistoryRestController --> postMedicalHistory ");
		
		
		MedicalHistory medicalHistory = new MedicalHistory();
		Customer customer = customerService.getCustomerById(idCustomer);
		
		medicalHistory.setCustomer( customer );
		medicalHistory.setType(type);
		medicalHistory.setName(name);
		medicalHistory.setDescription(description);
		
		service.putMedicalHistory(medicalHistory);
	}

	
	@DeleteMapping( value="/deleteMedicalHistoryById", params = {"id"} )
	public void deleteMedicalHistoryById( @RequestParam("id") long id ) {

		// check message
		System.out.println("MedicalHistoryRestController --> deleteMedicalHistoryById ");
		
		service.deleteMedicalHistoryById(id);
		
	}

}
