package laioffer.LinkedList;

public class InsertAtIndex {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode = new InsertAtIndex().insert(listNode, 1, 5);

        System.out.println(listNode);
    }

    public ListNode insert(ListNode head, int index, int value) {
        // Write your solution here
        ListNode target = new ListNode(value);
        if (head == null || index == 0) {
            target.next = head;
            return target;
        }

        ListNode prev = null;
        ListNode cur = head;

        while (index > 0) {
            // 当cur.next为null的时候，insert依然有效
            // 所以条件不需要加入cur.next != null
            if (cur != null) {
                prev = cur;
                cur = cur.next;
                index--;
            } else {
                return head;
            }
        }

        prev.next = target;
        target.next = cur;
        return head;
    }
}
