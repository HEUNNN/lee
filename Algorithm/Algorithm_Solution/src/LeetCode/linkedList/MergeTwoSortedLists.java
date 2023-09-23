package LeetCode.linkedList;

public class MergeTwoSortedLists {
    // https://leetcode.com/problems/merge-two-sorted-lists/submissions/
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(5, null)));
        ListNode list2 = new ListNode(4, new ListNode(6, new ListNode(7, null)));
        ListNode listNode = new MergeTwoSortedLists().mergeTwoLists(list1, list2);
        while (listNode.next != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
        System.out.println(listNode.val);
    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode result = null; // 결과
        ListNode current = null; // 현재 포인터

        while (list1 != null || list2 != null) {
            if (list2 == null || (list1 != null && list1.val < list2.val)) {
                // pick from list1
                if (result == null) {
                    result = list1; // result와 current에 list1 주소가 할당된다. 따라서 current의 next 등을 변경 시키면 result의 next도 같아진다.
                } else {
                    current.next = list1; // result의 next를 하면 안되는 이유 ?
                }
                current = list1;
                list1 = list1.next;

            } else {
                // pick from list2
                if (result == null) {
                    result = list2;
                } else {
                    current.next = list2;
                }
                current = list2;
                list2 = list2.next;
            }
        }

        return result;
    }
}
