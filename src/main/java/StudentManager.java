import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StudentManager {

    public static void removeStudentsWithLowGPA(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3.0);
    }

    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3.0) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты курса " + course + ":");
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        // Добавляем студентов с использованием Arrays.asList()
        students.add(new Student("Иван", "Группа 1", 1, Arrays.asList(4, 5, 3)));
        students.add(new Student("Мария", "Группа 2", 2, Arrays.asList(2, 2, 3)));
        students.add(new Student("Петр", "Группа 1", 1, Arrays.asList(5, 5, 5)));
        students.add(new Student("Анна", "Группа 3", 3, Arrays.asList(3, 3, 2)));

        removeStudentsWithLowGPA(students);
        promoteStudents(students);
        printStudents(students, 2);
    }
}