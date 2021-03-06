package laioffer.LinkedList;

public class MiddleNode {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode = middleNode(listNode);

        System.out.println(listNode);
    }

    /**
     * 快慢指针
     * 1 -> 2 -> 3 -> 4
     * 当链表长度为偶数时，中点取偏左的，可选择的余地较多
     */
    private static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        // 为了保证fast指针每次都能走两个节点，从而保证了slow在中点或者中点偏左侧
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
