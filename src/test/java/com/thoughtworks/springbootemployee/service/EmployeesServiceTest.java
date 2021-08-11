package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeesServiceTest {

    @InjectMocks
    private EmployeeService employeeService;


    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_getAllEmployees_given_none() {

        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Carms", 21, "Female", 1000));
        employees.add(new Employee(2, "Jan", 12, "Male", 2000));
        employees.add(new Employee(3, "Ian", 12, "Female", 2000));
        employees.add(new Employee(4, "Red", 12, "Male", 20300));
        employees.add(new Employee(5, "Adomar", 12, "Male", 23000));
        employees.add(new Employee(6, "DM", 12, "Male", 25000));
        employees.add(new Employee(7, "Rhea", 12, "Female", 10000));
        given(employeeRepository.getEmployees())
                .willReturn(employees);

        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //then

        assertEquals(employees.size(), actualEmployees.size());
        assertIterableEquals(employees, actualEmployees);

    }

    @Test
    public void should_return_employee_when_retrieveEmployee_given_employee_id(){
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Carms", 21, "Female", 1000));
        employees.add(new Employee(2, "Jan", 12, "Male", 2000));
        employees.add(new Employee(3, "Ian", 12, "Female", 2000));
        employees.add(new Employee(4, "Red", 12, "Male", 20300));
        employees.add(new Employee(5, "Adomar", 12, "Male", 23000));
        employees.add(new Employee(6, "DM", 12, "Male", 25000));
        employees.add(new Employee(7, "Rhea", 12, "Female", 10000));
        given(employeeRepository.findById(1))
                .willReturn(employees.get(0));
        //when
        Employee actualEmployee = employeeService.findById(1);
        //then
        assertEquals(employees.get(0), actualEmployee);
    }

    @Test
    public void should_return_employee_with_id_1_to_5_when_findEmployeeByPagination_given_pageIndex_1_and_pageSize_5() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Carms", 21, "Female", 1000));
        employees.add(new Employee(2, "Jan", 12, "Male", 2000));
        employees.add(new Employee(3, "Ian", 12, "Female", 2000));
        employees.add(new Employee(4, "Red", 12, "Male", 20300));
        employees.add(new Employee(5, "Adomar", 12, "Male", 23000));
        employees.add(new Employee(6, "DM", 12, "Male", 25000));
        employees.add(new Employee(7, "Rhea", 12, "Female", 10000));
        given(employeeRepository.findEmployeesByPagination(1, 5))
                .willReturn(Collections.singletonList(employees.get(0)));
        //when
        List<Employee> actualEmployees = employeeService.findEmployeesByPagination(1, 5);
        //then
        assertEquals(Collections.singletonList(employees.get(0)), actualEmployees);
    }

    @Test
    public void should_return_employee_that_is_male_when_findEmployeeByGender_given_gender() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Carms", 21, "Female", 1000));
        employees.add(new Employee(2, "Jan", 12, "Male", 2000));
        employees.add(new Employee(3, "Ian", 12, "Female", 2000));
        employees.add(new Employee(4, "Red", 12, "Male", 20300));
        employees.add(new Employee(5, "Adomar", 12, "Male", 23000));
        employees.add(new Employee(6, "DM", 12, "Male", 25000));
        employees.add(new Employee(7, "Rhea", 12, "Female", 10000));
        given(employeeRepository.findEmployeeByGender("Male"))
                .willReturn(Collections.singletonList(employees.get(0)));
        //when
        List<Employee> actualEmployees = employeeService.findEmployeeByGender("Male");
        //then
        assertEquals(Collections.singletonList(employees.get(0)), actualEmployees);
    }

    @Test
    public void should_return_new_employee_added_when_addNewEmployee_given_employee() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Carms", 21, "Female", 1000));
        employees.add(new Employee(2, "Jan", 12, "Male", 2000));
        employees.add(new Employee(3, "Ian", 12, "Female", 2000));
        employees.add(new Employee(4, "Red", 12, "Male", 20300));
        employees.add(new Employee(5, "Adomar", 12, "Male", 23000));
        employees.add(new Employee(6, "DM", 12, "Male", 25000));
        employees.add(new Employee(7, "Rhea", 12, "Female", 10000));


        //when
        Employee newEmployee = new Employee(employees.size() + 1, "NewTwo", 22, "Female", 10000);
        employees.add(newEmployee);
        //then
        assertEquals(employees.size(), newEmployee.getId());
    }

//    @Test
//    public void should_update_employee_when_UpdateEmployee_given() {
//        //given
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee(1, "Carms", 21, "Female", 1000));
//        employees.add(new Employee(2, "Jan", 12, "Male", 2000));
//        employees.add(new Employee(3, "Ian", 12, "Female", 2000));
//        employees.add(new Employee(4, "Red", 12, "Male", 20300));
//        employees.add(new Employee(5, "Adomar", 12, "Male", 23000));
//        employees.add(new Employee(6, "DM", 12, "Male", 25000));
//        employees.add(new Employee(7, "Rhea", 12, "Female", 10000));
//
//
//        //when
//        Employee newEmployee = new Employee(employees.size() + 1, "NewTwo", 22, "Female", 10000);
//        employees.add(newEmployee);
//        //then
//        assertEquals(employees.size(), newEmployee.getId());
//    }
}
