package laioffer.CrossTrainingIV;

import java.util.Arrays;

public class KClosestInSortedArray {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11};
        System.out.println(Arrays.toString(
                new KClosestInSortedArray().binarySearchClosestK(nums, 6, 4)));
    }

    /**
     * 在一个排序数组中，找到k个与target最接近的元素
     *
     * input:  int[] array
     *         int target
     *         int k
     * output: int[] (需要排序)
     * Assume: array != null && array.length >= k && k > 0
     *
     * high level: 使用两次binary search解答
     * detail level:
     *  1、使用binary search找到比target小的最大的元素
     *  2、以找到的元素为分界点，将原数组从逻辑上分为两个数组
     *     然后使用binary search分别对两个数组进行查找
     *  3、分别取k/2个元素，比较哪个离target更近，离target更近的那k/2个元素一定是最近的k个元素
     *  4、依次递归，最后返回两个角标中间的元素集合
     *
     * time = logn + 2logk = O(logn + logk)
     * space = O(logk)
     */
    public int[] binarySearchClosestK(int[] array, int target, int k) {
        if (array == null || k < 1 || array.length < k) {
            return new int[0];
        }

        int leftStart = largestSmaller(array, target);
        int[] res = new int[k];
        helper(array, leftStart, leftStart + 1, target, k, res);
        return res;
    }

    private void helper(int[] array, int leftStart, int rightStart, int target, int k, int[] res) {
        if (leftStart < 0) {
            for (int i = 0; i < res.length; i++) {
                res[i] = array[i];
            }
            return;
        }

        if (rightStart >= array.length) {
            for (int i = 0; i < res.length; i++) {
                res[i] = array[leftStart - k + 1 + i];
            }
            return;
        }

        if (k == 1) {
            if (target - array[leftStart] < array[rightStart] - target) {
                leftStart--;
            } else {
                rightStart++;
            }

            for (int i = 0; i < res.length; i++) {
                res[i] = array[leftStart + 1 + i];
            }
            return;
        }

        int leftMid = leftStart - (k / 2 - 1);
        int rightMid = rightStart + (k / 2 - 1);

        int leftVal = leftMid < 0 ? Integer.MAX_VALUE : array[leftMid];
        int rightVal = rightMid >= array.length ? Integer.MAX_VALUE : array[rightMid];

        if (target - leftVal < rightVal - target) {
            helper(array, leftMid - 1, rightStart, target, k - k / 2, res);
        } else {
            helper(array, leftStart, rightMid + 1, target, k - k / 2, res);
        }
    }

    private int largestSmaller(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        if (array[right] < target) {
            return right;
        }

        if (array[left] < target) {
            return left;
        }

        return -1;
    }
}
