package com.training.model;

import java.time.LocalDate;
import java.util.Scanner;
import com.example.demo.sevices.EmployeeService;
import com.training.exceptions.EmployeeNotFoundException;

public class App {

	public static LocalDate getWeddingChoice(Scanner input) {
		while (true) {
			System.out.println("Are you married? y/n or Y/N");
			String weddingChoice = input.nextLine();
			if (weddingChoice.equalsIgnoreCase("y")) {
				System.out.println("Wedding date:");
				LocalDate empWeddingDate = LocalDate.parse(input.nextLine());
				return empWeddingDate;
			}
			else if (weddingChoice.equalsIgnoreCase("n")) {
				return null;
			}
			else {
				System.out.println("PROVIDE VALID OPTION!!!!!!!!!!!");
			}
		}
	}

	public static void takingEmployeeDetailsFromUser() throws EmployeeNotFoundException {
		Scanner input = new Scanner(System.in);
		EmployeeService service = new EmployeeService();
		while (true) {
			System.out.println("Enter number between 1 to 7");
			System.out.println("1 Represents Adding the Employee");
			System.out.println("2 Represents Finding Employees By Their First Name");
			System.out.println("3 Represents Find each employee's first name and phone number");
			System.out.println("4 Represents Update an Employee's Email and Phone Number");
			System.out.println("5 Represents Delete Employee by First Name");
			System.out.println("6 Represents Discover the First Name and Email of every Employee by Birthday");
			System.out.println("7 Represents Discover every employee's first name and phone number by wedding date");
			System.out.println("Enter the number");
			int choice = Integer.parseInt(input.nextLine());
			if (choice == 1) {
				System.out.println("-------------Enter required details to add Employee---------------------");
				System.out.println("First Name:");
				String empFirstName = input.nextLine();
				System.out.println("Last Name:");
				String empLastName = input.nextLine();
				System.out.println("Address:");
				String empAddress = input.nextLine();
				System.out.println("Email:");
				String empEmail = input.nextLine();
				System.out.println("Phone Number:");
				long empPhoneNumber=Long.parseLong(input.nextLine());
				System.out.println("Date of birth:");
				LocalDate empDateOfBirth = LocalDate.parse(input.nextLine());
				LocalDate empWeddingDate =getWeddingChoice(input);
				service.save(new Employee(empFirstName, empLastName, empAddress, empEmail, empPhoneNumber, empDateOfBirth, empWeddingDate));
			} else if (choice == 2) {
				System.out.println("2->Find Employees By First Name");
				System.out.println("First Name:");
				String empFirstName = input.nextLine();
				service.findByFirstName(empFirstName);
			} else if (choice == 3) {
				System.out.println("3->Find First Name and Phone Number of all Employees");
				service.findFirstNameAndNumber();
			} else if (choice == 4) {
				System.out.println("4->Update an Employee's Email and Phone Number");
				System.out.println("Updated Email:");
				String updatedEmail = input.nextLine();
				System.out.println("Phone Number:");
				 long empPhoneNumber=Long.parseLong(input.nextLine());
				System.out.println("Old Email:");
				String empEmail = input.nextLine();
				service.updateByEmailAndNumber(updatedEmail, empPhoneNumber, empEmail);
			} else if (choice == 5) {
				System.out.println("5->Delete Employee by First Name");
				System.out.println("First Name:");
				String empFirstName = input.nextLine();
				System.out.println("Email:");
				String empEmail = input.nextLine();
				service.deleteByFirstName(empFirstName, empEmail);
			} else if (choice == 6) {
				System.out.println("6->Find all employees' first names and email addresses by birthdate");
				System.out.println("Date of birth:");
				LocalDate empDateOfBirth = LocalDate.parse(input.nextLine());
				service.findByBirthday(empDateOfBirth);
			} else if (choice == 7) {
				System.out.println("7->Find all employees' first names and phone numbers by their wedding dates");
				System.out.println("Wedding date:");
				LocalDate empWeddingDate = LocalDate.parse(input.nextLine());
				service.findByWeddingDate(empWeddingDate);
			} else {
				System.out.println("***********ONLY ENTER THE NUMBER BETWEEN 1 TO 7*******************");
				continue;
			}
			if (!getContinueChoice(input, service)) {
				break;
			}

		}

	}

	public static boolean getContinueChoice(Scanner input, EmployeeService service) throws EmployeeNotFoundException {
		while (true) {
			System.out.println("Do you want to continue? y/n or Y/N");
			String willingToContinue = input.nextLine();
			if (willingToContinue.equalsIgnoreCase("y")) {
				return true;

			} else if (willingToContinue.equalsIgnoreCase("n")) {
				input.close();
				System.out.println("Successfully Exited from the Menu");
				return false;

			} else {
				System.out.println("********************PROVIDE VALID OPTION***************");
			}
		}

	}

	public static void main(String[] args) throws EmployeeNotFoundException {
		takingEmployeeDetailsFromUser();
	}

}
