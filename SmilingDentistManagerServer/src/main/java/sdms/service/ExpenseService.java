package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Expense;
import sdms.repository.ExpenseRepository;

@Service
public class ExpenseService implements ExpenseServiceInterface {

	@Autowired
	ExpenseRepository repository;
	
	@Override
	public Expense getExpenseById(long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<Expense> getExpenses() {
		
		return repository.findAll();
	}

	@Override
	public void postExpense(Expense expense) {
		
		repository.save(expense);
	}

	@Override
	public void putExpense(Expense expense) {
		
		repository.save(expense);
	}

	@Override
	public void deleteExpense(long id) {
		
		repository.delete( repository.findById(id).get() );
	}
	
}
