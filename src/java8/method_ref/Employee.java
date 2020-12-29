package java8.method_ref;

/**
 * @Author Tom
 * @Date 2020/6/19 11:36
 * @Version 1.0
 * @Description
 */
public class Employee implements Comparable<Employee>{
    private Integer id;
    private String name;
    private Integer age;
    private Double salary;


    public Employee(Integer id) {
        this.id = id;
        System.out.println("Employee(Integer id)....");
    }

    public Employee(String name) {
        this.name = name;
        System.out.println("Employee(String name)....");

    }

    public Employee(Integer id, String name, Integer age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        int result;
        if(this.getSalary() > o.getSalary()) return 1;
        else if(this.getSalary() < o.getSalary()) return -1;
        else return 0;
    }
}
