package sdms.dto.join;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sdms.dto.AppointmentDTO;
import sdms.dto.CustomerDTO;
import sdms.dto.ExpenseDTO;
import sdms.dto.MedicalHistoryDTO;
import sdms.model.Customer;
import sdms.model.HasMedicalHistory;
import sdms.model.MedicalHistory;
import sdms.service.AppointmentServiceInterface;
import sdms.service.CustomerServiceInterface;
import sdms.service.ExpenseServiceInterface;
import sdms.service.HasMedicalHistoryServiceInterface;
import sdms.service.MedicalHistoryServiceInterface;

public class CustomerMedicalHistoryExpenseAppointmentDTO {
	
	private final Logger LOGGER = LoggerFactory.getLogger( CustomerMedicalHistoryExpenseAppointmentDTO.class );

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
			
//		List<MedicalHistory> medicalHistories = medicalHistoryService.getMedicalsHistoryByCustomerId(idCustomer);
		
		List<HasMedicalHistory> hasMedicalHistories = hasMedicalHistoryService.getHasMedicalHistoriesByCustomer( customer )
																					.stream()
																					.sorted( Comparator.comparing( hmh -> hmh.getMedicalHistory().getType() ) )
																					.toList();
		
		for( HasMedicalHistory hmh : hasMedicalHistories ) {
			LOGGER.info( "" + hmh );
		}

		String keyType = "";
		List<MedicalHistoryHasMedicalHistoryDTO> tmpJoinMedicalHistoriesDTO = null;
		
		int loop = 0;
		for( HasMedicalHistory hmh : hasMedicalHistories ) {
			
			if( ! hmh.getMedicalHistory().getType().equals(keyType) ) {
				
				keyType = hmh.getMedicalHistory().getType();
				tmpJoinMedicalHistoriesDTO = new ArrayList<MedicalHistoryHasMedicalHistoryDTO>();
				tmpJoinMedicalHistoriesDTO.add( new MedicalHistoryHasMedicalHistoryDTO().buildFromHasMedicalHistoryId(
																								hmh.getId(), 
																								hasMedicalHistoryService, 
																								medicalHistoryService, 
																								modelMapper) );
				
				this.mapByTypeJoinMedicalHistoriesDTO.put(keyType, tmpJoinMedicalHistoriesDTO);
				
				LOGGER.info("KeyType loop " + loop + " : " + keyType );
				loop ++;
				
			} else {
				this.mapByTypeJoinMedicalHistoriesDTO.get(keyType)
														.add( new MedicalHistoryHasMedicalHistoryDTO()
																.buildFromHasMedicalHistoryId(
																    hmh.getId(), 
																	hasMedicalHistoryService, 
																	medicalHistoryService, 
																	modelMapper) );
				
				
				LOGGER.info("KeyType loop " + loop + " : " + keyType );
				loop ++;
			}
			
		}
		
		// -------------------------------------------------------------------------------------------------------------------------
		
		
		// <<<<<<<<<<<<<---------------------------- MANCA EXPENSES 
		
		appointmentService.getAppointmentsByCustomerId( idCustomer ).forEach( app -> {
			this.appointmentsDTO.add( modelMapper.map(app, AppointmentDTO.class) );
		});
		
		return this;
	}
	
	
	// Returns true if the customer has the medical history ------------------------------------------------------------------

	public boolean containsMedicaHistory( MedicalHistoryDTO mhDTO ) {
		return containsMedicaHistory( mhDTO.getId() );
	}
	
	public boolean containsMedicaHistory( MedicalHistory mh ) {
		return containsMedicaHistory( mh.getId() );
	}
	
	public boolean containsMedicaHistory( long idMedicalHistory ) {
	
		for( String key : mapByTypeJoinMedicalHistoriesDTO.keySet() ) {
			for( MedicalHistoryHasMedicalHistoryDTO joinMedicalHistory : mapByTypeJoinMedicalHistoriesDTO.get(key)  ) {
				if( joinMedicalHistory.getMedicalHistoryDTO().getId() == idMedicalHistory )
					return true;
			}
		}
		
		return false;
	}
	
	// -----------------------------------------------------------------------------------------------------------------------
	
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
