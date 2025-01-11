package sdms.dto.join;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;

import sdms.dto.AppointmentDTO;
import sdms.dto.CustomerDTO;
import sdms.dto.ExpenseDTO;
import sdms.dto.MedicalHistoryDTO;
import sdms.model.Customer;
import sdms.model.MedicalHistory;
import sdms.service.AppointmentServiceInterface;
import sdms.service.CustomerServiceInterface;
import sdms.service.ExpenseServiceInterface;
import sdms.service.HasMedicalHistoryServiceInterface;
import sdms.service.MedicalHistoryServiceInterface;

public class CustomerMedicalHistoryExpenseAppointmentDTO {

	private CustomerDTO customerDTO;
	private Map<String, List<MedicalHistoryHasMedicalHistoryDTO>>  mapByTypeJoinMedicalHistoriesDTO;
	private List<ExpenseDTO> expensesDTO;
	private List<AppointmentDTO> appointmentsDTO;
	
	public CustomerMedicalHistoryExpenseAppointmentDTO() {
		this.customerDTO = new CustomerDTO();
		this.mapByTypeJoinMedicalHistoriesDTO = new TreeMap<String, List<MedicalHistoryHasMedicalHistoryDTO>>();
		this.expensesDTO = new ArrayList<ExpenseDTO>();
		this.appointmentsDTO = new ArrayList<AppointmentDTO>();
	}
	
	public CustomerMedicalHistoryExpenseAppointmentDTO buildFromCustomerId( Long idCustomer,
																			CustomerServiceInterface customerService,
																			HasMedicalHistoryServiceInterface hasMedicalHistoryService, 
																			MedicalHistoryServiceInterface medicalHistoryService,
																			ExpenseServiceInterface expenseService,
																			AppointmentServiceInterface appointmentService,
																			ModelMapper modelMapper ) {
		
		Customer customer = customerService.getCustomerById( idCustomer );
		this.customerDTO = modelMapper.map(customer, CustomerDTO.class);
		
		
		// Build th map of MedicalHistories mapped by the type ---------------------------------------------------------------------
			
		List<MedicalHistory> medicalHistories = medicalHistoryService.getMedicalsHistoryByCustomerId(idCustomer);
		
		String keyType = "";
		List<MedicalHistoryHasMedicalHistoryDTO> tmpJoinMedicalHistoriesDTO = null;
		
		for( MedicalHistory mh : medicalHistories ) {
			
			if( ! mh.getType().equals(keyType) ) {
				
				keyType = mh.getType();
				tmpJoinMedicalHistoriesDTO = new ArrayList<MedicalHistoryHasMedicalHistoryDTO>();
				tmpJoinMedicalHistoriesDTO.add( new MedicalHistoryHasMedicalHistoryDTO().buildFromMedicalHistoryId(
																								mh.getId(), 
																								hasMedicalHistoryService, 
																								medicalHistoryService, 
																								modelMapper) );
				
				this.mapByTypeJoinMedicalHistoriesDTO.put(keyType, tmpJoinMedicalHistoriesDTO);
				
			} else {
				this.mapByTypeJoinMedicalHistoriesDTO.get(keyType)
														.add( new MedicalHistoryHasMedicalHistoryDTO()
																.buildFromMedicalHistoryId(
																	mh.getId(), 
																	hasMedicalHistoryService, 
																	medicalHistoryService, 
																	modelMapper) );
			}
			
		}
		
		// -------------------------------------------------------------------------------------------------------------------------
		
		
		// <<<<<<<<<<<<<---------------------------- MANCA EXPENSES 
		
		appointmentService.getAppointmentsByCustomerId( idCustomer ).forEach( app -> {
			this.appointmentsDTO.add( modelMapper.map(app, AppointmentDTO.class) );
		});
		
		return this;
	}
	
	// GETTERS AND SETTERS 

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}



	public Map<String, List<MedicalHistoryHasMedicalHistoryDTO>> getMapByTypeJoinMedicalHistoriesDTO() {
		return mapByTypeJoinMedicalHistoriesDTO;
	}

	public void setMapByTypeJoinMedicalHistoriesDTO(
			Map<String, List<MedicalHistoryHasMedicalHistoryDTO>> mapByTypejoinMedicalHistoriesDTO) {
		this.mapByTypeJoinMedicalHistoriesDTO = mapByTypejoinMedicalHistoriesDTO;
	}

	public List<ExpenseDTO> getExpensesDTO() {
		return expensesDTO;
	}

	public void setExpensesDTO(List<ExpenseDTO> expensesDTO) {
		this.expensesDTO = expensesDTO;
	}

	public List<AppointmentDTO> getAppointmentsDTO() {
		return appointmentsDTO;
	}

	public void setAppointmentsDTO(List<AppointmentDTO> appointmentsDTO) {
		this.appointmentsDTO = appointmentsDTO;
	}
	
	
	
	
}
