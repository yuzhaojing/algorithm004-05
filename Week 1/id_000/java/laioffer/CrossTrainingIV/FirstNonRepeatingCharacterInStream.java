package laioffer.CrossTrainingIV;

import java.util.HashMap;
import java.util.Map;

/**
 * clarify:
 *  1、需要有哪些功能？在O(1)的时间内找到到目前为止第一个未重复元素
 *  2、需要实现哪些API？
 *     read(char ch)
 *     firstNonRepeating()
 *
 *  由于需要在O(1)的时间内进行查找，所以HashMap基本是必备的。查重这个功能也可以在HashMap里面做。
 *  需要找第一个，那么数据结构内需要有顺序，满足的有: ArrayList、LinkedList、DoubleLinkedList
 *  想找第一个未重复的元素，最简单的办法就是把这个元素放在最前面，每次发现和这个有重复就删除。
 *  ArrayList的随机删除时间未O(n), LinkedList、DoubleLinkedList都为O(1)
 *  每次新增元素的时间，需要添加在尾部，所以直接选用DoubleLinkedList
 *
 * high level: 使用HashMap + DoubleListNode实现
 * detail level:
 *  1、建立一个HashMap，用于存储char -> DoubleListNode的映射
 *  2、建立一个DoubleListNode，始终保持从头到尾都是按顺序为重复的元素
 *  3、读取一个char，通过HashMap查找对应的node。如果没有这个key，表示没出现过，直接加到链表尾部。
 *     如果存在node，那么将这个node从链表中删除，并将HashMap中对应的value置为null
 *     如果value为null，那么表示这个node之前出现过重复，以经被删除了，不做处理
 *  4、每次返回链表的head
 */
public class FirstNonRepeatingCharacterInStream {

    private Map<Character, DoubleListNode> cache;
    private DoubleListNode head;
    private DoubleListNode tail;

    public FirstNonRepeatingCharacterInStream() {
        // Initialize the class.
        this.cache = new HashMap<>();
    }

    public void read(char ch) {
        // Implement this method here.
        DoubleListNode node = null;
        if (!cache.containsKey(ch)) {
            node = new DoubleListNode(ch);
            addLast(node);
        } else {
            node = cache.get(ch);
            if (node != null) {
                remove(node);
            }
        }
    }

    public Character firstNonRepeating() {
        // Implement this method here.
        return head == null ? null : head.value;
    }

    private void addLast(DoubleListNode node) {
        cache.put(node.value, node);

        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    private void remove(DoubleListNode node) {
        cache.put(node.value, null);

        if (node.prev != null) {
            node.prev.next = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }

        if (node == head) {
            head = node.next;
        }

        if (node == tail) {
            tail = tail.prev;
        }

        node.prev = node.next = null;
    }

    static class DoubleListNode {
        char value;

        DoubleListNode prev;
        DoubleListNode next;

        public DoubleListNode(char ch) {
            this.value = ch;
        }
    }
}
