package sdms.controller.web.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee/employee")
public class EmployeeController {

	@GetMapping({"/employee","","/"})
	public String mainCalendarPage() {
		return("employee/employee/employee");
	}
}
