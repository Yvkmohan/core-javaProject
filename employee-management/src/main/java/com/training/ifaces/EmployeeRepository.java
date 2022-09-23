package com.training.ifaces;

import java.time.LocalDate;
import java.util.Collection;

import com.training.exceptions.EmployeeNotFoundException;
import com.training.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee> {
	public Collection<Employee> findByFirstName(String empFirstName) throws EmployeeNotFoundException;

	public Collection<Employee> findFirstNameAndNumber() throws EmployeeNotFoundException;

	public boolean updateByEmailAndNumber(String updatedEmail, long empPhoneNumber, String empEmail) throws EmployeeNotFoundException;

	public boolean deleteByFirstName(String empLastName, String empEmail) throws EmployeeNotFoundException;

	public Collection<Employee> findByBirthday(LocalDate empDateOfBirth)
			throws EmployeeNotFoundException;

	public Collection<Employee> findByWeddingDate(LocalDate empWeddingDate)
			throws EmployeeNotFoundException;
}
