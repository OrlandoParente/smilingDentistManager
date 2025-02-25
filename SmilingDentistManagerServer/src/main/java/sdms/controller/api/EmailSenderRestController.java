package sdms.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSenderRestController {
	
	@PostMapping()
	public void resetPassword( String email ) {
		
	}

}
