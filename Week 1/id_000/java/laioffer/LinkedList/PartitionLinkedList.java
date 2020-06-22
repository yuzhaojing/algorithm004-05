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
        listNode = partitionLinkedList(listNode, 4);

        System.out.println(listNode);
    }

    private static ListNode partitionLinkedList(ListNode head, int target) {
        if (head == null || head.next == null) return head;

        ListNode largePrev = new ListNode(0);
        ListNode smallPrev = new ListNode(0);

        ListNode largeCur = largePrev;
        ListNode smallCur = smallPrev;

        int count = 0;

        while (head != null) {
            if (head.value == target) {
                count++;
            } else if (head.value < target) {
                smallCur.next = head;
                smallCur = smallCur.next;
            } else {
                largeCur.next = head;
                largeCur = largeCur.next;
            }

            head = head.next;
        }

        while (count > 0) {
            smallCur.next = new ListNode(target);
            smallCur = smallCur.next;
            count--;
        }

        smallCur.next = largePrev.next;
        largeCur.next = null;

        return smallPrev.next;
    }
}
