/**
 * 21.合并两个有序链表
 */
public class Leetcode_206_ReverseLinkedList {

    public static void main(String[] args) {

    }

    private static ListNode method1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = method1(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

}
