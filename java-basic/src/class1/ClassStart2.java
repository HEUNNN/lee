package class1;

public class ClassStart2 {
    public static void main(String[] args) {
        // 배열 사용
        String[] studentNames = {"학생1", "학생2", "학생3"};
        int[] studentAges = {15, 17, 18};
        int[] studentGrades = {90, 80, 80};

        for (int i = 0; i < studentNames.length; i++) {
            System.out.println("이름: " + studentNames[i] + " 나이: " + studentAges[i] + " 성적: " + studentGrades[i]);
        }
    }
}
