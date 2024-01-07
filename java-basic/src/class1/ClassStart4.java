package class1;

public class ClassStart4 {
    // 배열과 인스턴스를 사용하여 특정 타입(Class)을 연속한 데이터 구조로 묶어서 사용
    public static void main(String[] args) {
        Student student1 = new Student("학생1", 15, 90);
        Student student2 = new Student("학생2", 17, 80);
        Student student3 = new Student("학생3", 18, 80);

        Student[] students = {student1, student2, student3};

        for (Student student : students) {
            System.out.println("이름: " + student.name + " 나이: " + student.age + " 성적: " + student.grade);
        }
    }
}
