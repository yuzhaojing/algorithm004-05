package laioffer.LinkedList;

public class ReverseLinkedListInPairs {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        listNode = new ReverseLinkedListInPairs().reverseInPairs(listNode);

        System.out.println(listNode);
    }

    public ListNode reverseInPairs(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = reverseInPairs(head.next.next);
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = next;
        return newHead;
    }
}
