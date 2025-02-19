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
import sdms.dto.TreatmentDTO;
import sdms.service.EmployeeServiceInterface;
import sdms.service.TreatmentServiceInterface;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/treatment")
public class TreatmentController {

	private final Logger LOGGER = LoggerFactory.getLogger( TreatmentController.class );
	
	@Autowired
	EmployeeServiceInterface employeeService;
	
	@Autowired
	TreatmentServiceInterface service;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"","/","/treatment"})
	public String getTreatmentPage( HttpServletRequest request, Model model ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model, employeeService);
		// ---------------------------------------------------------------------------------------------
		
		List<TreatmentDTO> treatments = service.getTreatments().stream()
									.map( treatment -> modelMapper.map( treatment, TreatmentDTO.class ) )	// convert into DTO object
									.sorted( Comparator.comparing( treat -> treat.getName() ) )				// sort by treatment name
									.toList();
		
		// check print
		for( TreatmentDTO treatmentDTO : treatments )
			LOGGER.info("Sorted by treatment name: " + treatmentDTO);
		
		model.addAttribute("treatments",treatments);
		
		return ("employee/treatments/treatments");
	}
}
