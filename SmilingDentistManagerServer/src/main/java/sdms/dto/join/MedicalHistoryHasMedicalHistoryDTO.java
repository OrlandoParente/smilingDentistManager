package sdms.dto.join;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sdms.dto.HasMedicalHistoryDTO;
import sdms.dto.MedicalHistoryDTO;
import sdms.model.HasMedicalHistory;
import sdms.model.MedicalHistory;
import sdms.service.HasMedicalHistoryServiceInterface;
import sdms.service.MedicalHistoryServiceInterface;

public class MedicalHistoryHasMedicalHistoryDTO {

	private static Logger LOGGER = LoggerFactory.getLogger( MedicalHistoryHasMedicalHistoryDTO.class );
	
	private HasMedicalHistoryDTO hasMedicalHistoryDTO;
	private MedicalHistoryDTO medicalHistoryDTO;
	
	public MedicalHistoryHasMedicalHistoryDTO () {
		this.hasMedicalHistoryDTO = new HasMedicalHistoryDTO();
		this.medicalHistoryDTO = new MedicalHistoryDTO();
	}
	
	public MedicalHistoryHasMedicalHistoryDTO buildFromHasMedicalHistoryId( Long id, 
														HasMedicalHistoryServiceInterface hasMedicalHistoryService, 
														MedicalHistoryServiceInterface medicalHistoryService,
														ModelMapper modelMapper) {
		
		LOGGER.info("build  MedicalHistoryHasMedicalHistoryDTO from HasMedicalHistoryId");
		
		HasMedicalHistory hasMedicalHistory = hasMedicalHistoryService.getHasMedicalHistoryById(id);
		MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryById( hasMedicalHistory.getMedicalHistory().getId() );
		
		
		this.hasMedicalHistoryDTO = modelMapper.map( hasMedicalHistory, HasMedicalHistoryDTO.class );
		this.medicalHistoryDTO = modelMapper.map( medicalHistory, MedicalHistoryDTO.class );

		return this;
	}

	// GETTERS AND SETTERS
	
	public HasMedicalHistoryDTO getHasMedicalHistoryDTO() {
		return hasMedicalHistoryDTO;
	}

	public void setHasMedicalHistoryDTO(HasMedicalHistoryDTO hasMedicalHistoryDTO) {
		this.hasMedicalHistoryDTO = hasMedicalHistoryDTO;
	}

	public MedicalHistoryDTO getMedicalHistoryDTO() {
		return medicalHistoryDTO;
	}

	public void setMedicalHistoryDTO(MedicalHistoryDTO medicalHistoryDTO) {
		this.medicalHistoryDTO = medicalHistoryDTO;
	}
	
	
	
}
