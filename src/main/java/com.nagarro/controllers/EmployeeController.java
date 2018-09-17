package com.nagarro.controllers;

import com.nagarro.constant.Constants;
import com.nagarro.models.Employee;
import com.nagarro.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * All Employee Management Controllers
 *
 * @author Sanyam Goel created on 17/9/18
 */
@Controller
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * GET method to get Employee List
     *
     * @param model
     * @param request
     * @return JSP PAGE response
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getEmployeeList(Model model, HttpServletRequest request) {
        String response;
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null && httpSession.getAttribute("userId") != null) {
            List<Employee> employees = employeeService.getAllEmployees();
            model.addAttribute("employees", employees);
            response = "employeeListPage";
        } else {
            response = "redirect:/login";
        }
        return response;
    }

    /**
     * POST method to upload FILES and redirect to employeeJSP
     *
     * @param file
     * @param model
     * @return redirect to
     */
    @RequestMapping(method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        employeeService.addAllEmployees(file);
        return "redirect:/employee";

    }

    /**
     * POST method to edit employee
     *
     * @param employee
     * @param model
     * @return edit employee JSP page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute("employee") Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "editEmployeePage";
    }

    /**
     * POST method to redirect to employee page after editing of employee
     *
     * @param employee
     * @param model
     * @return employee JSP page
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute("employee") Employee employee, Model model) {
        employeeService.updateEmployee(employee);
        return "redirect:/employee";
    }

    /**
     * POST method to download all employee data using CSV
     *
     * @param response
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void downloadFile(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=" + Constants.CSV_FILE_NAME);
        try (ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE)) {
            employeeService.addEmployeeDetailsToFile(csvBeanWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}