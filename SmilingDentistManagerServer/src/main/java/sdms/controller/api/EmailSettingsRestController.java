package sdms.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.service.EmailSettingsServiceInterface;

@RestController
public class EmailSettingsRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderRestController.class);

	@Autowired
	private EmailSettingsServiceInterface emailSettingsService;
	
	@PostMapping( value={"/saveEmailSettings"}, params= {"host", "port", "username", "password", "enableAuth", "enableTLS", "enableSSL"})
	public ResponseEntity<?> saveSettings( @RequestParam String host, @RequestParam int port, @RequestParam String username, @RequestParam String password, 
											@RequestParam boolean enableAuth, @RequestParam boolean enableTLS, @RequestParam boolean enableSSL){
		
		emailSettingsService.saveSettings(host, port, username, password, enableAuth, enableTLS, enableSSL);
		
		LOGGER.info("/saveEmailSettings");
		
		return ResponseEntity.ok().build();
	}
}
