package sdms.controller.web.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee/settings")
public class Settings {

	@GetMapping({"/settings","","/"})
	public String mainCalendarPage() {
		return("employee/settings/settings");
	}
}
