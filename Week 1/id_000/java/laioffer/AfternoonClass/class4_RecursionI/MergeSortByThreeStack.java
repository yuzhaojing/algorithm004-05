package laioffer.AfternoonClass.class4_RecursionI;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class MergeSortByThreeStack {

    public static void main(String[] args) {
        LinkedList<Integer> s1 = new LinkedList<>();
        s1.add(3);
        s1.add(1);
        s1.add(2);
        s1.add(5);

        new MergeSortByThreeStack().mergeSort(s1);

        while (!s1.isEmpty()) {
            System.out.println(s1.pollFirst());
        }
    }

    /**
     * high level:
     *     使用三个stack来模拟merge sort
     * 重要数据结构物理意义:
     *     s1:输入数据已经输出数据的栈
     *     s2、s3:缓存buffer的栈
     *
     * 1、找中点，通过将一半元素移动到s2中可以实现
     * 2、分别对以s1和s2为数据栈递归调用mergeSort
     * 3、合并s1以及s2的数据，最后copy回s1中
     */

    public void mergeSort(Deque<Integer> s1) {
        if (s1 == null || s1.size() == 0) {
            return;
        }

        mergeSort(s1, new ArrayDeque<Integer>(), new ArrayDeque<Integer>(), s1.size());
    }

    private void mergeSort(Deque<Integer> s1, Deque<Integer> s2, Deque<Integer> s3, int size) {
        if (size <= 1) {
            return;
        }

        int mid = size / 2;
        for (int i = 0; i < mid; i++) {
            s2.offerFirst(s1.pollFirst());
        }

        mergeSort(s2, s1, s3, mid);
        mergeSort(s1, s2, s3, size - mid);
        merge(s1, s2, s3, mid, size - mid);
    }

    private void merge(Deque<Integer> s1, Deque<Integer> s2, Deque<Integer> s3,
                       int leftSize, int rightSize) {
        int left = 0;
        int right = 0;

        while (left < leftSize && right < rightSize) {
            if (s1.peekFirst() < s2.peekFirst()) {
                s3.offerFirst(s1.pollFirst());
                left++;
            } else {
                s3.offerFirst(s2.pollFirst());
                right++;
            }
        }

        while (left < leftSize) {
            s3.offerFirst(s1.pollFirst());
            left++;
        }

        while (right < rightSize) {
            s3.offerFirst(s2.pollFirst());
            right++;
        }

        for (int i = 0; i < leftSize + rightSize; i++) {
            s1.offerFirst(s3.pollFirst());
        }
    }
}
