package java8.training.streamapi.util;

import beans.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Tom
 * @Date 2020/12/1 10:50
 * @Version 1.0
 * @Description
 */
public class EmployeeData {
    public static List<Employee> createEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1002, "jerry", 40, 2000));
        employeeList.add(new Employee(1003, "dale", 30, 3000));
        employeeList.add(new Employee(1004, "haiLun", 50, 4000));
        employeeList.add(new Employee(1005, "peter", 60, 5000));
        employeeList.add(new Employee(1001, "jack", 25, 3500));
        employeeList.add(new Employee(1001, "tom", 20, 1000));
        return employeeList;
    }
}
