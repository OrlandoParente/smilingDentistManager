package sdms.controller.web.employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.CustomerDTO;
import sdms.dto.EmployeeDTO;
import sdms.dto.MedicalHistoryDTO;
import sdms.dto.OrthopantomogramDTO;
import sdms.dto.TreatmentDTO;
import sdms.dto.join.CustomerMedicalHistoryExpenseAppointmentDTO;
import sdms.email.AutomaticEmailUtil;
import sdms.model.Customer;
import sdms.service.AppointmentServiceInterface;
import sdms.service.CustomerServiceInterface;
import sdms.service.EmployeeServiceInterface;
import sdms.service.ExpenseServiceInterface;
import sdms.service.HasMedicalHistoryServiceInterface;
import sdms.service.MedicalHistoryServiceInterface;
import sdms.service.OrthopantomogramServiceInterface;
import sdms.service.TreatmentServiceInterface;
import sdms.util.FileFormatManager;
import sdms.util.UserRoleManager;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/customer")
public class CustomerController {

	private final Logger LOGGER = LoggerFactory.getLogger( CustomerController.class );
	
	@Autowired
	CustomerServiceInterface service;
	
	@Autowired
	MedicalHistoryServiceInterface medicalHistoryService;
	
	@Autowired
	HasMedicalHistoryServiceInterface hasMedicalHistoryService;
	
	@Autowired
	ExpenseServiceInterface expenseService;
	
	@Autowired
	AppointmentServiceInterface appointmentService;
	
	@Autowired
	TreatmentServiceInterface treatmentService;
	
	@Autowired
	EmployeeServiceInterface employeeService;
	
	@Autowired
	OrthopantomogramServiceInterface orthopantomogramService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"/customer","","/"})
	public String mainCustomerPage( HttpServletRequest request, Model model,
									@RequestParam( defaultValue = "" ) String keyword ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model, employeeService);
		// ---------------------------------------------------------------------------------------------
		
		// Get DTO customers list ----------------------------------------------------------------------
		List<Customer> customers = new ArrayList<Customer>();
		
		// if keyword is set up, search customers by keyword
		if( ! keyword.equals("") )
			customers =  service.getCustomersByPartialKeyWordOverAllFields(keyword);
		else
			customers = service.getCustomers();
		
		List<CustomerDTO> customersDTO = customers.stream().sorted( Comparator.comparing( c -> c.getSurname() ) )
										.map( c -> modelMapper.map(c, CustomerDTO.class ) )
										.toList();
		
		// ---------------------------------------------------------------------------------------------
		
		// Fetch automatic email intervals 
		List<Integer> automaticEmailIntervals = AutomaticEmailUtil.getAutomaticEmailIntervals();
		
		// Add stuff to the model
		model.addAttribute("customers", customersDTO);
		model.addAttribute("customerPermissions", UserRoleManager.getCustomerPermissions());
		model.addAttribute("automaticEmailIntervals", automaticEmailIntervals);
		
		
		return("employee/customers/customers");
	}
	
	@GetMapping({ "/customer/details/{customerId}","/details/{customerId}",
				  "/customer/details/{customerId}/{selectedInvoiceNumber}","/details/{customerId}/{selectedInvoiceNumber}" })
	public String customerDetailsPage( HttpServletRequest request, Model model,
									@PathVariable("customerId") Long customerId,
									@PathVariable( required = false ) String selectedInvoiceNumber ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model, employeeService);
		// ---------------------------------------------------------------------------------------------

		
		// ---------------------------------------------------------------------------------------------
		
		Customer customer = service.getCustomerById(customerId);
		
		// <<<<<<<------------------------------ Si potrebbe reindirizzare ad una pagina di errore
		if( customer == null )
			return "404 NOT FOUND";
		
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		
		// ---------------------------------------------------------------------------------------------
		
		// ---------------------------------------------------------------------------------------------
		CustomerMedicalHistoryExpenseAppointmentDTO joinCustomer = new CustomerMedicalHistoryExpenseAppointmentDTO();
		
		joinCustomer.buildFromCustomerId(customerId, service, hasMedicalHistoryService, medicalHistoryService, expenseService, appointmentService, modelMapper);
		
		if( selectedInvoiceNumber != null )
			joinCustomer.filterAppointmentsByInvoiceNumber(selectedInvoiceNumber);
		
//		List<MedicalHistoryHasMedicalHistoryDTO> listGenerale = joinCustomer.getMapByTypeJoinMedicalHistoriesDTO().get("Generale");
//		
//		List<MedicalHistoryHasMedicalHistoryDTO> listOdontoiatrica = joinCustomer.getMapByTypeJoinMedicalHistoriesDTO().get("Odontoiatrica");
//		
//		for( MedicalHistoryHasMedicalHistoryDTO obj : listGenerale  ) {
//			LOGGER.info( "GENERALE #####################>>>>>>>>>>> " +obj.getHasMedicalHistoryDTO().getNotes()  );
//		}
//		
//		for( MedicalHistoryHasMedicalHistoryDTO obj : listOdontoiatrica  ) {
//			LOGGER.info( "ODONTOIATRICA #####################>>>>>>>>>>> " +obj.getHasMedicalHistoryDTO().getNotes()  );
//		}
		
		// ---------------------------------------------------------------------------------------------
				
		
		// ---------------------------------------------------------------------------------------------
		
