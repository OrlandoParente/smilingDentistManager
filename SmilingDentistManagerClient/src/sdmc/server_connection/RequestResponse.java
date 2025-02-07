package sdmc.server_connection;

public class RequestResponse {
	
	// In caso di internet assente, responseCode = CONNECTION REFUSED
	public static int CONNECTION_REFUSED = 0;
	
	private String responseString;
	private int responseCode;
	
	public RequestResponse( String responseString, int responseCode ) {
		this.responseString = responseString;
		this.responseCode = responseCode;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	

}
