package laioffer.LinkedList;

public class ReOrderLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode = new ReOrderLinkedList().reorder(listNode);

        System.out.println(listNode);
    }

    /**
     * 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
     *
     * 1、找出链表中点，将链表断开成为前后两个链表
     *    1 -> 2 -> null
     *    3 -> 4 -> null
     * 2、将后半部分链表反转
     *    1 -> 2 -> null
     *    4 -> 3 -> null
     * 3、合并两个链表，将两个链表按照1 -> 4 -> 2 -> 3的顺序合并
     *    由于链表的head可以确定为原始链表的head，所以不需要dummy
     */

    public ListNode reorder(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = middle(head);

        ListNode one = head;
        ListNode two = mid.next;
        mid.next = null;

        ListNode reverse = reverse(two);

        return merge(one, reverse);
    }

    private ListNode middle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode merge(ListNode one, ListNode two) {
        if (one == null) {
            return two;
        }

        if (two == null) {
            return one;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (one != null && two != null) {
            cur.next = one;
            one = one.next;
            cur.next.next = two;
            two = two.next;
            cur = cur.next.next;
        }

        if (one != null) {
            cur.next = one;
        }

        if (two != null) {
            cur.next = two;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
