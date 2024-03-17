package newframework.factories;

import models.MockStudent;
import models.Student;

public class StudentFactory {

    public static Student createRegularStudent() {
        return new Student("Adam", "Malysz", "Nowak", "04/03/1985");
    }

    public Student createCustomizedStudent() {
        return Student.builder().build();
    }

    public MockStudent getRandomStudent(String name) {
        return new MockStudent(name);
    }
}
