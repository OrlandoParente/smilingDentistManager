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
import jakarta.transaction.Transactional;
import sdms.model.Customer;
import sdms.model.Orthopantomogram;
import sdms.repository.CustomerRepository;
import sdms.repository.OrthopantomogramRepository;
import sdms.util.FolderManager;

@Service
public class OrthopantomogramService implements OrthopantomogramServiceInterface {

	private static final Logger LOGGER = LoggerFactory.getLogger( Orthopantomogram.class );
	
	@Autowired
	private OrthopantomogramRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Orthopantomogram getOrthopantomogramById(Long id) {
		Orthopantomogram orthopantomogram = repository.findById(id)
				.orElseThrow(
						() -> new EntityNotFoundException("Orthopantomogram with id " + id + " not found in the database" )
				);
		
		return orthopantomogram;
	}

	@Override
	public List<Orthopantomogram> getOrthopantomogramsByCustomerAndFilename( Long idCustomer, String filename) {
		
		Customer customer = customerRepository.findById(idCustomer)
				.orElseThrow( () -> new EntityNotFoundException("Customer with id " + idCustomer + " not found in the database") );
		
		List<Orthopantomogram> orthopantomograms = repository.findByCustomerAndFilenameContaining( customer , filename);
		
		return orthopantomograms;
	}

	@Override
	public List<Orthopantomogram> getOrthopantomograms() {
		
		return repository.findAll();
	}
	
	@Override
	public List<Orthopantomogram> getOrthopantomogramsByCustomer( Long idCustomer ) {
		
		Customer customer = customerRepository.findById(idCustomer)
				.orElseThrow( () -> new EntityNotFoundException("Customer with id " + idCustomer + " not found in the database") );
	
		
		return repository.findByCustomer(customer);
	}
	
	@Override
	public void uploadOrthopantomogram(Long idCustomer, MultipartFile orthopantomogram, String format ) {
		uploadOrthopantomogram(idCustomer, orthopantomogram, format, LocalDate.now() );
	}
	
	@Override
	public void uploadOrthopantomogram(String folderPath, MultipartFile orthopantomogram, String format ) {
		uploadOrthopantomogram(folderPath, orthopantomogram, format, LocalDate.now() );
	}
	
	@Override
	public void uploadOrthopantomogram(Long idCustomer, MultipartFile orthopantomogram, String format, LocalDate date ) {
		
		Customer customer = customerRepository.findById(idCustomer)
				.orElseThrow( () -> new EntityNotFoundException("Customer with id " + idCustomer + " not found in the database") );
	
		
		String folderPath = FolderManager.getOrthopantomogramFolder(customer);
		
		uploadOrthopantomogram(folderPath, orthopantomogram, format);
		
	}

	@Override
	@Transactional
	public void uploadOrthopantomogram(String folderPath, MultipartFile orthopantomogram, String format, LocalDate date ) {
		
		try {
			Orthopantomogram orthopantomogramEntity = new Orthopantomogram();
			orthopantomogramEntity.setFolder(folderPath);
			orthopantomogramEntity.setDate( date );
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
	@Transactional
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
	@Transactional
	public void deleteOrthopantomogram(Long id) {
		
		// find the Orthopantomogram
		Orthopantomogram orthopantomogram = repository.findById(id)
				.orElseThrow(
						() -> new EntityNotFoundException("Orthopantomogram with id " + id + " not found in the database" )
				);
		
		// delete the file
		String filePath = orthopantomogram.getFilename() + File.pathSeparator + orthopantomogram.getFilename();
		File file = new File( filePath );
		
        // Delete file
        if (!file.delete()) {
            throw new RuntimeException("Failed to delete file: " + file.getAbsolutePath());
        }
		
		repository.delete(orthopantomogram);
		
	}

}
