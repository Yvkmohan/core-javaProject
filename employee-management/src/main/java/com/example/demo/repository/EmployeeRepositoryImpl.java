package com.example.demo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.training.exceptions.EmployeeNotFoundException;
import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	private Connection con;

	public EmployeeRepositoryImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public Collection<Employee> findAll() throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select * from employee_details";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowToObject(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowToObject(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-100", "Employee is not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean save(Employee obj) {
		String sql = "insert into employee_details values (?,?,?,?,?,?,?)";
		int rowUpdated = 0;
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, obj.getEmpFirstName());
			pstmt.setString(2, obj.getEmpLastName());
			pstmt.setString(3, obj.getEmpAddress());
			pstmt.setString(4, obj.getEmpEmail());
			pstmt.setLong(5, obj.getEmpPhoneNumber());
			Date empDateOfBirth = Date.valueOf(obj.getEmpDateOfBirth());
			pstmt.setDate(6, empDateOfBirth);
			Date empWeddingDate = null;
			if (obj.getEmpWeddingDate() != null) {
				empWeddingDate = Date.valueOf(obj.getEmpWeddingDate());
			}
			pstmt.setDate(7, empWeddingDate);
			rowUpdated = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated == 1 ? true : false;
	}

	@Override
	public Collection<Employee> findByFirstName(String empFirstName) throws EmployeeNotFoundException {
		Collection<Employee> employeeList = new ArrayList<>();
		employeeList = findAll().stream().filter(e -> e.getEmpFirstName().equals(empFirstName)).collect(Collectors.toList());
		if (employeeList.isEmpty()) {
			throw new EmployeeNotFoundException("ERR-102", "Employee Not found with the given name: " + empFirstName);
		} else {
			return employeeList;
		}
	}

	@Override
	public Collection<Employee> findFirstNameAndNumber() throws EmployeeNotFoundException {
		Collection<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select empFirstName,empPhoneNumber from employee_details";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowFirstNameAndNumber(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowFirstNameAndNumber(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-103", "No Employees Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean updateByEmailAndNumber(String updatedEmail, long empPhoneNumber, String empEmail)
			throws EmployeeNotFoundException {
		String sql = "update employee_details SET empEmail=?, empPhoneNumber=? where empEmail=?";
		int rowUpdated = 0;
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, updatedEmail);
			statement.setLong(2, empPhoneNumber);
			statement.setString(3, empEmail);
			rowUpdated = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rowUpdated == 1) {
			return true;
		} else {
			throw new EmployeeNotFoundException("ERR-104",
				"Employee with the given email: " + empEmail + " cannot be found.So this employee cannot be updated");
		}

	}

	@Override
	public boolean deleteByFirstName(String empFirstName, String empEmail) throws EmployeeNotFoundException {
		int rowDeleted = 0;
		String sql = "delete from employee_details where empFirstName=? and empEmail=?";
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, empFirstName);
			statement.setString(2, empEmail);
			rowDeleted = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rowDeleted == 1) {
			return true;
		} else {
			throw new EmployeeNotFoundException("ERR-105",
					"Particular Employee with the given name: " + empFirstName + " and email: " + empEmail + " is not found");
		}
	}

	@Override
	public Collection<Employee> findByBirthday(LocalDate empDateOfBirth)
			throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select empFirstName,empEmail from employee_details where MONTH(empDateOfBirth)=? and DAY(empDateOfBirth)=?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			Date date = Date.valueOf(empDateOfBirth);
			pstmt.setInt(1, date.getMonth()+1);
			pstmt.setInt(2, date.getDate());
			System.out.println(date.getMonth()+" "+date.getDate());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowFirstNameAndEmail(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowFirstNameAndEmail(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-106",
						"No Employees have been found with the given date of birth: " + empDateOfBirth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public Collection<Employee> findByWeddingDate(LocalDate empWeddingDate)
			throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select empFirstName,empPhoneNumber from employee_details where  MONTH(empWeddingDate)=? and DAY(empWeddingDate)=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			Date date = Date.valueOf(empWeddingDate);
			pstmt.setInt(1, date.getMonth()+1);
			pstmt.setInt(2, date.getDate());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowFirstNameAndNumber(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowFirstNameAndNumber(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-107",
						"No Employees have been found with the given Wedding Date: " + empWeddingDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	private Employee mapRowToObject(ResultSet resultSet) throws SQLException {
		String empFirstName = resultSet.getString("empFirstName");
		String empLastName = resultSet.getString("empLastName");
		String empAddress = resultSet.getString("empAddress");
		String empEmail = resultSet.getString("empEmail");
		long empPhoneNumber = resultSet.getLong("empPhoneNumber");
		LocalDate empDateOfBirth = resultSet.getDate("empDateOfBirth").toLocalDate();
		LocalDate empWeddingDate = null;
		if (resultSet.getDate("empWeddingDate") != null) {
			empWeddingDate = resultSet.getDate("empWeddingDate").toLocalDate();
		}
		return new Employee(empFirstName, empLastName, empAddress, empEmail, empPhoneNumber, empDateOfBirth, empWeddingDate);
	}

	private Employee mapRowFirstNameAndNumber(ResultSet resultSet) throws SQLException {
		String empFirstName = resultSet.getString("empFirstName");
		long empPhoneNumber = resultSet.getLong("empPhoneNumber");
		return new Employee(empFirstName, empPhoneNumber);
	}

	private Employee mapRowFirstNameAndEmail(ResultSet resultSet) throws SQLException {
		String empFirstName = resultSet.getString("empFirstName");
		String empEmail = resultSet.getString("empEmail");
		return new Employee(empFirstName, empEmail);
	}

}
