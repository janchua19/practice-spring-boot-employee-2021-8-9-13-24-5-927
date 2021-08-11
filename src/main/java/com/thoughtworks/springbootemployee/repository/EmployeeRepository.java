package com.thoughtworks.springbootemployee.repository;


import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Carms", 21, "Female", 1000));
        employees.add(new Employee(2, "Jan", 12, "Male", 2000));
        employees.add(new Employee(3, "Ian", 12, "Female", 2000));
        employees.add(new Employee(4, "Red", 12, "Male", 20300));
        employees.add(new Employee(5, "Adomar", 12, "Male", 23000));
        employees.add(new Employee(6, "DM", 12, "Male", 25000));
        employees.add(new Employee(7, "Rhea", 12, "Female", 10000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }


    public Employee findById( Integer employeeId) {
        return employees
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findEmployeesByPagination(Integer pageIndex, Integer pageSize) {
        return employees
                .stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> findEmployeeByGender(String gender) {
        return employees.stream().filter(employee -> employee.getGender().equalsIgnoreCase(gender)).collect(Collectors.toList());
    }
}
