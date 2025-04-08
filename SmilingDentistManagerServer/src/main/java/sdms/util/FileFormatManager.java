package sdms.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileFormatManager {
	
	public final static Logger LOGGER = LoggerFactory.getLogger(FileFormatManager.class);
		
	public final static String FILE_FORMAT_IMAGE_SIMPLE = "SIMPLE_IMAGE"; // png, jpg, etc ..
	public final static String FILE_FORMAT_IMAGE_DICOM = "DICOM"; 	// .dcm -> Digital Imaging and Communications in Medicine 
	
	public final static String FILE_FORMAT_UNKNOWN = "UNKNOWN";
	
	public FileFormatManager() { }
	
	public static List<String> getOrthopantomogramFileFormats(){
		
		List<String> formats = new ArrayList<>();
		
		formats.add( FileFormatManager.FILE_FORMAT_IMAGE_DICOM );
		formats.add( FileFormatManager.FILE_FORMAT_IMAGE_SIMPLE );
		formats.add(FileFormatManager.FILE_FORMAT_UNKNOWN);
		
		return formats;
	}
	
	// Automatic featch format from filename ( by reading the extension )
	public static String getFormatFromFilename( String filename ) {
		
		// fetch the file format
		String formatExtension = FileFormatManager.extractFormatFileFromFilename(filename);
		
		if( formatExtension.trim().equals("") )
			return FileFormatManager.FILE_FORMAT_UNKNOWN;
		
		if( formatExtension.equals(".dcm") )	return FileFormatManager.FILE_FORMAT_IMAGE_DICOM;
		if( formatExtension.equals(".jpg") || formatExtension.equals(".jpeg") || formatExtension.equals(".png") )
				return FileFormatManager.FILE_FORMAT_IMAGE_SIMPLE;
		
		return FileFormatManager.FILE_FORMAT_UNKNOWN;
	}
	
	// extract the formatfile from the filename
	public static String extractFormatFileFromFilename( String filename ) {
		
		char [] arrFilename = filename.toCharArray();
		int i = arrFilename.length - 1;
		
		for( ; i > 0; i -- ) 
			if( arrFilename[i] == '.' )
				break;
		
		// No format found
		if( i == 0 )
			return ""; 
		
		String formatExtension = filename.substring(i);
		
		LOGGER.info( "File name format extension of " + filename + " : " + formatExtension );
		
		return formatExtension;
		
	}

}
