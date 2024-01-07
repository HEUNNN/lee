package ref;

public class Method2 {
    public static void main(String[] args) {
        Student student1 = createStudent("학생1", 15, 90); // student1도 지역변수이다. main 함수에 선언되어 있기 때문이다.
        Student student2 = createStudent("학생2", 16, 80); // 지역변수는 수동으로 초기화해주어야한다.

        printStudent(student1);
        printStudent(student2);
    }

    static Student createStudent(String name, int age, int grade) {
        Student student = new Student();
        student.name = name;
        student.age = age;
        student.grade = grade;
        return student; // Student 객체 참조값 반환
    }
    static void printStudent(Student student) {
        System.out.println("이름: " + student.name + ", 나이: " + student.age + ", 성적: " + student.grade);
    }
}
