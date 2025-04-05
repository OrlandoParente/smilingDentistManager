package sdms.util;

import java.util.ArrayList;
import java.util.List;

public class FileFormatManager {
		
	public final static String FILE_FORMAT_IMAGE_SIMPLE = "SIMPLE_IMAGE"; // png, jpg, etc ..
	public final static String FILE_FORMAT_IMAGE_DICOM = "DICOM.dcm"; 	// .dcm -> Digital Imaging and Communications in Medicine 
	
	public FileFormatManager() { }
	
	public static List<String> getOrthopantomogramFileFormats(){
		
		List<String> formats = new ArrayList<>();
		
		formats.add( FileFormatManager.FILE_FORMAT_IMAGE_DICOM );
		formats.add( FileFormatManager.FILE_FORMAT_IMAGE_SIMPLE );
		
		return formats;
	}

}