//		List<MedicalHistoryDTO> medicalHistories = medicalHistoryService.getMedicalsHistoryByCustomerId( customerId ).stream()
//													.map( mh -> modelMapper.map(mh, MedicalHistoryDTO.class) )
//													.toList();
//		
		// ---------------------------------------------------------------------------------------------
		
		
		// ---------------------------------------------------------------------------------------------
		
		List<MedicalHistoryDTO> addableMedicalHistories = medicalHistoryService.getMedicalHistories().stream()
															.filter( mh -> ! joinCustomer.containsMedicaHistory(mh) )
															.sorted( Comparator.comparing( mh -> mh.getName() ) )
															.map( mh -> modelMapper.map( mh, MedicalHistoryDTO.class) )
															.toList();
		
		// ---------------------------------------------------------------------------------------------
		
		// Fetch doctors and treatments -----------------------------------------------------------------
		// NEED EDIT: We need a filter to filter doctors from employees
		List<EmployeeDTO> doctors = employeeService.getEmployees().stream()
				.sorted( Comparator.comparing( emp -> emp.getSurname() ) )
				.map( employee -> modelMapper.map(employee, EmployeeDTO.class) )
				.toList();
		
		List<TreatmentDTO> treatments = treatmentService.getTreatments().stream()
				.sorted( Comparator.comparing( treat -> treat.getName() ) )
				.map( treatment -> modelMapper.map(treatment, TreatmentDTO.class) )
				.toList();
		// ---------------------------------------------------------------------------------------------
		
		// Fetch invoice numbers -----------------------------------------------------------------------
		List<String> customerInvoiceNumbers = appointmentService.getInvoiceNumbersByCustomerId( customerId );
		// ---------------------------------------------------------------------------------------------
				
		// Fetch automatic email intervals --------------------------------------------------------------
		List<Integer> automaticEmailIntervals = AutomaticEmailUtil.getAutomaticEmailIntervals();
		// ----------------------------------------------------------------------------------------------
		
		// Fetch orthopantomograms ----------------------------------------------------------------------
		List<OrthopantomogramDTO> orthopantomograms = orthopantomogramService.getOrthopantomogramsByCustomer(customerId).stream()
																				.map( ortho ->  modelMapper.map(ortho, OrthopantomogramDTO.class ) )
																				.sorted( Comparator.comparing( ortho -> ((OrthopantomogramDTO) ortho).getDate() ).reversed() )
																				.toList();
		
		orthopantomograms.forEach(  ortho ->  LOGGER.info(ortho.toString()) );
		// ----------------------------------------------------------------------------------------------

		
		// check print
		joinCustomer.getJoinAppointmentsDTO().forEach( jc -> {
			LOGGER.info( "joinCustomer.joinAppointmentDTO.teeth = " + jc.getAppointmentDTO().getTeeth() );
		});
		
		// Add stuff to the model
//		model.addAttribute("customer", customerDTO);	
//		model.addAttribute("medicalHistories", medicalHistories);
		model.addAttribute("joinCustomer", joinCustomer);
		model.addAttribute("addableMedicalHistories", addableMedicalHistories);
		model.addAttribute("doctors", doctors);
		model.addAttribute("treatments",treatments);
		model.addAttribute("customerInvoiceNumbers", customerInvoiceNumbers);
		model.addAttribute("selectedInvoiceNumber", selectedInvoiceNumber);
		model.addAttribute("automaticEmailIntervals", automaticEmailIntervals);
		model.addAttribute("orthopantomograms", orthopantomograms);
		
		// serve?
		model.addAttribute("customerPermissions", UserRoleManager.getCustomerPermissions());
		
		
//		model.addAttribute("orthoFormats", orthoFormats);		
//		// Fetch orthopantomogram formats ---------------------------------------------------------------
//		List<String> orthoFormats = FileFormatManager.getOrthopantomogramFileFormats();
//		// ----------------------------------------------------------------------------------------------

		
		return "employee/customers/customer-details";
	}
	
	@GetMapping( value =  "/openOrthopantomogram/{orthopantomogramId}" )
	public String openOrthopantomogram( HttpServletRequest request, Model model, @PathVariable Long orthopantomogramId ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model, employeeService);
		// ---------------------------------------------------------------------------------------------
					
		OrthopantomogramDTO orthopantomogram = modelMapper.map( orthopantomogramService.getOrthopantomogramById(orthopantomogramId), OrthopantomogramDTO.class );
		// <-------------------- TO ADD: GO TO NOT FOUND PAGE IF NOT FOUND ENTITY IS THROWED 
		
		
		model.addAttribute("orthopantomogram", orthopantomogram);
		
		return "employee/customers/view-orthopantomograms";
	}
	
	
}
