package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {


    private List<Employee> employees = new ArrayList<>();


    @Autowired
    private EmployeeService employeeService;

//    public EmployeesController() {
//
//        employees.add(new Employee(1, "Carms", 21, "Female", 1000));
//        employees.add(new Employee(2, "Jan", 12, "Male", 2000));
//        employees.add(new Employee(3, "Ian", 12, "Female", 2000));
//        employees.add(new Employee(4, "Red", 12, "Male", 20300));
//        employees.add(new Employee(5, "Adomar", 12, "Male", 23000));
//        employees.add(new Employee(6, "DM", 12, "Male", 25000));
//        employees.add(new Employee(7, "Rhea", 12, "Female", 10000));
//    }


    public EmployeesController(EmployeeService employeeService) {
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
        //return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findById(@PathVariable Integer employeeId) {
//        return employees
//                .stream()
//                .filter(employee -> employee.getId().equals(employeeId))
//                .findFirst()
//                .orElse(null);
        return employeeService.findById(1);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> findEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
//        return employees
//                .stream()
//                .skip((pageIndex - 1) * pageSize)
//                .limit(pageSize)
//                .collect(Collectors.toList());
        return employeeService.findEmployeesByPagination(1,5);
    }

    @GetMapping(params = "gender")
    public List<Employee> findEmployeeByGender(@RequestParam(required = true) String gender) {
        return employees.stream().filter(employee -> employee.getGender().equalsIgnoreCase(gender)).collect(Collectors.toList());
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = new Employee(employees.size() + 1, employee.getName(), employee.getAge(),
                employee.getGender(), employee.getSalary());
        employees.add(newEmployee);

    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated) {
        return employees
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployeeInformation(employee, employeeToBeUpdated))
                .orElse(null);
    }

    private Employee updateEmployeeInformation(Employee employee, Employee employeeToBeUpdated) {
        if (employeeToBeUpdated.getName() != null) {
            employee.setName(employeeToBeUpdated.getName());
        }
        if (employeeToBeUpdated.getAge() != null) {
            employee.setAge(employeeToBeUpdated.getAge());
        }
        if (employeeToBeUpdated.getGender() != null) {
            employee.setGender(employeeToBeUpdated.getGender());
        }
        if (employeeToBeUpdated.getSalary() != null) {
            employee.setSalary(employeeToBeUpdated.getSalary());
        }
          return employee;
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee (@PathVariable Integer employeeId){
        employees
                .stream().filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .ifPresent(employees :: remove);
    }
}

