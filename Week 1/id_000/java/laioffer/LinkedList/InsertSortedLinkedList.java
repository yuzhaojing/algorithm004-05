package laioffer.LinkedList;

public class InsertSortedLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode = new InsertSortedLinkedList().insert(listNode, 5);

        System.out.println(listNode);
    }

    public ListNode insert(ListNode head, int value) {
        // Write your solution here
        ListNode target = new ListNode(value);
        if (head == null || head.value >= value) {
            target.next = head;
            return target;
        }

        ListNode cur = head;

        while (cur != null) {
            if (cur.value < value && (cur.next == null || cur.next.value >= value)) {
                target.next = cur.next;
                cur.next = target;
                return head;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
