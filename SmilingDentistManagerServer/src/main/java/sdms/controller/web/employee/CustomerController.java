package sdms.controller.web.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee/customer")
public class CustomerController {

	@GetMapping({"/customer","","/"})
	public String mainCalendarPage() {
		return("employee/customer/customer");
	}
}
