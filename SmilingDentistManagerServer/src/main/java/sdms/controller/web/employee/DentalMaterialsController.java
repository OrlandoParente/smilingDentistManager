package sdms.controller.web.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee/dental-materials")
public class DentalMaterialsController {

	@GetMapping({"/dental-materials","","/"})
	public String mainCalendarPage() {
		return("employee/dental-materials/dental-materials");
	}
}
