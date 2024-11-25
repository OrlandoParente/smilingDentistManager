package sdms.service;

import java.util.List;

import sdms.model.Expense;

public interface ExpenseServiceInterface {

	// READ ------------------------------------------------------
	public Expense getExpenseById( long id );
	public List<Expense> getExpenses();

	// CREATE ----------------------------------------------------
	public void postExpense( Expense expense );
	
	// UPDATE ----------------------------------------------------
	public void putExpense( Expense expense );
	
	// DELETE ----------------------------------------------------
	public void deleteExpense( long id );
	
	
}
