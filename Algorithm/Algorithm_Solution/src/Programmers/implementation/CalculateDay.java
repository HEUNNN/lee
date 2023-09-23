package Programmers.implementation;

public class CalculateDay {
    public static void main(String[] args) {
        String solution = new CalculateDay().solution2(2, 29);
        System.out.println(solution);
    }

    public String solution(int a, int b) {
        String answer = "";
        int[] firstDay = new int[13]; // 각 월의 1일의 요일
        int[] dayCount = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 각 달의 총 일 수
        String[] day = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        initFirstDay(firstDay, dayCount, day);

        int firstDayIdx = firstDay[a]; // a월 1일의 요일을 가리키는 index
        int dayIdx = (b - 1) % 7; // a월 b일의 요일을 가리키는 index
        System.out.println(firstDayIdx);
        System.out.println(dayIdx);

        return day[(firstDayIdx + dayIdx) % 7];
    }

    public String solution2(int a, int b) {
        String answer = "";
        int allDate = 0; // 1월 1일 ~ a월 b일 까지의 모든 날을 count
        String[] day = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        int[] date = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (int i = 1; i < a; i++) { // 1월부터 (a-1)월 까지의 총 Date를 count
            allDate += date[i];
        }

        // a월 b일의 요일 구하기
        allDate -= 1; // 1월 1일은 빼주기
        allDate += b;

        return day[allDate % 7];
    }

    private void initFirstDay(int[] firstDay, int[] dayCount, String[] day) {

        firstDay[1] = 5; // 5 == FRI
        for (int i = 2; i < firstDay.length; i++) {
            int rest = (dayCount[i - 1] - 1) % 7;
            firstDay[i] = (firstDay[i - 1] + rest + 1) % 7; // 이전달의 마지막 날의 요일 index 에 + 1을 해야, 현재 달의 1일의 요일이다.
        }

    }
}
