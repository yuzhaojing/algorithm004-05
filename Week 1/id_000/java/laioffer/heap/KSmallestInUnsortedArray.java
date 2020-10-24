package laioffer.heap;

import java.util.*;

public class KSmallestInUnsortedArray {

    public static void main(String[] args) {
        new Random().nextInt(3);
        int[] nums = {2, 4, 8, 1, 5, 7, 9, 3};
        System.out.println(Arrays.toString(new KSmallestInUnsortedArray().solution3(nums, 4)));
    }

    /**
     * 先将数组排序，然后返回前k个数
     * <p>
     * 时间复杂度O(nlogn) 排序的时间复杂度为O(nlogn)，返回前k个数的时间复杂度为O(1)
     * <p>
     * 空间复杂度O(k)
     */
    private int[] solution1(int[] array, int k) {
        if (array == null || array.length <= k) return array;
        Arrays.sort(array);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = array[i];
        }
        return res;
    }

    /**
     * 使用小顶堆，将整个array数组初始化为小顶堆，最后取出k个元素
     * <p>
     * Time = O(n + klogn)
     * 分析思路:
     * 1.初始化小顶堆 time = O(n)
     * 2.取出k个元素 time = klogn
     * total time = O(n + klogn)
     * <p>
     * Space = O(n)
     */
    private int[] solution2(int[] array, int k) {
        if (array == null || array.length == 0 || k == 0) {
            return new int[0];
        }

        Queue<Integer> minHeap = new PriorityQueue<>();

        for (int num : array) {
            minHeap.offer(num);
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll();
        }

        return res;
    }

    /**
     * 将k个元素放入大顶堆中，之后每次取元素和堆顶元素比较，
     * 小于堆顶元素就将堆顶元素pop出来，然后将新元素add进去
     * <p>
     * Time = O(k + (n - k)logk)
     * 分析思路:
     * 1.使用前k个元素初始化大顶堆 time = O(k)
     * 2.遍历后面 n - k 个元素，如果比堆顶元素小，就将其替换 time = 2 * (n - k) * logk
     * total time = O(k + (n-k)logk)
     * <p>
     * Space = O(k)
     */
    private int[] solution3(int[] array, int k) {
        if (array == null || array.length == 0 || k == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int num : array) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            } else if (maxHeap.peek() > num) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = maxHeap.poll();
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
     *
     * 但是solution3的空间复杂度为O(k) < solution2的O(n)
     * 所以总和考虑solution3依然较优
     */

    /**
     * 利用快排思想，每次选择一个pivot，将小于pivot的移到左边，大于pivot的移到右边
     * Time = O(n^2)
     * Avg Time = O(n)
     * worst case 每次排除一个元素，每次耗费O(n)
     * 期望 n + n/2 + n/4 + ... = O(n)
     *
     * 如果需要返回的数组是排序的
     * Avg Time = O(n) + O(klogk) = O(n + klogk)
     *
     * Space = O(n)
     * Avg Time = O(logn)
     * worst case 递归n层，每层O(1)
     * 期望logn层，每层O(1)
     */
    public int[] solution4(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length == 0 || k == 0) {
            return new int[0];
        }

        // 利用quick select算法，找出前n小的元素
        quickSelect(array, 0, array.length - 1, k - 1);
        int[] res = Arrays.copyOf(array, k);
        Arrays.sort(res);
        return res;
    }

    private void quickSelect(int[] array, int left, int right, int k) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(array, left, right);
        if (pivotIndex < k) {
            quickSelect(array, pivotIndex + 1, right, k);
        } else if (pivotIndex > k) {
            quickSelect(array, left, pivotIndex - 1, k);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = left + (int)(Math.random() * (right - left + 1));
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);

        int leftBound = left;
        int rightBound = right - 1;

        while (leftBound <= rightBound) {
            if (array[leftBound] < pivot) {
                leftBound++;
            } else {
                swap(array, leftBound, rightBound--);
            }
        }
        swap(array, leftBound, right);
        return leftBound;
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    /**
     * 如何比较两者？
     * solution3 O(k + (n - k)logk)
     * solution4 O(n + klogk)
     *
     * 1. n >> k
     * solution3 O(nlogk)
     * solution4 O(n)
     * 难以区分，理由同solution2与solution3的比较
     *
     * 2.n与k在同一数量级
     * solution3 O(nlogn)
     * solution4 O(nlogn)
     * 难以区分，理由同solution2与solution3的比较
     *
     * 3.空间复杂度
     * solution3 O(k)
     * solution4 O(logn)
     * 具体优劣看k和logn的大小关系
     */
}
