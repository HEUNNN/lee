package Programmers.lv2;

public class SumOfSubsequences {
    public static void main(String[] args) {
        SumOfSubsequences sumOfSubsequences = new SumOfSubsequences();
        int[] sequence1 = {1, 2, 3, 4, 5};
        int[] sequence2 = {1, 1, 1, 2, 3, 4, 5};
        int[] sequence3 = {2, 2, 2, 2, 2};

        int[] answer = sumOfSubsequences.solution(sequence1, 7);
        for (int i : answer) {
            System.out.println(i);
        }
    }

    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[]{0, sequence.length - 1};
        int leftIndex = 0;
        int rightIndex = 1;
        int sum = sequence[0];



        while (leftIndex < rightIndex) {
            if (sum == k) {
                // answer 결정 → (sum == k) 분기문의 위치를 고려할 것
                // 앞에서 부터 값을 더해가기 때문에 최초 sum == k 일 때 값을 asnwer에 저장해두되 또 sum == k가 나와도 갱신하지 않는다.
                // -> 앞쪽에 나오는 부분 수열을 정답으로 하기 위해서
                if (((rightIndex - 1) - leftIndex) < answer[1] - answer[0]) {
                    answer[0] = leftIndex;
                    answer[1] = rightIndex - 1;
                }
                sum -= sequence[leftIndex++];
            } else if (sum > k) {
                sum -= sequence[leftIndex++];
            } else if (rightIndex < sequence.length) { // sum < k && (rightIndex < sequence.length)
                sum += sequence[rightIndex++];
            } else {
                break;
            }

        }

        return answer;
    }
}
