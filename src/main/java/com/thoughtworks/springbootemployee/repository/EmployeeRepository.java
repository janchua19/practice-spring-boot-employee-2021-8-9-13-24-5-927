package com.thoughtworks.springbootemployee.repository;


import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Ian", 44, "Female", 20000));
        employees.add(new Employee(2, "Adomar", 50, "Male", 1000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }


}
