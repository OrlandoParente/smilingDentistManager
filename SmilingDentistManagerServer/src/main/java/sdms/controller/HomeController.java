package sdms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Controller per la gestione delle pagine jsp
 */

@Controller
public class HomeController {

	@GetMapping("/")
	public String getHome() {
		return "index";
	}
}
