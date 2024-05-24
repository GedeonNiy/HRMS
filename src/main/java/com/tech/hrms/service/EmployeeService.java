package com.tech.hrms.service;

import com.tech.hrms.model.Employee;
//import com.tech.hrms.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
//    @Autowired
//    private EmployeeRepository employeeRepository;
List<Employee> list = new ArrayList<> ();
    public void saveEmployee(Employee employee) {
        list.add(employee);
//         employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
       return list;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return list.stream ().filter ( employee -> employee.getId ().equals ( id ) ).findFirst ();
//        return employeeRepository.findById(id);
//        for (Employee employee : list) {
//            if (employee.getId().equals(id)) {
//                return Optional.of(employee);
//
//            }else {
//                return Optional.empty();
//            }
//
//        }
//        return Optional.empty();
    }

}
