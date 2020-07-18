package laioffer.LinkedList;

public class PartitionLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(6);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(2);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(2);
        listNode.next.next.next.next.next.next = new ListNode(4);
        listNode = new PartitionLinkedList().partition(listNode, 4);

        System.out.println(listNode);
    }

    /**
     * 给定一个链表和一个value，返回一个新链表。
     * 将比value小的放在node放在左边，大于等于value的node放在右边，并且不影响原先的相对顺序
     * L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3,
     * is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
     *
     * 1、定义两个dummy，一个为比value小的node的链表，另一个为大于等于value的node的链表
     *    (不能确定head的位置，用dummy比较方便)
     * 2、然后遍历原链表，将node放到符合要求的链表中
     * 3、large链表最后的node需要将next指向null，不然会形成环
     *   （small不需要，因为small最后一个node的next会指向large的第一个）
     * 4、将small链表最后一个node和large链表第一个node链接
     * 5、返回small的head
     */

    public ListNode partition(ListNode head, int target) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }

        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);

        ListNode smallCur = smallDummy;
        ListNode largeCur = largeDummy;

        while (head != null) {
            if (head.value < target) {
                smallCur.next = head;
                smallCur = smallCur.next;
            } else {
                largeCur.next = head;
                largeCur = largeCur.next;
            }
            head = head.next;
        }

        largeCur.next = null;
        smallCur.next = largeDummy.next;

        return smallDummy.next;
    }
}
