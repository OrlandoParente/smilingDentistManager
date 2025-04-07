package sdms.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sdms.model.Customer;
import sdms.model.Orthopantomogram;

public class FolderManager {

	private final static Logger LOGGER = LoggerFactory.getLogger( FolderManager.class );
	
	// Private paths: Use get for be sure that the folder exists
	private final static String DEFAULT_FOLDER_CUSTOMERS = File.separator + "img" + File.separator  + "customer_folders";
	
	// Public paths
	public final static String DEFAULT_FOLDER_ORTHOPANTOMOGRAMS = File.separator + "orthopantomograms";
	public final static String FOLDER_STATIC_RESOURCES = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static";
	
	public FolderManager() {
	}
	
	public static void deleteFolder(String folderPath) {
	    File folder = new File(folderPath);

	    if (folder.exists() && folder.isDirectory()) {
	        // Recursively delete all files and subfolders
	        deleteFolderContents(folder);

	        // Finally, delete the folder itself
	        if (folder.delete()) {
	            LOGGER.info("Folder deleted successfully: " + folderPath);
	        } else {
	            throw new RuntimeException("Failed to delete the folder: " + folderPath);
	        }
	    } else {
	        throw new IllegalArgumentException("Invalid folder path or the folder does not exist: " + folderPath);
	    }
	}

	public static void deleteFolderContents(File folder) {
	    File[] files = folder.listFiles();
	    if (files != null) {
	        for (File file : files) {
	            if (file.isDirectory()) {
	                // Recursively delete subfolders
	                deleteFolderContents(file);
	            }
	            // Delete files
	            if (!file.delete()) {
	                throw new RuntimeException("Failed to delete file: " + file.getAbsolutePath());
	            }
	        }
	    }
	}

	
	// Return customerFolder path, and create it if not exists
	// WARNING: This method do not save the generated path 
	public static String getCustomerFolder( Customer customer ) {
		
		String folderPath = null;
		
		
		if( customer.getCustomerFolder() != null && ! customer.getCustomerFolder().isEmpty() ) {	// If folderPath already exists for the customer, just return it
			if( new File( customer.getCustomerFolder() ).exists() ) {
				return customer.getCustomerFolder();
			} else {	// If, for some reason the folder was deleted, so let create it again
				folderPath = customer.getCustomerFolder();
			}
				
		} else { // If not exist yet, create a unique folder path
			
									
			String prefixFolderPath = 	FolderManager.getAbsoluteRootProjectPath() + File.separator 
										+ FolderManager.FOLDER_STATIC_RESOURCES + FolderManager.DEFAULT_FOLDER_CUSTOMERS + File.separator 
										+ customer.getName() + "-" + customer.getSurname() + "-";
		
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
			
			if( folder.mkdirs() ) {
				LOGGER.info(folderPath + " created ");

			} else {
				LOGGER.error("Fail to create : " + folderPath);
			    throw new IllegalStateException("Failed to create directory: " + folderPath);
		        // throw new IOException("Failed to create directory at: " + folderPath);

			}
				
			
		} else {
			LOGGER.info(folderPath + " alredy exists");
		}
		
		return folderPath;
	}
	
	// Return customerFolder path, and create it if not exists
	// WARNING: This method do not save the generated path 	
	public static String getOrthopantomogramFolder( Customer customer ) {
		
		// Get ( eventually generate ) customer folder 
		String customerFolderPath = getCustomerFolder( customer );
		
		LOGGER.info("getOrthopantomogramFolder -> customerFolderPath: " + customerFolderPath);
		
		String folderPath = customerFolderPath + File.separator + FolderManager.DEFAULT_FOLDER_ORTHOPANTOMOGRAMS;
		
		LOGGER.info("getOrthopantomogramFolder -> orthopantomogramsFolderPath: " + customerFolderPath);
		
		File folder = new File( folderPath );
		
		// If the folder was deleted for some reason, try to create it again
		if( ! folder.exists() ) {
			
			if( folder.mkdir() ) {
				LOGGER.info(folderPath + " created ");
			} else {
				LOGGER.error("Fail to create : " + folderPath);
			    throw new IllegalStateException("Failed to create directory: " + folderPath);
		        // throw new IOException("Failed to create directory at: " + folderPath);

			}
				
			
		} else {
			LOGGER.info(folderPath + " alredy exists");
		}
		
		
		return folderPath;
	}
	
	// First check if Orthopantomogram already have a folder path
	public static String getOrthopantomogramFolder( Customer customer, Orthopantomogram orthopantomogram ) {
		
		if( orthopantomogram.getFolder() == null || orthopantomogram.getFolder().isEmpty() )
			return getCustomerFolder(customer);
		
		String folderPath = orthopantomogram.getFolder();
		File folder = new File( folderPath );
		
		// If the folder was deleted for some reason, try to create it again
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
	
	// Static paths can cause a problem for different contexts that depends if you are run the war or the jar 
	public static String getAbsoluteRootProjectPath() {
		
		File root = new File(".");
		String rootProjectPath = null;
		
		try {
			rootProjectPath = root.getCanonicalPath();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return rootProjectPath;
	}
}
