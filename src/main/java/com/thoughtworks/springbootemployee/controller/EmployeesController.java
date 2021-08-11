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
        employees.add(new Employee(2, "Jan", 12, "Male", 2000));
        employees.add(new Employee(3, "Ian", 12, "Female", 2000));
        employees.add(new Employee(4, "Red", 12, "Male", 20300));
        employees.add(new Employee(5, "Adomar", 12, "Male", 23000));
        employees.add(new Employee(6, "DM", 12, "Male", 25000));
        employees.add(new Employee(7, "Rhea", 12, "Female", 10000));
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

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> findEmployeesByPagination (@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return employees
                .stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }


    @GetMapping(params = "gender")
    public List<Employee> findEmployeeByGender(@RequestParam(required = true) String gender) {
        return employees.stream().filter(employee -> employee.getGender().equalsIgnoreCase(gender)).collect(Collectors.toList());
    }

}
