package sdms.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSettingsDTO {

	private final static Logger LOGGER = LoggerFactory.getLogger( EmailSettingsDTO.class );
	
	private long id;
	
	private String host;
	private int port;
	private String username;
	private String password;
	private boolean enableAuth;
	private boolean enableTLS;
	private boolean enableSSL;
	
	public EmailSettingsDTO() { }

	
	// TO STRING 
	@Override
	public String toString() {
		return "EmailSettingsDTO [id=" + id + ", host=" + host + ", port=" + port + ", username=" + username
				+ ", password=" + password + ", enableAuth=" + enableAuth + ", enableTLS=" + enableTLS + ", enableSSL="
				+ enableSSL + "]";
	}
	
	
	// GETTERS AND SETTERS
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	// Hide the real password to the client
	public void setPassword(String password) {
		
		LOGGER.info("Replace the real password with stars");
		
		char pswChar[] = password.toCharArray();
		
		for( int i = 0; i < pswChar.length; i ++ ) {
			pswChar[i] = '*';
		}
		
		this.password = new String(pswChar);
	}

	public boolean isEnableAuth() {
		return enableAuth;
	}

	public void setEnableAuth(boolean enableAuth) {
		this.enableAuth = enableAuth;
	}

	public boolean isEnableTLS() {
		return enableTLS;
	}

	public void setEnableTLS(boolean enableTLS) {
		this.enableTLS = enableTLS;
	}

	public boolean isEnableSSL() {
		return enableSSL;
	}

	public void setEnableSSL(boolean enableSSL) {
		this.enableSSL = enableSSL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
