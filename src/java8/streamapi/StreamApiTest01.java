package java8.streamapi;

import beans.Employee;
import java8.training.streamapi.util.EmployeeData;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * @Author Tom
 * @Date 2020/12/6 17:43
 * @Version 1.0
 * @Description
 */
public class StreamApiTest01 {
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.createEmployees();
        long count = employees.stream().filter(employee -> employee.getSalary() > 3000).count();
        System.out.println("employees salary > 3000: " + count);


        Optional<Double> max = employees.stream().map(e -> e.getSalary()).max(Double::compare);
        System.out.println("max salary is: " + max);

    }

    @Test
    public void test2(){

    }
}
