package laioffer.CrossTrainingIV;

import java.util.HashMap;
import java.util.Map;

/**
 * clarify:
 *  1、需要哪些特性？能缓存数据并且进行缓存淘汰，将最长时间不用的淘汰
 *  2、需要哪些API？要求达到怎样的时间复杂度
 *     get(K key)           O(1)
 *     put(K key, V value)  O(1)
 *     隐含remove O(1)的条件，因为put要求为O(1)
 *     隐含需要记录元素访问时间的要求
 *
 *  可以做到get(K key)为O(1)的数据结构有: HashMap
 *  可以做到put(K key, V value)或add()为O(1)的数据结构有:
 *      HashMap、LinkedList、DoubleLinkedList、ArrayList
 *  可以做到remove()为O(1)的数据结构有:
 *      HashMap，LinkedList和DoubleLinkedList需要O(n)的时间search，ArrayList只要在尾部能做到
 *  可以隐含时间顺序的数据结构有: LinkedList、DoubleLinkedList、ArrayList (可以通过前后顺序表示访问时间)
 *
 *  结论: HashMap为必须，否则无法O(1)的时间search到指定元素。
 *       与ArrayList合用，删除尾部元素可以做到O(1)，但是在头部新增元素为O(n)
 *       与LinkedList合用，可以在头部新增元素为O(1)，但是尾部删除元素为O(n)
 *       与DoubleLinkedList合用，可以做到头尾都为O(1)
 *
 * high level: 使用HashMap + DoubleLinkedList实现
 * detail level:
 *  1、创建一个HashMap用于索引，DoubleLinkedList用于存储具体数据
 *  2、添加一个元素的时候，新建一个Node，将其插入在链表头部，然后存储该Node的ref在HashMap内
 *     如果发现超过长度限制的时间，将tail指针像前移动一步，然后断掉最后一个Node的ref
 *  3、查找一个元素，直接在HashMap中通过key拿到对应的ref，将这个Node插入到链表头部，然后返回该Node
 */
public class LRUCache<K, V> {

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(1);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }

    private Map<K, DoubleListNode<K, V>> cache;
    private DoubleListNode DoubleListNode;

    private DoubleListNode<K, V> head;
    private DoubleListNode<K, V> tail;

    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
    }

    public V get(K key) {
        LRUCache.DoubleListNode<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }

        remove(node);
        addFirst(node);
        return node.value;
    }

    public void put(K key, V value) {
        LRUCache.DoubleListNode<K, V> node = null;

        if (cache.containsKey(key)) {
            // 如果原先有这个node，那么需要更新value，并且从原来的位置删除
            node = cache.get(key);
            node.value = value;
            remove(node);
        } else {
            // 如果没有这个node，需要新建一个node
            // 如果大小已经到达capacity，则删除tail
            if (cache.size() == capacity) {
                remove(tail);
            }
            node = new DoubleListNode<>(key, value);
        }

        // 将node插入head
        addFirst(node);
    }

    private void addFirst(LRUCache.DoubleListNode<K, V> node) {
        cache.put(node.key, node);

        if (head == null) {
            // 此时链表为空
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    private void remove(LRUCache.DoubleListNode<K, V> node) {
        cache.remove(node.key);

        if (node.prev != null) {
            node.prev.next = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }

        if (node == tail) {
            tail = node.prev;
        }

        if (node == head) {
            head = node.next;
        }

        node.prev = node.next = null;
    }

    static class DoubleListNode<K, V> {
        K key;
        V value;
        DoubleListNode<K, V> prev;
        DoubleListNode<K, V> next;

        public DoubleListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
