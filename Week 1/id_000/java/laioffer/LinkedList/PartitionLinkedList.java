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
