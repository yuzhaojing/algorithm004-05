package laioffer.LinkedList;

public class InsertSortedLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode = insertSortedLinkedList(listNode, 5);

        System.out.println(listNode);
    }

    private static ListNode insertSortedLinkedList(ListNode head, int value) {
        ListNode node = new ListNode(value);
        ListNode cur = head;

        while (cur != null) {
            if (cur.value > value) {
                node.next = cur;
                return node;
            } else {
                ListNode next = cur.next;
                if (cur.next == null || cur.next.value >= value) {
                    cur.next = node;
                    node.next = next;
                    return head;
                } else {
                    cur = next;
                }
            }
        }

        return node;
    }
}
