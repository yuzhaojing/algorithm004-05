package leetcode;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_141_LinkedListCycle {

    public static void main(String[] args) {

    }

    private static boolean method1(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

}
