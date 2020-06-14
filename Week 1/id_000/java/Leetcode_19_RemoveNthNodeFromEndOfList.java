/**
 * 1、两数之和
 */
public class Leetcode_19_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(4);
        listNode.next.next.next = new ListNode(6);

        ListNode res = method1(listNode, 2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    private static ListNode method1(ListNode head, int n) {
        if (head == null) return null;

        ListNode prev = new ListNode(0);
        prev.next = head;

        ListNode fast = prev;
        ListNode slow = prev;

        for (int i = 0 ; i < n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return prev.next;
    }
}
