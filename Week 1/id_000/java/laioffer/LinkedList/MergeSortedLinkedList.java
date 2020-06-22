package laioffer.LinkedList;

public class MergeSortedLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode = mergeSortedLinkedList2(listNode, new ListNode(5));

        System.out.println(listNode);
    }

    private static ListNode mergeSortedLinkedList1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.value < l2.value) {
            l1.next = mergeSortedLinkedList1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeSortedLinkedList1(l1, l2.next);
            return l2;
        }
    }

    private static ListNode mergeSortedLinkedList2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode prev = new ListNode(0);
        ListNode cur = prev;

        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }

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
