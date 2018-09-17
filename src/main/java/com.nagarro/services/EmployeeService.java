package com.nagarro.services;

import com.nagarro.models.Employee;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.List;

/**
 * @author Sanyam Goel created on 17/9/18
 */
public interface EmployeeService {

    List<Employee> getAllEmployees();

    void addAllEmployees(MultipartFile file);

    void updateEmployee(Employee employee);

    void addEmployeeDetailsToFile(ICsvBeanWriter csvBeanWriter) throws IOException;


}