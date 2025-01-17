package sdms.service;

import java.time.LocalDate;
import java.util.List;

import sdms.model.Customer;
import sdms.model.DentalMaterial;
import sdms.model.Employee;
import sdms.model.Expense;

public interface ExpenseServiceInterface {

	// READ ------------------------------------------------------
	public Expense getExpenseById( long id );
	public List<Expense> getExpenses();
	public List<String> getExpenseTags();

	// CREATE ----------------------------------------------------
	public void postExpense( Expense expense );
	public void postDentalMaterialPurchase( long idDentalMaterial , double amountExpense, int quantity, LocalDate date, String tag );
	public void postEmployeeSalaryPayment( Employee employee );
	public void postCustomerRefund( Customer customer );
	
	// UPDATE ----------------------------------------------------
	public void putExpense( Expense expense );
	
	// DELETE ----------------------------------------------------
	public void deleteExpense( long id );
	
	
}
