package laioffer.queue_and_stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class MergeSortStackTest {

    public static void main(String[] args) {
        LinkedList<Integer> s1 = new LinkedList<>();
        s1.add(3);
        s1.add(1);
        s1.add(2);
        s1.add(5);

        new MergeSortStackTest().mergeSort(s1);

        while (!s1.isEmpty()) {
            System.out.println(s1.pollFirst());
        }
    }

    /**
     * 用array进行merge sort，需要两个array
     * 一个是输入数组，放原本的元素
     * 另一个是缓存数组，用于排序
     * <p>
     * 换成stack则需要三个stack
     * 两个用于放原本的元素，因为stack无法random access
     * 另一个是用于排序
     */

    public void mergeSort(Deque<Integer> s1) {
        if (s1 == null || s1.size() == 0) {
            return;
        }

        mergeSort(s1, new ArrayDeque<Integer>(), new ArrayDeque<Integer>(), s1.size());
    }

    private void mergeSort(Deque<Integer> left, Deque<Integer> right, Deque<Integer> buffer, int size) {
        if (size <= 1) {
            return;
        }

        int mid = size / 2;
        for (int i = 0; i < mid; i++) {
            right.offerFirst(left.pollFirst());
        }

        mergeSort(left, right, buffer, size - mid);
        mergeSort(right, left, buffer, mid);

        merge(left, right, buffer, size - mid, mid);
    }

    private void merge(Deque<Integer> left, Deque<Integer> right, Deque<Integer> buffer, int leftSize, int rightSize) {
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (left.peekFirst() > right.peekFirst()) {
                buffer.offerFirst(left.pollFirst());
                leftIndex++;
            } else {
                buffer.offerFirst(right.pollFirst());
                rightIndex++;
            }
        }

        while (leftIndex < leftSize) {
            buffer.offerFirst(left.pollFirst());
            leftIndex++;
        }

        while (rightIndex < rightSize) {
            buffer.offerFirst(right.pollFirst());
            rightIndex++;
        }

        while (!buffer.isEmpty()) {
            left.offerFirst(buffer.pollFirst());
        }
    }
}
