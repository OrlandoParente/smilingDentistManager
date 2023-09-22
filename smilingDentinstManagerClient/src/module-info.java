/**
 * @author Orlando Parente
 */
module smilingDentinstManagerClient {
	
	exports sdmc.main;
	exports sdmc.utils;
	
	requires java.desktop;
	requires transitive org.json;
	requires java.net.http;
	
}