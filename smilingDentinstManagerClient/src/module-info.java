/**
 * @author Orlando Parente
 */
module smilingDentinstManagerClient {
	
	exports sdmc.main;
	exports sdmc.utils;
	exports sdmc.calendar;
	exports sdmc.customer;
	exports sdmc.employee;
	exports sdmc.professional_role;
	exports sdmc.combo_box_management;
	exports sdmc.server_connection;
	exports sdmc.settings;
	
	requires transitive java.desktop;
	requires transitive org.json;
	requires java.net.http;
	
	
}