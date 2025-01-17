package sdms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Customer;
import sdms.model.DentalMaterial;
import sdms.model.Employee;
import sdms.model.Expense;
import sdms.repository.CustomerRepository;
import sdms.repository.DentalMaterialRepository;
import sdms.repository.EmployeeRepository;
import sdms.repository.ExpenseRepository;

@Service
public class ExpenseService implements ExpenseServiceInterface {

	@Autowired
	ExpenseRepository repository;
	
	@Autowired
	DentalMaterialRepository dentalMaterialRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Expense getExpenseById(long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<Expense> getExpenses() {
		
		return repository.findAll();
	}
	
	@Override
	public List<String> getExpenseTags() {
		
		List<Expense> expenses = repository.findAll();
		List<String> tags = new ArrayList<String>();
		
		// sort expenses so we have the same tags next to the previous 
		expenses.sort( Comparator.comparing( Expense :: getTag ) );
		
		String tmpTag = "";
		
		for( Expense e : expenses ) {
			if(  ! e.getTag().equals(tmpTag) ) {
				tmpTag = e.getTag();
				tags.add( e.getTag() );
			}
		}
		
		return tags;
	}

	@Override
	public void postExpense(Expense expense) {
		
		// for avoid nullPointerExceptions
		if( expense.getTag() == null )	expense.setTag("");
		
		repository.save(expense);
	}

	@Override
	public void putExpense(Expense expense) {
		
		// for avoid nullPointerExceptions
		if( expense.getTag() == null )	expense.setTag("");
		
		repository.save(expense);
	}

	@Override
	public void deleteExpense(long id) {
		
		Expense expense = repository.findById(id).get();
		
		repository.delete( expense );
	}

	@Override
	public void postDentalMaterialPurchase( long idDentalMaterial, double amountExpense, int quantity, LocalDate date, String tag) {
		
		DentalMaterial dentalMaterial = dentalMaterialRepository.findById( idDentalMaterial ).get();
		String description = quantity + " x " + dentalMaterial.getName();
		
		Expense expense = new Expense();
		
		expense.setDate(date);
		expense.setAmount(amountExpense);
		expense.setTag(tag);
		expense.setDescription(description);
		expense.setDentalMaterial(dentalMaterial);
		expense.setCustomer(null);
		expense.setEmployee(null);
		
		repository.save(expense);
		
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<----------------------------- TO IMPLEMENT 
	@Override
	public void postEmployeeSalaryPayment(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<----------------------------- TO IMPLEMENT 
	@Override
	public void postCustomerRefund(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	
}
