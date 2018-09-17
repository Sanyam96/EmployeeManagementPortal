package com.nagarro.services;

import com.nagarro.constant.Constants;
import com.nagarro.models.Employee;
import com.nagarro.repository.EmployeeRepository;
import com.nagarro.utils.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.List;

/**
 * @author Sanyam Goel created on 17/9/18
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public void addAllEmployees(MultipartFile file) {
        List<Employee> employees = CsvUtil.getParsedData(file);
        if (employees != null) {
            employeeRepository.addAllEmployees(employees);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.updateEmployee(employee);
    }

    @Override
    public void addEmployeeDetailsToFile(ICsvBeanWriter csvBeanWriter) throws IOException {
        List<Employee> employees = this.getAllEmployees();
        csvBeanWriter.writeHeader(Constants.DISPLAY_HEADER);
        for (Employee employee : employees) {
            csvBeanWriter.write(employee, Constants.BEAN_HEADER);
        }
    }

}