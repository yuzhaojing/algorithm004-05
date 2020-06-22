package laioffer.LinkedList;

public class CovertLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        listNode.next.next.next.next.next.next = new ListNode(7);
        listNode = covertLinkedList(listNode);

        System.out.println(listNode);
    }

    /**
     * N1 -> N2 -> N3 -> N4 -> ... -> N99 -> N100
     *              |
     *              |
     * N1 -> N100 -> N2 -> N99 -> ...
     *
     * 1、找到链表中点
     * 2、反转后半部分链表
     * 3、将两个链表合并
     */
    private static ListNode covertLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;

        while (fast.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        slow = reverseLinkedList(slow);

        return mergeLinkedList(head, slow);
    }

    private static ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    private static ListNode mergeLinkedList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode prev = new ListNode(0);
        ListNode cur = prev;

        int count = 0;

        while (l1 != null && l2 != null) {
            if (count % 2 == 0) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }

            count++;
            cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        }

        if (l2 != null) {
            cur.next = l2;
        }

        return prev.next;
    }
}
