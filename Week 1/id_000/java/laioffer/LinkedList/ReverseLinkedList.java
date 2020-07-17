package laioffer.LinkedList;

class ListNode {
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
    }
}

public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode = reverse1(listNode);

        System.out.println(listNode);
    }

    /**
     * 迭代法
     *
     * 1 -> 2 -> 3 -> null
     *
     * 需要有三个指针，分别为prev，cur，next
     * next指针用于记录cur指向prev之后，链表的头节点
     */
    private static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    /**
     * 递归
     */
    private static ListNode reverse1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse1(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
