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
	
	
}
