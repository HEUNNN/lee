package LeetCode.bit;

public class SingleNumber {
    // https://leetcode.com/problems/single-number/description/
    public static void main(String[] args) {
        int[] nums = new int[] {4, 1, 2, 1, 2};
        int result = new SingleNumber().singleNumber(nums);
        System.out.println(result);
    }
    public int singleNumber(int[] nums) {
        // XOR 이 0이면 두 숫자는 같은 숫자이다.
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
            // 같은 num이 나오게 되면 num 에 해당하는 숫자는 없어진다.
            // 2, 1를 xor하고, 다시 1만 xor 하면 2만 남는다.
            // 2: 0 0 1 0, 1: 0 0 0 1 XOR → 0 0 1 1
            // 1 XOR → 0 0 1 0, 2만 남았다.
        }
        return ret;
    }
}
