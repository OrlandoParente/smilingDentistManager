package sdms.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sdms.model.Customer;

public class FolderManager {

	private final static Logger LOGGER = LoggerFactory.getLogger( FolderManager.class );
	
	// Private paths: Use get for be sure that the folder exists
	private final static String DEFAULT_FOLDER_CUSTOMERS = File.pathSeparator + "img" + File.pathSeparator  + "customer_folders";
	
	// Public paths
	public final static String DEFAULT_FOLDER_ORTHOPANTOMOGRAMS = File.pathSeparator + "orthopantomograms";
	public final static String FOLDER_STATIC_RESOURCES = "src" + File.pathSeparator + "main" + File.pathSeparator + "resources" + File.pathSeparator + "static";
	
	public FolderManager() {
	}
	
	// Return customerFolder path, and create it if not exists
	// WARNING: This method do not save the generated path 
	public static String getCustomerFolder( Customer customer ) {
		
		String folderPath = null;
		
		
		if( customer.getCustomerFolder() != null ) {	// If folderPath already exists for the customer, just return it
			if( new File( customer.getCustomerFolder() ).exists() ) {
				return customer.getCustomerFolder();
			} else {	// If, for some reason the folder was deleted, so let create it again
				folderPath = customer.getCustomerFolder();
			}
				
		} else { // If not exist yet, create a unique folder path
			
									
			String prefixFolderPath = FolderManager.FOLDER_STATIC_RESOURCES + FolderManager.DEFAULT_FOLDER_CUSTOMERS + File.pathSeparator + customer.getName() + "-" + customer.getSurname() + "-";
		
			if( customer.getTaxIdCode() != null ) {	// if we have the taxIdCode (Cod. Fiscale) we have a unique folder name
				folderPath = prefixFolderPath + customer.getTaxIdCode();
			} else {
				int uniqueNum=2;
				folderPath = prefixFolderPath + uniqueNum;
				
				while( new File( folderPath ).exists() ) {
					uniqueNum ++;
					folderPath = prefixFolderPath + uniqueNum;
				}
			}
		}
		
		File folder = new File(folderPath);
		
		if( ! folder.exists() ) {
			
			if( folder.mkdir() ) {
				LOGGER.info(folderPath + " created ");
			} else {
				LOGGER.error("Fail to create : " + folderPath);
				return null;
			}
				
			
		} else {
			LOGGER.info(folderPath + " alredy exists");
		}
		
		return folderPath;
	}
}
