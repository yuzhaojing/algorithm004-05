package laioffer.CrossTrainingII;

import laioffer.practice.design_queue_stack_deque.ListNode;

public class ReverseLinkedList {

    public static void main(String[] args) {

    }

    /**
     * input: head ListNode
     * output: ListNode (反转链表后的头节点)
     * 假设：head != null
     * 如果不符合假设，那么链表为空，则返回null
     *
     * high level: 可以使用recursion或者iterator进行解答
     * mid level:
     *  recursion
     *    1、向下一层要反转后的head
     *    2、反转本层
     *    3、返回head
     *  iterator
     *    1、保存三个指针，prev、cur、next
     *    2、每次迭代 cur.next = prev 然后三个指针走一步
     *
     * time = O(n)
     * space = O(n) (recursion)
     * space = O(1) (iterator)
     */
    public ListNode reverse(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }
}
