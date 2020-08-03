package laioffer.practice.design_queue_stack_deque;

/**
 * 使用链表ListNode实现一个Queue
 * 可以考虑从head进tail出、tail进head出
 * 从tail出存在一个问题，即当tail需要删除元素时，tail指针无法返回上一个元素
 * 所以决定从head出元素，tail端进元素
 */
public class ListQueue {
    private ListNode head;
    private ListNode tail;
    private int size;

    // 当tail == null的时候，ListQueue中没有元素
    // 所以head和tail应该同时指向新的ListNode
    // 当tail != null的时候，只跟新tail指针
    public void offer(int value) {
        ListNode target = new ListNode(value);
        if (tail == null) {
            tail = target;
            head = target;
        } else {
            tail.next = target;
            tail = tail.next;
        }
    }

    // 由于当head为null的时候需要返回null，所有返回值类型为Integer
    // 当head删除一个node的时候，应该检查head是否为null
    // 如果head == null，则代表ListQueue中没有元素，tail也需要指向null
    public Integer poll() {
        if (head == null) {
            return null;
        }

        ListNode prev = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        prev.next = null;
        return prev.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
