package laioffer.LinkedList;

public class DeleteAtIndex {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode = new DeleteAtIndex().deleteNodeWithDummy(listNode, 1);

        System.out.println(listNode);
    }

    // 不使用dummy，提前判断
    public ListNode deleteNode(ListNode head, int index) {
        // Write your solution here
        if (head == null || index < 0) {
            return head;
        }

        if (index == 0) {
            return head.next;
        }

        ListNode prev = null;
        ListNode cur = head;

        while (index > 0) {
            // 此时index > 0,如果cur = null或者cur.next = null,
            // 那么继续下一轮循环无意义，因为null无法被删除
            if (cur.next == null) {
                return head;
            }

            prev = cur;
            cur = cur.next;
            index--;
        }

        // 此时index = 0 and cur != null
        prev.next = cur.next;
        return head;
    }

    public ListNode deleteNodeWithDummy(ListNode head, int index) {
        // Write your solution here
        if (head == null || index < 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;

        while (index > 0) {
            // 此时index > 0,如果cur = null或者cur.next = null,
            // 那么继续下一轮循环无意义，因为null无法被删除
            if (cur.next == null) {
                return head;
            }

            prev = cur;
            cur = cur.next;
            index--;
        }

        // 此时index = 0 and cur != null
        prev.next = cur.next;
        return dummy.next;
    }
}
