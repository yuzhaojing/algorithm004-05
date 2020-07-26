package laioffer.design_queue_stack_deque;

/**
 * 使用链表ListNode实现一个Stack
 * 可以考虑从head进出以及从tail进出
 * 从tail进出存在一个问题，即当tail需要删除元素时，tail指针无法返回上一个元素
 * 所以决定从head端进出元素
 */
public class ListStack {
    private ListNode head;
    private int size;

    // 将target的next指向head，并将head指向target
    public void push(int value) {
        ListNode target = new ListNode(value);
        target.next = head;
        head = target;
        size++;
    }

    // 由于当head为null的时候需要返回null，所有返回值类型为Integer
    public Integer pop() {
        if (head == null) {
            return null;
        }

        ListNode prev = head;
        head = head.next;
        // good practice
        prev.next = null;
        size--;
        return prev.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
