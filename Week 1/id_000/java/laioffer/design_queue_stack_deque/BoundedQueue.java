package laioffer.design_queue_stack_deque;

/**
 * 使用环形数组实现一个queue
 * 当实现queue的时候，由于从head取出元素，head会向后移动
 * 如果使用普通数组，会在head之前还有大量空间的时候进行扩容
 * 使用环形数组可以节省空间，只有确实存满了元素才会扩容
 */
public class BoundedQueue {

    public static void main(String[] args) {
        BoundedQueue queue = new BoundedQueue(1);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    private int[] array;
    private int head;
    private int tail;

    // 由于初始化的时候head和tail相差一个角标，所以最少需要长度为2的数组
    // head和tail对应的数组角标不装元素
    public BoundedQueue(int capacity) {
        if (capacity < 2) {
            capacity = 2;
        }
        array = new int[capacity];
        head = 0;
        tail = 1;
    }

    // 因为初始化时tail = head + 1，所以只有当数组满了才会使head == tail
    // 每次添加元素的时候直接在tail对应的角标添加元素，然后tail后移一位
    // 如果tail已经是数组最后一个角标，那么移动到数组头，即角标0
    public void offer(int value) {
        if (isFull()) {
            resize(array.length + 1);
        }

        array[tail] = value;
        tail = tail + 1 == array.length ? 0 : tail + 1;
    }

    // 每次扩容*1.5倍，如果扩容后出现int溢出等问题，设置为最小扩容阈值
    // 数据拷贝有两种情况
    // 1.tail > head，此时数组还未旋转，只需要拷贝head ~ tail - 1的元素即可
    // 2.tail <= head，此时数组已经旋转了，需要分两部分拷贝
    //   1、拷贝head ～ array.length - 1的数据
    //   2、拷贝0 ～ tail - 1的数据
    //   3、将2接在1的后面
    // 最后需要重置head = 0，tail = 数据拷贝次数
    private void resize(int minCapacity) {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        int[] newArray = new int[newCapacity];
        int j = 0;
        if (tail > head) {
            for (int i = head; i < tail; i++) {
                newArray[j] = array[i];
                j++;
            }
        } else {
            for (int i = head; i < array.length; i++) {
                newArray[j] = array[i];
                j++;
            }
            for (int i = 0; i < tail; i++) {
                newArray[j] = array[i];
                j++;
            }
        }

        tail = j;
        head = 0;
        array = newArray;
    }

    // head + 1又可能越界，所以模以数组长度可以得到正确的下一个角标
    // 如果head的下一个角标是tail，则说明数组内没有元素
    // 最后更新head的值
    public Integer poll() {
        if (isEmpty()) {
            return null;
        }

        head = head + 1 == array.length ? 0 : head + 1;
        return array[head];
    }

    public boolean isFull() {
        return head == tail;
    }

    public boolean isEmpty() {
        return (head + 1) % array.length == tail;
    }

    public int size() {
        if (tail > head) {
            return tail - head - 1;
        } else {
            return array.length - 1 - head + tail;
        }
    }
}
