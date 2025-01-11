package sdms.dto.join;

import org.modelmapper.ModelMapper;

import sdms.dto.HasMedicalHistoryDTO;
import sdms.dto.MedicalHistoryDTO;
import sdms.model.MedicalHistory;
import sdms.service.HasMedicalHistoryServiceInterface;
import sdms.service.MedicalHistoryServiceInterface;

public class MedicalHistoryHasMedicalHistoryDTO {

	private HasMedicalHistoryDTO hasMedicalHistoryDTO;
	private MedicalHistoryDTO medicalHistoryDTO;
	
	public MedicalHistoryHasMedicalHistoryDTO () {
		this.hasMedicalHistoryDTO = new HasMedicalHistoryDTO();
		this.medicalHistoryDTO = new MedicalHistoryDTO();
	}
	
	public MedicalHistoryHasMedicalHistoryDTO buildFromMedicalHistoryId( Long id, 
														HasMedicalHistoryServiceInterface hasMedicalHistoryService, 
														MedicalHistoryServiceInterface medicalHistoryService,
														ModelMapper modelMapper) {
		
		MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryById(id);
		
		this.medicalHistoryDTO = modelMapper.map( medicalHistory, MedicalHistoryDTO.class );
		this.hasMedicalHistoryDTO = modelMapper.map( hasMedicalHistoryService.getHasMedicalHistoriesByMedicalHistory( medicalHistory ), HasMedicalHistoryDTO.class);
		
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
