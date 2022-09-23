package com.example.demo.sevices;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.utils.ConnectionFactory;
import com.training.exceptions.EmployeeNotFoundException;
import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;

public class EmployeeService {
	Connection con;
	EmployeeRepository repo;
	private static final Logger logger = LogManager.getRootLogger();

	public EmployeeService() {
		super();
		this.con = ConnectionFactory.getMySqlConnection();
		this.repo = new EmployeeRepositoryImpl(con);
	}

	public void save(Employee obj) {
		boolean hasSaved=this.repo.save(obj);
		if (hasSaved) {
			logger.info("is Employee Created:=" + hasSaved);
		} else {
			logger.error("is Employee Created:=" + hasSaved);
		}

	}

	public void findByFirstName(String empFirstName) {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findByFirstName(empFirstName);
			logger.info("List of employees whose first names begin with: " + empFirstName);
		} catch (EmployeeNotFoundException e) {
			logger.error("Having trouble finding with first name: " + empFirstName);
		}
		for (Employee employee : employeeList) {
			logger.info(employee);
		}
	}

	public void findFirstNameAndNumber() {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findFirstNameAndNumber();
			logger.info("First name and PhoneNumber of all employees");
		} catch (EmployeeNotFoundException e1) {
			logger.error("First name and PhoneNumber of employees cannot be found");
		}

		employeeList.forEach(e -> System.out.println(" FirstName :"+e.getEmpFirstName() + " and PhoneNumber: " + e.getEmpPhoneNumber()));
	}

	public void updateByEmailAndNumber(String updatedEmail, long empPhoneNumber, String empEmail) {
		try {
			boolean update = this.repo.updateByEmailAndNumber(updatedEmail, empPhoneNumber, empEmail);
			logger.info("Does an employee with email: " + empEmail + " get updated:=" + update);
		} catch (EmployeeNotFoundException e) {
			logger.error(
					"Employee with the given email: " + empEmail + " cannot be found.So this employee cannot be updated");
		}
	}

	public void deleteByFirstName(String empFirstName, String empEmail) {
		try {
			boolean delete = this.repo.deleteByFirstName(empFirstName, empEmail);
			logger.info("Does an employee with email: " + empEmail + " get deleted:=" + delete);
		} catch (EmployeeNotFoundException e) {
			logger.error(
					"Particular Employee with the given name: " + empFirstName + " and email: " + empEmail + " is not found");
		}
	}

	public void findByBirthday(LocalDate empDateOfBirth) {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findByBirthday(empDateOfBirth);
			logger.info("First name and PhoneNumber of all employees who have born on=" + empDateOfBirth);
		} catch (EmployeeNotFoundException e1) {
			logger.error("No Employees have been found with the given date of birth: " + empDateOfBirth);
		}
		for(Employee eachEmployee:employeeList) {
			System.out.println(" FirstName ="+eachEmployee.getEmpFirstName() + " and PhoneNumber= " + eachEmployee.getEmpEmail());
		}
	}

	public void findByWeddingDate(LocalDate empWeddingDate) {
		Collection<Employee> employeeList = new ArrayList<>();
		
		try {
			employeeList = this.repo.findByWeddingDate(empWeddingDate);
			logger.info("First name and PhoneNumber of all employees who got married on=" + empWeddingDate);

		} catch (EmployeeNotFoundException e1) {
			logger.error("No Employees have been found with the given date of birth: " + empWeddingDate);
		}
		for(Employee eachEmployee:employeeList) {
			System.out.println(" FirstName ="+eachEmployee.getEmpFirstName() + " and PhoneNumber=k77hggbvgbb " + eachEmployee.getEmpPhoneNumber());
		}

		
	}
}
