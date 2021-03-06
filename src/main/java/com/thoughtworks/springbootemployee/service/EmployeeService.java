package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getAllEmployees() {

        return employeeRepository.getEmployees();
    }

    public Employee findById(@PathVariable Integer employeeId) {
        return employeeRepository.findById(1);
    }

    public List<Employee> findEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return employeeRepository.findEmployeesByPagination(1,5);
    }

    public List<Employee> findEmployeeByGender(@RequestParam(required = true) String gender) {
        return employeeRepository.findEmployeeByGender("Male");
    }

    public void addEmployee(@RequestBody Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated) {
        return employeeRepository.updateEmployee(employeeId, employeeToBeUpdated);
    }


    public void deleteEmployee (@PathVariable Integer employeeId){
        employeeRepository.deleteEmployee(employeeId);
    }
}
