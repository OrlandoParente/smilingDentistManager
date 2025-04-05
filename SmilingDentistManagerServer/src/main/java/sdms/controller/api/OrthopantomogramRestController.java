package sdms.controller.api;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sdms.dto.OrthopantomogramDTO;
import sdms.repository.OrthopantomogramRepository;
import sdms.service.OrthopantomogramServiceInterface;

@RestController
public class OrthopantomogramRestController {

	private final static Logger LOGGER = LoggerFactory.getLogger( OrthopantomogramRestController.class );
	
	@Autowired
	OrthopantomogramServiceInterface service;
	
	@Autowired
	ModelMapper modeMapper;

    // Get -----------------------------------------------------------------------------------------------------------------------
	
	// GET-ALL
	@GetMapping("/getOrthopantomograms")
	public List<OrthopantomogramDTO> getOrthopantomograms(){
		
		LOGGER.info("/getOrthopantomograms");
		
		return service.getOrthopantomograms().stream()
											.map( ortho -> modeMapper.map(ortho, OrthopantomogramDTO.class ) )
											.collect( Collectors.toList() );
		
	}
	
	
	
	// GET-BY-ID
	
	// GET-BY-NAME  <------------- POTREBBE ESSERE UN PROBLEMA FARE IL FILENAME UNIVOCO
	
	// ---------------------------------------------------------------------------------------------------------------------------
	
	// Post ----------------------------------------------------------------------------------------------------------------------
	
	
	// UPLOAD ( POST )
	
	// ---------------------------------------------------------------------------------------------------------------------------
	
	// Put -----------------------------------------------------------------------------------------------------------------------
	
	// PUT 
	
	
	// ---------------------------------------------------------------------------------------------------------------------------
}
