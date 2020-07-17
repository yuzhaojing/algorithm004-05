package laioffer.LinkedList;

public class MergeSortLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode = new MergeSortLinkedList().mergeSort(listNode);

        System.out.println(listNode);
    }

    public ListNode mergeSort(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = middle(head);
        ListNode one = head;
        ListNode two = mid.next;
        mid.next = null;

        ListNode left = mergeSort(one);
        ListNode right = mergeSort(two);

        return merge(left, right);
    }

    private ListNode merge(ListNode one, ListNode two) {
        if (one == null) {
            return two;
        }

        if (two == null) {
            return one;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (one != null && two != null) {
            if (one.value < two.value) {
                cur.next = one;
                one = one.next;
            } else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }

        if (one != null) {
            cur.next = one;
        }

        if (two != null) {
            cur.next = two;
        }

        return dummy.next;
    }

    private ListNode middle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
