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
            return null;
        }

        ListNode dummy = new ListNode(0);
        // 由于在head.key != val的情况下，prev没有给next赋值
        // 所以需要预先将head接到dummy后面
        // 或者将prev = head改写为 prev.next = head; prev = prev.next;
        dummy.next = head;
        ListNode prev = dummy;

        while (head != null) {
            if (head.value == val) {
                prev.next = head.next;
            } else {
                prev = head;
            }
            head = head.next;
        }

        return dummy.next;
    }
}
