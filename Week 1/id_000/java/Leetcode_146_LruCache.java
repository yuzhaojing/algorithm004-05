import java.util.HashMap;

public class Leetcode_146_LruCache {

    public static void main(String[] args) {
        /*Leetcode_146_LruCache cache = new Leetcode_146_LruCache(2 *//* 缓存容量 *//*);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4*/
    }

    private HashMap<Integer, DoubleNode> map;
    private DoubleNodeList cache;
    private int capacity;

    public Leetcode_146_LruCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.cache = new DoubleNodeList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        int val = map.get(key).val;
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        DoubleNode node = new DoubleNode(key, val);

        if (map.containsKey(key)) {
            cache.remove(map.get(key));
        } else {
            if (cache.getSize() == capacity) {
                DoubleNode lastNode = cache.removeLast();
                map.remove(lastNode.key);
            }
        }

        cache.addFirst(node);
        map.put(key, node);
    }
}

// 链表节点
class DoubleNode {
    public int key;
    public int val;

    public DoubleNode prev;
    public DoubleNode next;

    public DoubleNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

// 双向链表
class DoubleNodeList {
    private DoubleNode head;
    private DoubleNode tail;

    private int size;

    public DoubleNodeList() {
        head = new DoubleNode(0, 0);
        tail = new DoubleNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(DoubleNode node) {
        if (node == null) return;

        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;

        size++;
    }

    public void remove(DoubleNode node) {
        if (node == null) return;

        node.prev.next = node.next;
        node.next.prev = node.prev;

        size--;
    }

    public DoubleNode removeLast() {
        if (tail.prev == head) return null;

        DoubleNode lastNode = tail.prev;
        remove(lastNode);

        return lastNode;
    }

    public int getSize() {
        return this.size;
    }
}
