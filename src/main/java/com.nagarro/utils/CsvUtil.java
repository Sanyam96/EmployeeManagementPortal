package com.nagarro.utils;

import com.nagarro.constant.Constants;
import com.nagarro.models.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanyam Goel created on 17/9/18
 */
public class CsvUtil {

    public static List<Employee> getParsedData(MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String row = null;
            String[] data = null;
            List<Employee> employees = new ArrayList<Employee>();
            Employee employee;
            boolean firstRow = true;
            while ((row = reader.readLine()) != null) {
                if (firstRow) {
                    firstRow = false;
                } else {
                    data = row.split(Constants.CSV_SEPERATOR);
                    employee = new Employee();
                    employee.setEmployeeCode(Long.valueOf(data[0]));
                    employee.setEmployeeName(data[1]);
                    employee.setLocation(data[2]);
                    employee.setEmail(data[3]);
                    employee.setDateOfBirth(data[4]);
                    employees.add(employee);
                }
            }
            return employees;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}