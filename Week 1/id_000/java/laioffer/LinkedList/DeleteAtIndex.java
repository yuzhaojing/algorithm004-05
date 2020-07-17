package laioffer.LinkedList;

public class DeleteAtIndex {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode = new DeleteAtIndex().deleteNode(listNode, 1);

        System.out.println(listNode);
    }

    public ListNode deleteNode(ListNode head, int index) {
        // Write your solution here
        if (index < 0 || head == null) {
            return head;
        }

        if (index == 0) {
            return head.next;
        }

        ListNode prev = null;
        ListNode cur = head;

        while (index > 0) {
            // 由于定义上要删除的是cur，所以cur.next也不能为null
            // 否则进入cur = cur.next之后，index在本轮循环减至0
            // 在下面删除的时候就会删除链表尾部的null，这是无意义的
            if (cur != null && cur.next != null) {
                prev = cur;
                cur = cur.next;
                index--;
            } else {
                // 当index未归0，并且cur为null的情况下，后续循环无意义，不会删除元素
                return head;
            }
        }

        prev.next = cur.next;
        cur.next = null;
        return head;
    }
}
