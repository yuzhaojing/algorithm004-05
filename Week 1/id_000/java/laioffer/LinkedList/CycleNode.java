package laioffer.LinkedList;

public class CycleNode {

    public static void main(String[] args) {
        ListNode target = new ListNode(2);
        ListNode listNode = new ListNode(1);
        listNode.next = target;
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = target;
        listNode = new CycleNode().cycleNode(listNode);

        System.out.println(listNode);
    }

    /**
     * 如何寻找环的起点
     * 定义slow和fast两个快慢指针，slow一次走一步，fast一次走两步
     * 则可以列出一下方程:
     * f代表fast指针走的距离，s代表slow指针走的距离。a代表head到起点的距离，b代表环的长度
     * f = 2s
     * f = s + nb (fast指针想要追上slow指针，必须要比slow指针多走n个环)
     * 可以求得 f = 2nb; s = nb;
     * 然后从head到环的起点距离为 a + nb
     * 目前slow已经走了nb步了，再走a步就是环的起点
     * fast也已经走了2nb步了，同样再走a步就是环的起点
     * 此时将fast指针重置回head，并且每次走一步，fast指针走了a步的时候，slow指针走了a + nb步
     * 此时f = a, s = a + nb,两指针重合并指向环形起点
     */
    public ListNode cycleNode(ListNode head) {
        // write your solution here
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }
}
