package programmers.lv2;

public class BinaryConvert {
    // https://school.programmers.co.kr/learn/courses/30/lessons/70129
    public static void main(String[] args) {
        String x = "1111111";
        String binaryString = Integer.toBinaryString(4);
        System.out.println(binaryString);
        int[] answer = getResult(x);
        for (int i : answer) {
            System.out.println(i);
        }

    }

    private static int[] getResult(String s) {
        int originLen = s.length();
        int afterZeroRemoveLen = 0;
        int[] answer = new int[2];
        while (true) {
            afterZeroRemoveLen = afterRemoveZeroLength(s);
            answer[0]++;
            answer[1] += (originLen - afterZeroRemoveLen);
            if (afterZeroRemoveLen == 1) {
                break;
            }
            s = getBinaryString(afterZeroRemoveLen);
            originLen = s.length();
        }
        return answer;
    }

    public static int afterRemoveZeroLength(String x) {
        int cnt = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == '1') {
                cnt++;
            }
        }
        return cnt;
    }

    public static String getBinaryString(int len) {
        StringBuilder ret = new StringBuilder();
        int remain = len;
        while (true) {
            if (remain == 1) {
                ret.append("1");
                break;
            } else {
                ret.append(remain % 2 == 0 ? "0" : "1");
                remain = remain / 2;
            }
        }
        return ret.reverse().toString();
    }
}
