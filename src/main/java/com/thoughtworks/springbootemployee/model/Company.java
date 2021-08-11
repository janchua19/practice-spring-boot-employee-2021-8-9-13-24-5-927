package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private List<Company> companies;
    private List<Employee> employees;
    private Integer companyID;

    public Company(List<Company> companies, List<Employee> employees, Integer companyID){
        this.companies = companies;
        this.employees = employees;
        this.companyID = companyID;
    }
}
