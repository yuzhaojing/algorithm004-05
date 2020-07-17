package laioffer.binary_search;

import java.util.Arrays;

public class KClosestInSortedArray {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        System.out.println(Arrays.toString(binarySearchClosestK(nums, 10, 3)));
    }

    // 搜索大小和target最接近的k个数
    private static int[] binarySearchClosestK(int[] array, int target, int k) {
        if (array == null || array.length == 0) {
            return array;
        }

        // 获取小于等于target的最大角标
        int left = largerSmallerEqual(array, target);
        int right = left + 1; // 如果left是数组的最后一个元素，right可能越界

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            // 优先判断right是否越界
            if (right > array.length - 1 || (left >= 0 && target - array[left] <= array[right] - target)) {
                res[i] = array[left--];
            } else{
                res[i] = array[right++];
            }
        }

        return res;
    }

    private static int largerSmallerEqual(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                // 将mid作为左边界
                left = mid;
            } else if (array[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        // 优先看right，因为要找的是最大角标
        if (array[right] <= target) {
            return right;
        }

        if (array[left] <= target) {
            return left;
        }

        return -1;
    }
}
