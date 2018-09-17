package com.nagarro.repository;

import com.nagarro.models.Employee;

import java.util.List;

/**
 * @author Sanyam Goel created on 17/9/18
 */
public interface EmployeeRepository {

    List<Employee> getAllEmployees();

    void addEmployee(Employee employee);

    void addAllEmployees(List<Employee> employees);

    void updateEmployee(Employee employee);

}
