package laioffer.practice.design_heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Heap {
    private int[] array;
    private int size;

    public Heap(int[] array) {
        this.array = array;
    }

    public Heap(int capacity) {
        this.array = new int[capacity];
    }

    public void heapify() {
        // size / 2 - 1这个角标是最后一个非叶子节点
        for (int i = size / 2 - 1; i >= 0; i--) {
            pocalateDown(i);
        }
    }

    // 当父节点小于当前节点或者已经是root时，停止循环
    public void pocalateUp(int index) {
        // 只要index > 0，就一定还有父节点
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (array[parentIndex] > array[index]) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public void pocalateDown(int index) {
        // 左孩子角标为2 * index + 1
        // 只要左孩子存在，就可以继续循环
        // 2 * index + 1 <= size - 1可以化简为 index <= size / 2 - 1
        while (index <= size / 2 - 1) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int swapIndex = leftChildIndex;

            if (rightChildIndex < size &&
                    array[leftChildIndex] > array[rightChildIndex]) {
                swapIndex = rightChildIndex;
            }

            swap(swapIndex, index);
            index = swapIndex;
        }
    }

    public int update(int index, int element) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Heap is Empty.");
        }

        int res = array[index];
        array[index] = element;

        if (res < element) {
            pocalateUp(index);
        } else if (res > element) {
            pocalateDown(index);
        }

        return res;
    }

    public void offer(int element) {
        array[size] = element;
        pocalateUp(size);
        size++;

        // 扩容
        if (array.length == size) {
            array = Arrays.copyOf(array, (int)(array.length * 1.5));
        }
    }

    public int poll() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is Empty.");
        }

        int element = array[0];
        array[0] = array[size - 1];
        size--;
        pocalateDown(size);

        return element;
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is Empty.");
        }
        return array[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
