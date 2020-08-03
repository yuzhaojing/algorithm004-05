package laioffer.practice.design_queue_stack_deque;

/**
 * 使用链表ListNode实现一个Deque
 * 此时我们需要一个双向链表，如果还是使用单向链表的话，tail端的删除代价太高
 *
 */
public class ListDeque {

    public static void main(String[] args) {
        ListDeque deque = new ListDeque();
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerLast(5);
        deque.offerLast(6);

        System.out.println(deque.pollLast());
        System.out.println(deque.pollLast());
        System.out.println(deque.pollLast());
        System.out.println(deque.pollLast());
        System.out.println(deque.pollLast());
        System.out.println(deque.pollLast());
    }

    private DoubleListNode head;
    private DoubleListNode tail;
    private int size;

    // 在添加元素的时候，一定要注意head == null的情况
    // 因为是双向链表，需要更新head.prev
    public void offerFirst(int value) {
        DoubleListNode target = new DoubleListNode(value);
        if (head == null) {
            head = target;
            tail = target;
        } else {
            target.next = head;
            head.prev = target;
            head = target;
        }
        size++;
    }

    // 在添加元素的时候，一定要注意tail == null的情况
    // 因为是双向链表，需要更新tail.next
    public void offerLast(int value) {
        DoubleListNode target = new DoubleListNode(value);
        if (tail == null) {
            head = target;
            tail = target;
        } else {
            tail.next = target;
            target.prev = tail;
            tail = tail.next;
        }
        size++;
    }

    public Integer pollFirst() {
        if (head == null) {
            return null;
        }

        DoubleListNode prev = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        prev.next = null;
        size--;
        return prev.value;
    }

    // 需要注意当Double中只剩一个元素的时候，tail和head都需要指向null
    // 并且tail.next不需要更新，否则会导致NPE
    public Integer pollLast() {
        if (tail == null) {
            return null;
        }

        DoubleListNode next = tail;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return next.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
