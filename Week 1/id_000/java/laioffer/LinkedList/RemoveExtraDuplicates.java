package laioffer.LinkedList;

public class RemoveExtraDuplicates {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next.next = new ListNode(5);
        listNode = new RemoveExtraDuplicates().removeDup(listNode);

        System.out.println(listNode);
    }

    public ListNode removeDup(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.value == cur.next.value) {
                ListNode temp = cur;
                while (temp != null && temp.next != null && temp.value == temp.next.value) {
                    temp = temp.next;
                }
                cur = temp.next;
                prev.next = cur;
            } else {
                prev.next = cur;
                prev = prev.next;
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}
