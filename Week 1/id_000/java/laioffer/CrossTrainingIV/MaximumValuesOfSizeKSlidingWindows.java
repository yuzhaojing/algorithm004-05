package laioffer.CrossTrainingIV;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaximumValuesOfSizeKSlidingWindows {

    public static void main(String[] args) {
        System.out.println(new MaximumValuesOfSizeKSlidingWindows().maxWindows(new int[] {1, 2, 3, 2, 4, 2, 1}, 3));
    }

    /**
     * input:  int[] array
     *         int k
     * output: List<Integer>
     * Assume: array != null && array.length >= k && k >= 1
     *
     * high level: 使用单调队列解答
     * detail level:
     *  1、创建一个deque，内部顺序为单调递减
     *     First端第一个元素表示当前window最大value的index，后面的元素表示之后有可能成为某个window的最大值
     *  2、每次移动window，有以下几种情况
     *     case1: 新进入的元素大于等于Last端的一个或多个元素
     *            while (!deque.isEmpty() && array[deque.peekLast()] <= newValue) deque.pollLast()
     *     case2: First端第一个元素已经不在window内了
     *            if (!deque.isEmpty() && deque.peekFirst() < leftBound) deque.pollFirst()
     *  3、res.add(deque.peekFirst());
     *
     * amortize time = O(1)
     * worst case: 一次移动操作为O(n)，但是这次操作之前的n次操作一定为O(1)
     * (n + n * 1) / n = 2 = O(1)
     * space = O(k)
     */
    public List<Integer> maxWindows(int[] array, int k) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (array == null || k < 1 || array.length < k) {
            return res;
        }

        Deque<Integer> deque = new ArrayDeque<>();

        // i表示右边界
        for (int i = 0; i < array.length; i++) {
            while (!deque.isEmpty() && array[deque.peekLast()] <= array[i]) {
                deque.pollLast();
            }

            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            deque.offerLast(i);

            // 右边界到左边界的长度等于k的时候开始计算
            if (i >= k - 1) {
                res.add(array[deque.peekFirst()]);
            }
        }

        return res;
    }
}
