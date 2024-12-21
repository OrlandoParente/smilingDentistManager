package sdms.controller.api;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import sdms.dto.TreatmentDTO;
import sdms.model.Treatment;
import sdms.service.TreatmentServiceInterface;

@RestController
public class TreatmentRestController {
	
	@Autowired
	private TreatmentServiceInterface service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/getTreatmentById/{id}")
	public TreatmentDTO getTreatmentById( @PathVariable long id ) {
		
		// check message
		System.out.println("TreatmentRestController -> getTreatmentById ");
		
		Treatment treatment = service.getTreatmentById(id);
		
		return modelMapper.map(treatment, TreatmentDTO.class);
	}

	@GetMapping("/getTreatments")
	public List<TreatmentDTO> getTreatments( ) {
		
		// check message
		System.out.println("TreatmentRestController -> getTreatments ");
		
		List<Treatment> treatments = service.getTreatments();
		
		List<TreatmentDTO> listTreatmentDTO = treatments.stream()
							.map( treatment -> modelMapper.map(treatment, TreatmentDTO.class) )
							.toList();
		
		return listTreatmentDTO;
	}
	
	@GetMapping("/getTreatmentsById/{idCustomer}")
	public TreatmentDTO getTreatmentsByCustomerId( @PathVariable long idCustomer ) {
		
		// check message
		System.out.println("TreatmentRestController -> getTreatmentsByCustomerId ");
		
		Treatment treatment = service.getTreatmentById(idCustomer);
		
		return modelMapper.map(treatment, TreatmentDTO.class);
	}
	
	
	@GetMapping("/getTreatmentsByBillNumber/{billNumber}")
	public List<TreatmentDTO> getTreatmentsByBillNumber( @PathVariable String billNumber ) {
		
		// check message
		System.out.println("TreatmentRestController -> getTreatmentsByBillNumber ");
		
		List<Treatment> treatments = service.getTreatmentsByBillNumber(billNumber);
		
		return treatments.stream().map( t -> modelMapper.map( t, TreatmentDTO.class ) ).toList();
	}
	
	@PostMapping( value="/postTreatment", params = {"name","cost"} )
	public void postTreatment( @RequestParam("name") String name, @RequestParam("cost") float cost ) {
		
		// check message
		System.out.println("TreatmentRestController -> postTreatment");
		
		Treatment treatment = new Treatment();
		treatment.setName(name);
		treatment.setCost(cost);
		
		service.postTreatment(treatment);
		
	}
	
	@PostMapping( value="/postTreatment", params = {"name", "description", "cost"} )
	public void postTreatment( @RequestParam("name") String name, @RequestParam("description") String description,
								  @RequestParam("cost") float cost ) {
		
		// check message
		System.out.println("TreatmentRestController -> postTreatment");
		
		Treatment treatment = new Treatment();
		treatment.setName(name);
		treatment.setCost(cost);
		treatment.setDescription(description);
		
		service.postTreatment(treatment);
	}
	
	@PutMapping( value="/putTreatment", params = {"id", "name", "description", "cost"} )
	public ResponseEntity<?> putTreatment( @RequestParam("id") long id, @RequestParam("name") String name, 
								@RequestParam("description") String description,  @RequestParam("cost") float cost ) {
		
		// check message
		System.out.println("TreatmentRestController -> postTreatment");
		
		
		Treatment treatment = service.getTreatmentById(id);
		if( treatment == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Treatment to upload not found in the databse ");
		
		treatment.setName(name);
		treatment.setCost(cost);
		treatment.setDescription(description);
		
		service.putTreatment(treatment);
		
		return ResponseEntity.status( HttpStatus.OK ).body(treatment);
	}
	
	// boolean deleteTreatmentById( String id ) throws SQLException;
	@DeleteMapping( value="/deleteTreatmentById", params = {"id"})
	public void deleteTreatmentById( @RequestParam("id") long id ) {

		// check message
		System.out.println("TreatmentRestController -> deleteTreatmentById");
		
		service.deleteTreatmentById(id);
		
	}
			

}
