package sdms.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sdms.util.WebClientCookieManager;

// NOT USED, WE CAN DELETE THIS

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	@GetMapping("/employee")
	public String employeeDashboard( HttpServletRequest request, Model model ) {
		
		String name = WebClientCookieManager.getCookieValue(request, WebClientCookieManager.NAME);
		model.addAttribute(WebClientCookieManager.NAME, name);
		
		return "employee/dashboard";
//		return "employee/calendar/view/day";	
	}
	
}
