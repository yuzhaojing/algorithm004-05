package laioffer.heap;

import java.util.*;

public class KSmallestInUnsortedArray {

    public static void main(String[] args) {
        int[] nums = {2, 4, 8, 1, 5, 7, 9, 3};
        System.out.println(Arrays.toString(solution4(nums, 4)));
    }

    /**
     * 先将数组排序，然后返回前k个数
     *
     * 时间复杂度O(nlogn) 排序的时间复杂度为O(nlogn)，返回前k个数的时间复杂度为O(1)
     *
     * 空间复杂度O(k)
     */
    private static int[] solution1(int[] array, int k) {
        if (array == null || array.length <= k) return array;
        Arrays.sort(array);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = array[i];
        }
        return res;
    }

    /**
     * 将所有元素放入小顶堆中，最后取k个元素
     *
     * 时间复杂度O(n + klogn)
     *
     * 分析思路:
     * 将未排序数组组成堆，时间复杂度为O(n)
     * 然后需要pop出堆里的k个元素，pop每个元素O(logn)
     * 所以总时间复杂度为 O(n + klogn)
     */
    private static int[] solution2(int[] array, int k) {
        if (array == null || array.length <= k) return array;
        int[] res = new int[k];
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int num : array) {
            heap.add(num);
        }

        for (int i = 0; i < k; i++) {
            res[i] = heap.poll();
        }

        return res;
    }

    /**
     * 将k个元素放入大顶堆中，之后每次取元素和堆顶元素比较，
     * 小于堆顶元素就将堆顶元素pop出来，然后将新元素add进去
     *
     * 时间复杂度O(k + (n - k)logk)
     * 分析思路:
     * 将k个元素放入大顶堆中 O(k)
     * 后面还有 n-k 个元素，worst case每个元素都会替换 O((n - k)logk)
     * 所以总时间复杂度为 O(k + (n - k)logk)
     */
    private static int[] solution3(int[] array, int k) {
        if (array == null || array.length <= k) return array;
        int[] res = new int[k];
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int num : array) {
            if (heap.size() < k) {
                heap.add(num);
            } else if (num < heap.peek()) {
                heap.poll();
                heap.add(num);
            }
        }

        for (int i = 0; i < k; i++) {
            res[i] = heap.poll();
        }

        return res;
    }

    /**
     * 如何比较两者？
     * solution2 O(n + klogn)
     * solution3 O(k + (n - k)logk)
     *
     * 下面进行分情况讨论(前提 n > k, 不然不存在讨论需要)
     *
     * 1、n >> k
     * solution2 O(n)
     * solution3 O(nlogk)
     * 粗看认为O(n) < O(nlogk),因为当k > 2时logk就大于1了
     * 但是heapify的时间复杂度实际上为O(c * n)，也是也一个大于1的常数
     * 所以具体时间优势需要具体看logk与c的关系来判定
     *
     * 2、n与k相近，在同一数量级
     * solution2 O(nlogn)
     * solution3 O(nlogn)
     * 时间复杂度相同
     *
     * 所以两者时间比较与n、k、c三者都有关系
     */

    /**
     * 利用快排思想，每次选择一个pivot，将小于pivot的移到左边，大于pivot的移到右边
     * 时间复杂度 O(n^2) 平均时间复杂度O(n)
     * worst case 每次排除一个元素，每次耗费O(n),
     * 平均n/2 + n/4 +... = O(n)
     */
    private static int[] solution4(int[] array, int k) {
        if (array == null || array.length <= k) return array;

        quickPartition(array, 0, array.length - 1, k - 1);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = array[i];
        }

        return res;
    }

    private static void quickPartition(int[] array, int begin, int end, int k) {
        int pivot = partition(array, begin, end);

        if (pivot > k) {
            quickPartition(array, begin, pivot - 1, k);
        } else if (pivot < k) {
            quickPartition(array, pivot + 1, end, k);
        }
    }

    private static int partition(int[] array, int begin, int end) {
        int pivot = end;
        int counter = begin;

        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                swap(array, i, counter);
                counter++;
            }
        }

        swap(array, pivot, counter);
        return counter;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
