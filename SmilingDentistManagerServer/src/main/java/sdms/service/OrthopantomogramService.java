package sdms.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import sdms.model.Customer;
import sdms.model.Orthopantomogram;
import sdms.repository.OrthopantomogramRepository;
import sdms.util.FolderManager;

@Service
public class OrthopantomogramService implements OrthopantomogramServiceInterface {

	private static final Logger LOGGER = LoggerFactory.getLogger( Orthopantomogram.class );
	
	@Autowired
	private OrthopantomogramRepository repository;
	
	
	@Override
	public Orthopantomogram getOrthopantomogramById(Long id) {
		Orthopantomogram orthopantomogram = repository.findById(id)
				.orElseThrow(
						() -> new EntityNotFoundException("Orthopantomogram with id " + id + " not found in the database" )
				);
		
		return orthopantomogram;
	}

	@Override
	public Orthopantomogram getOrthopantomogramByFileName(String filename) {
		
		Orthopantomogram orthopantomogram = repository.findByFilename(filename)
				.orElseThrow(
						() -> new EntityNotFoundException("Orthopantomogram with filename " + filename + " not found in the database" )
				);
		
		return orthopantomogram;
	}

	@Override
	public List<Orthopantomogram> getOrthopantomograms() {
		
		return repository.findAll();
	}
	
	@Override
	public List<Orthopantomogram> getOrthopantomogramsByCustomer( Customer customer ) {
		
		return repository.findByCustomer(customer);
	}
	
	@Override
	public void uploadOrthopantomogram(Customer customer, MultipartFile orthopantomogram, String format) {
		
		String folderPath = FolderManager.getOrthopantomogramFolder(customer);
		
		uploadOrthopantomogram(folderPath, orthopantomogram, format);
		
	}

	@Override
	public void uploadOrthopantomogram(String folderPath, MultipartFile orthopantomogram, String format) {
		
		try {
			Orthopantomogram orthopantomogramEntity = new Orthopantomogram();
			orthopantomogramEntity.setFolder(folderPath);
			orthopantomogramEntity.setDate( LocalDate.now() );
			orthopantomogramEntity.setFileName( orthopantomogram.getOriginalFilename() );
			orthopantomogramEntity.setFormat(format);
			
			String filePath = folderPath + orthopantomogramEntity.getFilename();
			orthopantomogram.transferTo( new File( filePath ) );
			
			repository.save( orthopantomogramEntity );
			
		} catch( IOException e ) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void putOrthopantomogram(Orthopantomogram orthopantomogram) {
		
		// If filename is changed, upload the file
		Orthopantomogram original = repository.findById( orthopantomogram.getId() )
				.orElseThrow(
						() -> new EntityNotFoundException("Orthopantomogram with id " + orthopantomogram.getId() + " not found in the database" )
				);
		
		// check if it has to rename the filename 
		if( ! original.getFilename().equals( orthopantomogram.getFilename() ) ) {
			// change filename
			String oldFilePath = original.getFolder() + File.pathSeparator + original.getFilename();
			String newFilePath = orthopantomogram.getFolder() + File.pathSeparator + orthopantomogram.getFilename();
			File originalFile = new File( oldFilePath );
			File newFile = new File(newFilePath);
			
			if( originalFile.renameTo( newFile ) ) {
				LOGGER.info( oldFilePath + " renamed to " + newFilePath );
			} else {
				LOGGER.error( "Impossible rename file from " + oldFilePath + " to " + newFilePath );
			    throw new IllegalStateException( "Impossible rename file from " + oldFilePath + " to " + newFilePath );
			}
			
		}
		
		repository.save( orthopantomogram );
	}

	@Override
	public void deleteOrthopantomogram(Long id) {
		
		Orthopantomogram orthopantomogram = repository.findById(id)
				.orElseThrow(
						() -> new EntityNotFoundException("Orthopantomogram with id " + id + " not found in the database" )
				);
		
		repository.delete(orthopantomogram);
		
	}

	@Override
	public void deleteOrthopantomogram(String filename) {
		
		Orthopantomogram orthopantomogram = repository.findByFilename(filename)
				.orElseThrow(
						() -> new EntityNotFoundException("Orthopantomogram with filename " + filename + " not found in the database" )
				);
		
		repository.delete(orthopantomogram);
	}

}
