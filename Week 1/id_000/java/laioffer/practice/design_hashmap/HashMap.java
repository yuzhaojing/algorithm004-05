package laioffer.practice.design_hashmap;

/**
 * 确定需要实现的API
 * 1. put(K key, V value)
 * 2. get(Object key)
 * 3. resize()
 * 4. size()
 * 确定实现的数据结构
 * 确定constructor
 */
public class HashMap<K, V> {
    // 构建静态内部类Entry，用于存储数据
    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] array;
    private int size;
    private float loadFactor;

    private static final int DEFAULT_CAPACITY = 2 << 4;
    private static final float DEFAULT_LOADFACTOR = 0.75f;
    private static final float DEFAULT_SCALE_FACTOR = 2;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOADFACTOR);
    }

    public HashMap(int capacity, float loadFactor) {
        this.array = (Node<K, V>[]) new Node[capacity];
        this.loadFactor = loadFactor;
    }

    public V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (keyEquals(node.key, key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            node = node.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = array[index];
        array[index] = newNode;
        size++;

        if ((float) size / array.length > loadFactor) {
            resize();
        }

        return null;
    }

    private void resize() {
        Node<K, V>[] oldArray = array;
        array = (Node<K, V>[]) new Node[array.length * 2];
        for (Node<K, V> node : oldArray) {
            while (node != null) {
                int index = getIndex(node.key);
                Node<K, V> next = node.next;
                node.next = array[index];
                array[index] = node;
                node = next;
            }
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (keyEquals(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    private boolean keyEquals(K nodeKey, K getKey) {
        return nodeKey == getKey || (nodeKey != null && nodeKey.equals(getKey));
    }

    private int getIndex(K key) {
        // 因为固定了数组长度为2^n，所有array.length - 1后面全部都是1
        // 所以结果就是hashcode在数组长度内的值，相当于取模，效率更高
        return hash(key) & (array.length - 1);
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int h = key.hashCode();
        // 高位扰动
        return h ^ (h >> 16);
    }
}
