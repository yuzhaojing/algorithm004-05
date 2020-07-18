package laioffer.LinkedList;

public class RotateLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode = new RotateLinkedList().rotateKplace(listNode, 3);

        System.out.println(listNode);
    }

    public ListNode rotateKplace(ListNode head, int n) {
        // Write your solution here
        if (head == null || head.next == null || n <= 0) {
            return head;
        }

        // 初始长度为1，因为虚幻的条件为oldTail.next != null
        // 也就是说最后一个node不会被统计
        int len = 1;
        ListNode oldTail = head;
        while (oldTail.next != null) {
            len++;
            oldTail = oldTail.next;
        }

        // 将tail指向head，这样在下一次循环中只需要管head就可以了
        oldTail.next = head;

        ListNode newTail = head;
        // len - n % len的结果为链表从第一个node到需要翻转的第一个元素之前的node之和
        // 例如: 1->2->3->4->null n = 2 len = 4
        // 需要翻转的第一个元素为3，len - n % len = 2，在3之前的元素有2个
        // 由于我们需要找的是新链表的tail， 并且newTail的起始位置就在第一个node上
        // 所以实际上我们只需要让newTail移动1个元素就可以了
        // 所以需要移动的节点数为len - n % len - 1
        int index = len - n % len - 1;

        for (int i = 0; i < index; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }
}
