package Programmers.hash;

import java.util.HashSet;

public class 폰켓몬 {
    public int getPhonekenmon(int nums[]) {
        int MaxForGet = nums.length / 2;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        return Math.min(MaxForGet, hashSet.size());
    }
}
