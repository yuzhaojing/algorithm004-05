package laioffer.LinkedList;

public class RemoveElements {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode = new RemoveElements().removeElements(listNode, 3);

        System.out.println(listNode);
    }

    public ListNode removeElements(ListNode head, int val) {
        // Write your solution here
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.value != val) {
                prev.next = cur;
                prev = prev.next;
            } else{
                prev.next = cur.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }
}
