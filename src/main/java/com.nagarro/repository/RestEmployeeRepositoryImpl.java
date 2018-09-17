package com.nagarro.repository;

import com.nagarro.constant.Constants;
import com.nagarro.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Rest API consumption layer
 *
 * @author Sanyam Goel created on 17/9/18
 */
@Repository
public class RestEmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    RestTemplate restTemplate;

    /**
     * get all employees via rest template
     *
     * @return
     */
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees;
        String url = Constants.REST_API_SLUG + Constants.GET_ALL_EMPLOYEES;
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                });
        employees = response.getBody();
        return employees;
    }

    /**
     * to add employee
     *
     * @param employee
     */
    @Override
    public void addEmployee(Employee employee) {
        String url = Constants.REST_API_SLUG + Constants.POST_EMPLOYEE;
        restTemplate.postForObject(url, employee, Employee.class);
    }

    /**
     * to add all employees
     *
     * @param employees
     */
    @Override
    public void addAllEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            this.addEmployee(employee);
        }
    }

    /**
     * Rest template to call update end point to update employee
     * @param employee
     */
    @Override
    public void updateEmployee(Employee employee) {
        String url = Constants.REST_API_SLUG + Constants.PUT_EMPLOYEE;
//        String url = Constants.REST_API_URL_EMPLOYEE + "/" + employee.getId();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
////        map.add("email", "first.last@example.com");
//        map.add("employeeCode", employee.getEmployeeCode()+"");
//        map.add("employeeName", employee.getEmployeeName());
//        map.add("location", employee.getLocation());
//        map.add("email", employee.getEmail());
//        map.add("dateOfBirth", employee.getDateOfBirth());
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//        restTemplate.put(url, request, String.class);
        restTemplate.put(url, employee);
    }

}