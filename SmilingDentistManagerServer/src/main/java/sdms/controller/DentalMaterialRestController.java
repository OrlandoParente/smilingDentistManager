package sdms.controller;

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

import sdms.dto.DentalMaterialDTO;
import sdms.model.DentalMaterial;
import sdms.service.DentalMaterialServiceInterface;

@RestController
public class DentalMaterialRestController {

	@Autowired
	DentalMaterialServiceInterface service;
	
	@Autowired
	ModelMapper modelMapper;
	
	// READ ------------------------------------------------------
	@GetMapping("/getDentalMaterialById/{id}")
	public DentalMaterialDTO getDentalMaterialById( @PathVariable Long id ) {
		
		DentalMaterial dentalMaterial = service.getDentalMaterialById(id);
		DentalMaterialDTO dentalMaterialDTO = modelMapper.map( dentalMaterial, DentalMaterialDTO.class);	
		
		return dentalMaterialDTO;
	}
	
	@GetMapping("/getDentalMaterials")
	public List<DentalMaterialDTO> getDentalMaterials() {
		
		List<DentalMaterial> dentalMaterials = service.getDentalMaterials();
		List<DentalMaterialDTO> dentalMaterialDTOs = dentalMaterials.stream()
														.map( dm -> modelMapper.map( dm, DentalMaterialDTO.class ) )
														.toList();
		
		return dentalMaterialDTOs;
	}
	
	
	// CREATE ----------------------------------------------------
	@PostMapping("/postDentalMaterial")
	public void postDentalMaterial(  @RequestParam("name") String name, @RequestParam("quantity") String quantity, 
										@RequestParam("description") String description, @RequestParam("cost")  double cost ) {
		
		DentalMaterial dentalMaterial = new DentalMaterial();
		dentalMaterial.setName(name);
		dentalMaterial.setQuantity(quantity);
		dentalMaterial.setDescription(description);
		dentalMaterial.setCost(cost);
		
		service.postDentalMaterial(dentalMaterial);
	}
	

	
	// UPDATE ----------------------------------------------------
	@PutMapping("/putDentalMaterial")
	public ResponseEntity<?> putDentalMaterial(  @RequestParam("id") long id, @RequestParam("name") String name, 
									@RequestParam("quantity") String quantity, @RequestParam("description") String description, 
									@RequestParam("cost")  double cost ) {
		
		DentalMaterial dentalMaterial = service.getDentalMaterialById(id);
		
		if( dentalMaterial == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("NOT FOUND: Dental Material to update not found in the database");
					
		dentalMaterial.setName(name);
		dentalMaterial.setQuantity(quantity);
		dentalMaterial.setDescription(description);
		dentalMaterial.setCost(cost);
		
		service.putDentalMaterial(dentalMaterial);
		
		return ResponseEntity.status(HttpStatus.OK).body(dentalMaterial);
	}
	
	// DELETE ----------------------------------------------------
	@DeleteMapping("/deleteDentalMaterialById")
	public void deleteDentalMaterial( @RequestParam("id") long id ) {
		
		service.deleteDentalMaterial(id);
	}
}
