package LeetCode.linkedList;

public class MiddleOfLinkedList {
    // Lhttps://leetcode.com/problems/middle-of-the-linked-list/description/

    public ListNode middleNode(ListNode head) {
        // Walker & Runner 기술을 사용한다.
        // Walker: 1번에 1칸씩 이동
        // Runner: 1번에 2칸씩 이동
        // Runner가 끝나면 walker는 중간에 와있음
        // 구현을 위한 브레인 스토밍
        ListNode walker = head;
        ListNode runner = head;
        while (runner != null) {
            runner = runner.next;
            if (runner != null) {
                runner = runner.next;
                walker = walker.next;
            }
        }
        return walker;
    }
}
