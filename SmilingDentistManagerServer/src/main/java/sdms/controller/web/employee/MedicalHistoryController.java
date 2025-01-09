package sdms.controller.web.employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.MedicalHistoryDTO;
import sdms.model.MedicalHistory;
import sdms.service.MedicalHistoryServiceInterface;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/medical-histories")
public class MedicalHistoryController {
	
	private final Logger LOGGER = LoggerFactory.getLogger( MedicalHistoryController.class );
	
	@Autowired
	MedicalHistoryServiceInterface service;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"/medical-histories","","/"})
	public String mainCalendarPage( HttpServletRequest request, Model model ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		

		// Sorting for prepare the build of the map --------------------------------------------------------------------------------
		List<MedicalHistoryDTO> medicalHistoriesDTO = service.getMedicalHistories().stream()
				 												.sorted( Comparator.comparing( mh -> mh.getName() ) )			// first sort by name
				 												.sorted( Comparator.comparing( mh -> mh.getCategory() ) )		// then by category
				 												.sorted( Comparator.comparing( mh -> mh.getType() ) )			// in th end by type
				 												.map( mh -> modelMapper.map(mh, MedicalHistoryDTO.class) )
				 												.toList();
		// -------------------------------------------------------------------------------------------------------------------------
		
		// Build th map of MedicalHistories mapped by the type ---------------------------------------------------------------------
		
		Map< String, List<MedicalHistoryDTO> > mapByTypeMedicalHistories = new TreeMap< String, List<MedicalHistoryDTO> >();
		
		String keyType = "";
		List<MedicalHistoryDTO> tmpMedicalHistoriesDTO = null;
		
		for( MedicalHistoryDTO mhDTO : medicalHistoriesDTO ) {
			
			if( ! mhDTO.getType().equals(keyType) ) {
				
				keyType = mhDTO.getType();
				tmpMedicalHistoriesDTO = new ArrayList<MedicalHistoryDTO>();
				tmpMedicalHistoriesDTO.add(mhDTO);
				
				mapByTypeMedicalHistories.put(keyType, tmpMedicalHistoriesDTO);
				
			} else {
				mapByTypeMedicalHistories.get(keyType).add(mhDTO);
			}
			
		}
		
		// -------------------------------------------------------------------------------------------------------------------------
		
		List<String> medicalHistoryTypes = service.getMedicalHistoryTypes();
		
		List<String> medicalHistoryCategories = service.getMedicalHistoryCategories();
		
		// Add stuff to the model
		model.addAttribute("mapByTypeMedicalHistories", mapByTypeMedicalHistories);
		model.addAttribute("medicalHistoryTypes", medicalHistoryTypes);
		model.addAttribute("medicalHistoryCategories", medicalHistoryCategories);
		
		return("employee/medical-histories/medical-histories");
	}

}
