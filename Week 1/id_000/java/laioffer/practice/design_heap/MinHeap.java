package laioffer.practice.design_heap;

public class MinHeap {
    private int[] array;
    private int size;

    public MinHeap(int[] array) {
        this.array = array;
    }

    public MinHeap(int capacity) {
        this.array = new int[capacity];
    }

    public void pocalateUp(int index) {
        while (index > 0) {
            int parentIndex = size / 2 - 1;
            if (array[index] < array[parentIndex]) {
                swap(index, parentIndex);
            }
            index = parentIndex;
        }
    }

    public void pocataleDown(int index) {
        while (index <= size / 2 - 1) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int swapIndex = leftChildIndex;

            if (rightChildIndex < size && array[rightChildIndex] < array[leftChildIndex]) {
                swapIndex = rightChildIndex;
            }

            swap(index, swapIndex);
            index = swapIndex;
        }
    }

    public void offer(int element) {

    }

    public int poll() {
        return 0;
    }

    public int peek() {
        return 0;
    }

    public int update(int index, int element) {
        return 0;
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
