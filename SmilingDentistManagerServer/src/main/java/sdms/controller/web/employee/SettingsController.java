package sdms.controller.web.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/employee/settings")
public class SettingsController {
	
	public final Logger LOGGER = LoggerFactory.getLogger( SettingsController.class );

	@GetMapping({"/settings","","/"})
	public String mainCalendarPage() {
		return("employee/settings/settings");
	}
	
    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request) {
        request.getSession().setAttribute("lang", lang);
        LOGGER.info( "Changing language to: " + lang );
        return "redirect:" + request.getHeader("Referer");
    }

	
}
