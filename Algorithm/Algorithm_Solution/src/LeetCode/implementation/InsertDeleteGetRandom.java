package LeetCode.implementation;

import java.util.*;

class InsertDeleteGetRandom {
    // https://leetcode.com/problems/insert-delete-getrandom-o1/
    Map<Integer, Integer> map; // key: set element, value: index
    List<Integer> list; // getRandom 을 할 때 o(1)이 되게 하기 위해 ArrayList 를 사용한다.
    int size; // list에 들어있는 유효한 element 개수
    Random r = new Random();

    public InsertDeleteGetRandom() { // 생성자
        map = new HashMap<>();
        list = new ArrayList<>();
        size = 0; // index
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, size++); // list에 저장되는 인덱스를 알 수 없어서, value값에 해당하는 index를 size를 증가시키면서 세알린다.
        list.add(val);
        return true;
    }

    /*
    1 2 3 4 , size: 4
    remove 2
    1 4 3 2 , 자리 변경
    1 4 3, size: 3 , 마지막 원소 제거하면 최종 결과
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        // map에서 val에 해당하는 값 삭제
        int idx = map.get(val); // 삭제해야할 valid의 index(size 값을 갖고 넣어준 값)
        map.remove(val);

        // list에서 val 삭제
        if (idx != size - 1) {
            int swap = list.get(size - 1);
            list.set(idx, swap);
            map.put(swap, idx);
        }
        list.remove(--size); // size - 1 != --size
        return true;
    }

    public int getRandom() {
        int randNum = new Random().nextInt(size);
        return list.get(randNum);
    }
}
