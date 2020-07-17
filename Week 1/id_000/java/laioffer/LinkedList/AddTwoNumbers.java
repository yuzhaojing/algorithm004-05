package laioffer.LinkedList;

public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode = new AddTwoNumbers().addTwoNumbers(listNode, new ListNode(5));

        System.out.println(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Write your solution here
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        int value = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null || l2 != null || value != 0) {
            if (l1 != null) {
                value += l1.value;
                l1 = l1.next;
            }

            if (l2 != null) {
                value += l2.value;
                l2 = l2.next;
            }

            cur.next = new ListNode(value % 10);
            cur = cur.next;
            value = value / 10;
        }

        return dummy.next;
    }
}
