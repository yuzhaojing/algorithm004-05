package laioffer.LinkedList;

public class RemoveNthNodeFromEnd {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next.next = new ListNode(5);
        listNode = new RemoveNthNodeFromEnd().removeNthFromEnd(listNode, 2);

        System.out.println(listNode);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Write your solution here
        if (head == null || n < 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n + 1; i++) {
            if (fast == null) {
                return head;
            }
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}
