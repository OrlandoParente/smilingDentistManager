package sdms.controller.web.employee;

import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.DentalMaterialDTO;
import sdms.service.DentalMaterialServiceInterface;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/dental-materials")
public class DentalMaterialsController {
	
	private final Logger LOGGER = LoggerFactory.getLogger( DentalMaterialsController.class );

	@Autowired
	DentalMaterialServiceInterface service;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"/dental-materials","","/"})
	public String mainCalendarPage( HttpServletRequest request, Model model ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		
		// ---------------------------------------------------------------------------------------------
		
		// Fetch dental materials list 
		List<DentalMaterialDTO> dentalMaterials = service.getDentalMaterials().stream()
													.sorted( Comparator.comparing( dm -> dm.getName() ) )
													.map( dm -> modelMapper.map(dm, DentalMaterialDTO.class) )
													.toList();
		
		// Add stuff to the model
		model.addAttribute("dentalMaterials", dentalMaterials);
		
		return("employee/dental-materials/dental-materials");
	}
}
