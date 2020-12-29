package java8.training.streamapi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author Tom
 * @Date 2020/12/7 14:53
 * @Version 1.0
 * @Description
 */
public class StreamApiTest01 {

    private static List<Student> studentList = Arrays.asList(
            new Student("tony", 90),
            new Student("tom", 80),
            new Student("alex", 100),
            new Student("peter", 68));



    public static void main(String[] args) {
 /*       Stream<Student> stream = studentList.stream();
        // [student1, student2, student3]
        //[80,90,100]
        Stream<Integer> integerStream = stream.map(student -> student.getEnglishEcore());

        integerStream.max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        Optional<Integer> max = integerStream.max(Integer::compare);
        System.out.println("最高分：" + max.get());*/

/*        Optional<Integer> max = studentList.stream()
                .filter(student -> student.getEnglishEcore() >= 90 ? true : false)
                .map(student -> student.getEnglishEcore())
                .max(Integer::compare);
        System.out.println("最高分:" + max.get());*/
        final Student herry = new Student("herry", 40);

        System.out.println(studentList);

        Stream<Student> stream1 = studentList.stream();

        Optional<Integer> sum = stream1.map(Student::getEnglishEcore)
                .reduce((o1, o2) -> o1 + o2);

/*        int sum = stream1.map(student -> student.getEnglishEcore())
                .mapToInt(Integer::intValue)
                .sum();

        Stream<Student> stream2 = studentList.stream();
        OptionalDouble average = stream2.map(student -> student.getEnglishEcore())
                .mapToInt(Integer::intValue)
                .average();*/

        System.out.println("总分:"+sum.get());
//        System.out.println("平均分：" + average.getAsDouble());
    }
}

class Student implements Comparable {
    private String name;
    private Integer englishEcore;

    public Student(String name, Integer englishEcore) {
        this.name = name;
        this.englishEcore = englishEcore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnglishEcore() {
        return englishEcore;
    }

    public void setEnglishEcore(Integer englishEcore) {
        this.englishEcore = englishEcore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", englishEcore=" + englishEcore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(englishEcore, student.englishEcore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, englishEcore);
    }

    @Override
    public int compareTo(Object o) {
        Student target = (Student) o;
        return this.getEnglishEcore() > target.getEnglishEcore() ? 1 : this.getEnglishEcore() == target.getEnglishEcore() ? 0 : -1;
    }
}
