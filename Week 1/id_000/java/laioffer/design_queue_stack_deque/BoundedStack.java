package laioffer.design_queue_stack_deque;

/**
 * 使用数组实现一个Stack
 * 维护一个head指针，当插入元素时head++，删除元素时head--
 * 当数组装满的时候，新建一个1.5倍大小的数组，并将数据copy过去
 */
public class BoundedStack {

    public static void main(String[] args) {
        BoundedStack stack = new BoundedStack(1);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    private int[] array;
    private int head;
    private int size;

    public BoundedStack(int capacity) {
        array = new int[capacity];
    }

    // head指针表示下一个插入元素的位置
    // 如果head指针的值超过的数组最大角标，则需要扩容
    public void push(int value) {
        if (head == array.length) {
            resize(size + 1);
        }
        array[head] = value;
        head++;
        size++;
    }

    // 每次扩容*1.5倍，如果扩容后出现int溢出等问题，设置为最小扩容阈值
    private void resize(int minCapacity) {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        int[] newArray = new int[newCapacity];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    // head指针的前一位才是最后一个元素
    public Integer pop() {
        if (head == 0) {
            return null;
        }

        int value = array[head - 1];
        head--;
        size--;
        return value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
