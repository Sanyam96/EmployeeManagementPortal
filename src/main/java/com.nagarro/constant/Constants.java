package com.nagarro.constant;

/**
 * Constants File
 * @author Sanyam Goel created on 17/9/18
 */
public class Constants {

    public static final String REST_API_SLUG = "http://localhost:9898/";
    public static final String GET_ALL_EMPLOYEES = "employees/";
    public static final String GET_EMPLOYEE = "employee/";
    public static final String POST_EMPLOYEE = "employee/";
    public static final String PUT_EMPLOYEE = "employee/";
    public static final String CSV_SEPERATOR = ",";
    public static final String CSV_FILE_NAME = "EmployeeData.csv";
    public static final String[] DISPLAY_HEADER = {"Id", "Employee Code", "Employee Name", "Location", "Email", "Date Of Birth"};
    public static final String[] BEAN_HEADER = {"id", "employeeCode", "employeeName", "location", "email", "dateOfBirth"};

    private static Constants constantParamsObject = null;

    private Constants() {
        super();
    }

    public static final Constants getObject() {
        if (constantParamsObject == null) {
            constantParamsObject = new Constants();
        }
        return constantParamsObject;
    }
}
