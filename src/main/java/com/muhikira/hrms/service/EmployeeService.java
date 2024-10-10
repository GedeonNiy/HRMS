package com.muhikira.hrms.service;

import com.muhikira.hrms.dto.DepartmentDto;
import com.muhikira.hrms.exception.DepartmentNotFoundException;
import com.muhikira.hrms.model.Employee;
import com.muhikira.hrms.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private DepartmentServiceClient departmentServiceClient;

  public Employee createEmployee(Employee employee) {
    // Check if the department exists
    departmentServiceClient
        .getDepartmentById(employee.getDepartmentId())
        .onErrorResume(
            e -> {
              throw new DepartmentNotFoundException(
                  "Department not found for ID: " + employee.getDepartmentId());
            })
        .block();

    // If department is not found, an exception is thrown.
    // If it exists, save the employee with the departmentId.
    return employeeRepository.save(employee);
  }

  // Get all employees
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  // Get an employee by ID
  public Optional<Employee> getEmployeeById(Long id) {
    Optional<Employee> employee = employeeRepository.findById(id);

    employee.ifPresent(
        emp -> {
          DepartmentDto departmentDto =
              departmentServiceClient
                  .getDepartmentById(emp.getDepartmentId())
                  .block(); // Blocking for synchronous call
          emp.setDepartmentId(departmentDto.getId());
        });

    return employee;
  }

  // Get employees by first name
  public List<Employee> getEmployeesByFirstName(String firstName) {
    return employeeRepository.findByFirstName(firstName);
  }

  // Get employees by last name
  public List<Employee> getEmployeesByLastName(String lastName) {
    return employeeRepository.findByLastName(lastName);
  }

  // Get employees by department
  public List<Employee> getEmployeesByDepartment(String department) {
//    return employeeRepository.findByDepartment(department);
    return null;
  }

  // Get employees by salary greater than or equal to a value
  public List<Employee> getEmployeesBySalary(BigDecimal salary) {
    return employeeRepository.findBySalaryGreaterThanEqual(salary);
  }

  // Get employees by age
  public List<Employee> getEmployeesByAge(int age) {
    return employeeRepository.findByAge(age);
  }

  // Update an existing employee
  public Employee updateEmployee(Long id, Employee employeeDetails) {
    Employee employee =
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));

    employee.setFirstName(employeeDetails.getFirstName());
    employee.setLastName(employeeDetails.getLastName());
    employee.setEmail(employeeDetails.getEmail());
    employee.setPhone(employeeDetails.getPhone());
    employee.setDateOfBirth(employeeDetails.getDateOfBirth());
    employee.setPlaceOfBirth(employeeDetails.getPlaceOfBirth());
    employee.setPosition(employeeDetails.getPosition());
    employee.setDepartmentId(employeeDetails.getDepartmentId());
    employee.setHireDate(employeeDetails.getHireDate());
    employee.setSalary(employeeDetails.getSalary());

    return employeeRepository.save(employee);
  }

  // Delete an employee by ID
  public void deleteEmployee(Long id) {
    Employee employee =
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    employeeRepository.delete(employee);
  }
}
