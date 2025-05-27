package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.DentalMaterial;
import sdms.model.Expense;
import sdms.repository.CustomerRepository;
import sdms.repository.DentalMaterialRepository;
import sdms.repository.EmployeeRepository;
import sdms.repository.ExpenseRepository;

/*
 	INTERFACE METHODS
 	
  	// READ ------------------------------------------------------
	public Expense getExpenseById( long id );
	public List<Expense> getExpenses();
	public List<Expense> getExpensesByCustomerId( long idCustomer );
	public List<Expense> getExoensesByEmployeeId( long idEmployee );
	public List<Expense> getExpensesByDentalMaterialId( long idDentalMaterial );
	public List<String> getExpenseTags();

	// CREATE ----------------------------------------------------
	public void postExpense( Expense expense );
	// actually we don't need this, but I use this in the code. First edit the code and then I can delete this
	public void postDentalMaterialPurchase( long idDentalMaterial , double amountExpense, int quantity, LocalDate date, String tag );

	// UPDATE ----------------------------------------------------
	public void putExpense( Expense expense );
	
	// DELETE ----------------------------------------------------
	public void deleteExpense( long id );
 */

class ExpenseServiceTest {

	@Mock
    private ExpenseRepository expenseRepository;
    
    @Mock
    private DentalMaterialRepository dentalMaterialRepository;
    
    @Mock
    private EmployeeRepository employeeRepository;
    
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExpenseById() {
        Expense expense = new Expense();
        expense.setId(1L);
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        Expense result = expenseService.getExpenseById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetExpensesByDentalMaterialId() {
        DentalMaterial material = new DentalMaterial();
        material.setId(1L);
        Expense expense = new Expense();
        expense.setDentalMaterial(material);

        when(dentalMaterialRepository.findById(1L)).thenReturn(Optional.of(material));
        when(expenseRepository.findByDentalMaterial(material)).thenReturn(Arrays.asList(expense));

        // var result = expenseService.getExpensesByDentalMaterialId(1L);
        List<Expense> result = expenseService.getExpensesByDentalMaterialId(1L);

        assertFalse(result.isEmpty());
        assertEquals(material, result.get(0).getDentalMaterial());
    }

    @Test
    void testPostExpense() {
        Expense expense = new Expense();
        expense.setTag("Test");
        
        expenseService.postExpense(expense);

        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void testDeleteExpense() {
        Expense expense = new Expense();
        expense.setId(1L);
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        expenseService.deleteExpense(1L);

        verify(expenseRepository, times(1)).delete(expense);
    }

}
