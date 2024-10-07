package com.muhikira.hrms.controller;

import com.muhikira.hrms.model.Employee;
import com.muhikira.hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
/*

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public void createEmployee(@RequestBody Employee employee)
    {
        employeeService.saveEmployee(employee);
    }

    @GetMapping("/getEmployees")
  @Parameter(name = "getMapping", example = "sample API", description = "This is API will get the list of all APIs")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
*/
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get employees by first name
    @GetMapping("/firstName/{firstName}")
    public List<Employee> getEmployeesByFirstName(@PathVariable String firstName) {
        return employeeService.getEmployeesByFirstName(firstName);
    }

    // Get employees by last name
    @GetMapping("/lastName/{lastName}")
    public List<Employee> getEmployeesByLastName(@PathVariable String lastName) {
        return employeeService.getEmployeesByLastName(lastName);
    }

    // Get employees by department
    @GetMapping("/department/{department}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    // Get employees by salary greater than or equal to a value
    @GetMapping("/salary/{salary}")
    public List<Employee> getEmployeesBySalary(@PathVariable BigDecimal salary) {
        return employeeService.getEmployeesBySalary(salary);
    }

    // Get employees by age
    @GetMapping("/age/{age}")
    public List<Employee> getEmployeesByAge(@PathVariable int age) {
        return employeeService.getEmployeesByAge(age);
    }

    // Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
