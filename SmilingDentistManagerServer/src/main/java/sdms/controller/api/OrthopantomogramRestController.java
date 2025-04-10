package sdms.controller.api;

import java.time.DateTimeException;
import java.time.LocalDate;
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
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import sdms.dto.OrthopantomogramDTO;
import sdms.model.Orthopantomogram;
import sdms.service.OrthopantomogramServiceInterface;
import sdms.util.DateAndTimeManager;
import sdms.util.FileFormatManager;

@RestController
public class OrthopantomogramRestController {

	private final static Logger LOGGER = LoggerFactory.getLogger( OrthopantomogramRestController.class );

	@Autowired
	OrthopantomogramServiceInterface service;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	DateAndTimeManager dateAndTimeManager;

    // Get -----------------------------------------------------------------------------------------------------------------------
	
	// GET-ALL
	@GetMapping("/getOrthopantomograms")
	public List<OrthopantomogramDTO> getOrthopantomograms(){
		
		LOGGER.info("/getOrthopantomograms");
		
		return service.getOrthopantomograms().stream()
											.map( ortho -> modelMapper.map(ortho, OrthopantomogramDTO.class ) )
											.collect( Collectors.toList() );
		
	}
	
	// GET-BY-FILENAME
	@GetMapping("/getOrthopantomogramsByIdCustomerAndFilename")
	public List<OrthopantomogramDTO> getOrthopantomogramsByIdCustomerAndFilename( @RequestParam Long idCustomer, @RequestParam String filename ){
		
		LOGGER.info("/getOrthopantomogramsByIdCustomerAndFilename, PARAMS={ idCustomer=" + idCustomer + ", filename=" + filename + " }");
		
		return service.getOrthopantomogramsByCustomerAndFilename( idCustomer, filename ).stream()
											.map( ortho -> modelMapper.map(ortho, OrthopantomogramDTO.class ) )
											.collect( Collectors.toList() );
		
	}
	
	
	// GET-BY-ID
	@GetMapping("/getOrthopantomogramById/{id}")
	public ResponseEntity<?> getOrthopantomogramById( @PathVariable Long id ){
		
		LOGGER.info("/getOrthopantomograms PATH_VARIABLES={ id= " + id + " }");
		
		OrthopantomogramDTO orthopantomogramDTO = null;
		
		try {
			orthopantomogramDTO = modelMapper.map( service.getOrthopantomogramById(id), OrthopantomogramDTO.class);
		
		} catch( EntityNotFoundException e ) {
			e.printStackTrace();
			LOGGER.error( e.getMessage() );
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Orthopantomogram with id " + id + " not found in the database" );
			
		} catch( Exception e ) {
			e.printStackTrace();
			LOGGER.error( e.getMessage() );
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().body( orthopantomogramDTO );
		
	}
	
	
	// ---------------------------------------------------------------------------------------------------------------------------	
	
	// Post ----------------------------------------------------------------------------------------------------------------------
	// UPLOAD ( POST )
	// Should this method return orthopantomogram entity? So we have to create findLastInseterd method in database and in the repository
	// OSS:: not include "orthopantomogram" in params cause Spring can't correctly verify that's not null if present in params list
	@PostMapping( value="/uploadOrthopantomogram", params = { "idCustomer" } )
	public ResponseEntity<?> uploadOrthopantomogram( @RequestParam Long idCustomer, @RequestParam MultipartFile orthopantomogram,
													@RequestParam( defaultValue = "" ) String format,
													@RequestParam( defaultValue = "" ) String date ){
		
		LOGGER.info("/uploadOrthopantomogram PARAMS = { idCustomer=" + idCustomer + " ; format=" + format + " ;"
															+ " date="  + date + " : orthopantomogram=" + orthopantomogram + " ; }");
		
		// check orthopantomogram file is not null or not embpy
		if( orthopantomogram == null || orthopantomogram.isEmpty() ) {
			LOGGER.error("The param orthopantomogram file is null or empty");
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body(" File to upload not present or empty ");
		}
		
		LocalDate tmpDate = LocalDate.now();
		
		try {
			if( ! date.equals("") )
				tmpDate = dateAndTimeManager.parseDate(date);
		
		} catch ( DateTimeException e ) {
			e.printStackTrace();
			LOGGER.error( e.getMessage() );
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body(" Invalid format data ");
		}
		
		// If format is not passed, it try to automatic fetch it from the filename 
		if( format.trim().equals("") )
			format = FileFormatManager.getFormatFromFilename( orthopantomogram.getOriginalFilename() );
		
		service.uploadOrthopantomogram(idCustomer, orthopantomogram, format, tmpDate );
		
		return ResponseEntity.ok().build();
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------
	
	// Put -----------------------------------------------------------------------------------------------------------------------
	// PUT 
	@PutMapping( value="/putOrthopantomogram", params = { "id" } )
	public ResponseEntity<?> uploadOrthopantomogram( @RequestParam Long id, 
													@RequestParam( defaultValue = "" ) String filename,
													@RequestParam( defaultValue = "" ) String format,
													@RequestParam( defaultValue = "" ) String date ){
			
		LOGGER.info("/putOrthopantomogram");
		
		
		Orthopantomogram orthopantomogram = new Orthopantomogram();
		orthopantomogram.setId(id);
		orthopantomogram.setFilename(filename);
		orthopantomogram.setFormat(format);
			
		// Manage date ---------------------------------------------
		
		LocalDate tmpDate = null;
		if( ! date.equals("") ) {
			try {
				
				tmpDate = dateAndTimeManager.parseDate(date);
				orthopantomogram.setDate(tmpDate);
				
			} catch ( DateTimeException e ) {
				
				e.printStackTrace();
				LOGGER.error( e.getMessage() );
				return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body(" Invalid format data ");
			}
		} else {
			orthopantomogram.setDate(null);
		}
		// ---------------------------------------------------------
		
		try {
			service.putOrthopantomogram(orthopantomogram);
		} catch ( EntityNotFoundException e ) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body(" Orthopantomogram with id " + id + " not found in the database ");
			
		} catch( Exception e ) {
			LOGGER.error( e.getMessage() );
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
			
			
		return ResponseEntity.ok().body( modelMapper.map( orthopantomogram, OrthopantomogramDTO.class ) );
	}
	
	
	// ---------------------------------------------------------------------------------------------------------------------------
	
	// Delete --------------------------------------------------------------------------------------------------------------------
	// DELETE
	@DeleteMapping( value= "/deleteOrthopantomogram", params= { "id" } )
	public ResponseEntity<?> deleteOrthopantomogram( @RequestParam Long id ){
		
		try {
			service.deleteOrthopantomogram(id);
		} catch ( EntityNotFoundException e ) {
			
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body(" Orthopantomogram with id " + id + " not found in the database ");
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			LOGGER.error( e.getMessage() );
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body(" Invalid format data ");
		}
		
		
		return ResponseEntity.ok().build();
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------
}
