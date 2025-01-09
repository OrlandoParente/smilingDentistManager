package sdms.controller.web.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/medical-histories")
public class MedicalHistoryController {
	
	@GetMapping({"/medical-histories","","/"})
	public String mainCalendarPage( HttpServletRequest request, Model model ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		
		return("employee/medical-histories/medical-histories");
	}

}
