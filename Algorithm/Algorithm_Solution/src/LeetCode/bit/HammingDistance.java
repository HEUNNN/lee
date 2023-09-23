package LeetCode.bit;

public class HammingDistance {
    // https://leetcode.com/problems/hamming-distance/

    public static void main(String[] args) {
        int x = 1; // 0 0 0 1
        int y = 4; // 0 1 0 1
        int i = new HammingDistance().hammingDistance(x, y);
        System.out.println(i);
    }

    public int hammingDistance(int x, int y) {
        // x ^ y (x xor y)를 해서 1이 몇번 등장하는지를 count 한다.
        int xor = x ^ y;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (xor >> i) & 1; // 1 → 00000000 00000000 00000000 00000001
        }
        return count;
    }
}
