package laioffer.LinkedList;

public class CheckPalindrome {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(3);
        Boolean b = new CheckPalindrome().isPalindrome(listNode);

        System.out.println(b);
    }

    public boolean isPalindrome(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return true;
        }

        ListNode mid = middle(head);
        ListNode one = head;
        ListNode two = mid.next;
        mid.next = null;

        two = reverse(two);

        while (one != null && two != null) {
            if (one.value == two.value) {
                one = one.next;
                two = two.next;
            } else {
                return false;
            }
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
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
