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
