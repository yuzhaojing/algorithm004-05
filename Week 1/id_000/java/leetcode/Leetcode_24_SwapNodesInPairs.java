package leetcode;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_24_SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(3);
        a.next.next = new ListNode(4);
        a.next.next.next = new ListNode(5);
        a.next.next.next.next = new ListNode(6);

        ListNode listNode = method1(a);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    private static ListNode method1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode firstNode = head;
        ListNode secondNode = head.next;

        firstNode.next = method1(secondNode.next);
        secondNode.next = firstNode;

        return secondNode;
    }

}
