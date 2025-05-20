package sdms.dto.join;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sdms.dto.CustomerDTO;
import sdms.dto.ExpenseDTO;
import sdms.dto.MedicalHistoryDTO;
import sdms.model.Customer;
import sdms.model.Expense;
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
	private List<AppointmentCustomerDoctorTreatmentDTO> joinAppointmentsDTO;
	
	public CustomerMedicalHistoryExpenseAppointmentDTO() {
		this.customerDTO = new CustomerDTO();
		this.mapByTypeJoinMedicalHistoriesDTO = new TreeMap<String, List<MedicalHistoryHasMedicalHistoryDTO>>();
		this.expensesDTO = new ArrayList<ExpenseDTO>();
		this.joinAppointmentsDTO = new ArrayList<AppointmentCustomerDoctorTreatmentDTO>();
	}
	
	public CustomerMedicalHistoryExpenseAppointmentDTO buildFromCustomerId( Long idCustomer,
																			CustomerServiceInterface customerService,
																			HasMedicalHistoryServiceInterface hasMedicalHistoryService, 
																			MedicalHistoryServiceInterface medicalHistoryService,
																			ExpenseServiceInterface expenseService,
																			AppointmentServiceInterface appointmentService,
																			ModelMapper modelMapper ) {
		
		LOGGER.info("build CustomerMedicalHistoryExpenseAppointmentDTO from CustomerId");
		
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
		
		
		// Set Expenses
		this.expensesDTO = expenseService.getExpensesByCustomerId(idCustomer).stream()
											.sorted( Comparator.comparing(Expense :: getDate ).reversed() )
											.map( ex -> modelMapper.map(ex, ExpenseDTO.class))
											.toList();
		
		// Set Appoitnments
		appointmentService.getAppointmentsByCustomerId( idCustomer ).forEach( app -> {
			this.joinAppointmentsDTO.add( new AppointmentCustomerDoctorTreatmentDTO()
											.buildFromAppointmentId(app.getId(), appointmentService, modelMapper));
		});
		
		// sort appointments by decreasing date  
		this.joinAppointmentsDTO = this.joinAppointmentsDTO.stream()
				.sorted( Comparator.comparing( ja -> ja.getAppointmentDTO().getDate(), Comparator.reverseOrder() ) ).toList();
		
		return this;
	}
	
	// Filter the list of appointments by the invoice number -------------------------------------------------------------------
	public void filterAppointmentsByInvoiceNumber( String invoiceNumber ) {
		
		if( this.joinAppointmentsDTO == null )
			return;
		
		this.joinAppointmentsDTO = this.joinAppointmentsDTO.stream()
														   .filter( ja -> Optional.ofNullable( ja.getAppointmentDTO().getInvoiceNumber() )	// make sure to manage null invoice number 
																   					.orElse("")
																   					.equals(invoiceNumber) )
														   .toList();
	}
			
	// -------------------------------------------------------------------------------------------------------------------------
	
	
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

	public List<AppointmentCustomerDoctorTreatmentDTO> getJoinAppointmentsDTO() {
		return joinAppointmentsDTO;
	}

	public void setJoinAppointmentsDTO(List<AppointmentCustomerDoctorTreatmentDTO> joinAppointmentsDTO) {
		this.joinAppointmentsDTO = joinAppointmentsDTO;
	}


	
	
}
