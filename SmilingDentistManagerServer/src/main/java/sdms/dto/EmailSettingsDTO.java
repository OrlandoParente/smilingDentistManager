package sdms.dto;

public class EmailSettingsDTO {

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

	public void setPassword(String password) {
		
		this.password = password;
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
