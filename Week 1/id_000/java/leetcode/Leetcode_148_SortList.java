package leetcode;

/**
 * 1、两数之和
 */
public class Leetcode_148_SortList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(4);
        listNode.next.next.next = new ListNode(6);

        ListNode res = method1(listNode);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    private static ListNode method1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        // 快慢指针寻找中点
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 切断链表
        prev.next = null;

        ListNode l1 = method1(head);
        ListNode l2 = method1(slow);

        return merge2(l1, l2);
    }

    private static ListNode merge1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = merge1(l1.next, l2);
            return l1;
        } else {
            l2.next = merge1(l2.next, l1);
            return l2;
        }
    }

    private static ListNode merge2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode prev = new ListNode(-1);
        ListNode curr = prev;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        if (l1 != null) {
            curr.next = l1;
        }

        if (l2 != null) {
            curr.next = l2;
        }

        return prev.next;
    }
}
