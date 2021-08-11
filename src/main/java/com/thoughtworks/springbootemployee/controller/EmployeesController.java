package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private List<Employee> employees = new ArrayList<>();

    public EmployeesController() {

        employees.add(new Employee(1, "Carms", 21, "Female", 1000));
        employees.add(new Employee(2, "Jan Edward", 12, "Male", 2000));

    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findById(@PathVariable Integer employeeId) {
        return employees
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

//    @GetMapping(path = "?page={page}&pageSize={pageSize}")
//    public List<Employee> findEmployeesByPageAndPageSize(@PathVariable Integer page,
//                                                         @PathVariable Integer pageSize) {
//
//     //   return employees.stream().filter(employee -> employees.indexOf(employee) <= page - 1).collect(Collectors.toList());
//
//    }

    @GetMapping(params = "gender")
    public List<Employee> findEmployeeByGender(@RequestParam(required = true) String gender) {
        return employees.stream().filter(employee -> employee.getGender().equalsIgnoreCase(gender)).collect(Collectors.toList());
    }

}
