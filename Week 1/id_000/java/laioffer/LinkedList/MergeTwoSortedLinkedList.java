package laioffer.LinkedList;

public class MergeTwoSortedLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode = new MergeTwoSortedLinkedList().merge(listNode, new ListNode(5));

        System.out.println(listNode);
    }

    public ListNode merge(ListNode one, ListNode two) {
        // Write your solution here
        if (one == null) {
            return two;
        }

        if (two == null) {
            return one;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (one != null && two != null) {
            if (one.value < two.value) {
                cur.next = one;
                one = one.next;
            } else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }

        if (one != null) {
            cur.next = one;
        }

        if (two != null) {
            cur.next = two;
        }

        return dummy.next;
    }
}
