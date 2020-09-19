package laioffer.AfternoonClass.class3_binarysearch;

import java.util.Arrays;

public class ClassicalBinarySearch {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 13};
        System.out.println(Arrays.toString(new ClassicalBinarySearch().findMissingKNumber(array, 2)));
    }

    /**
     * 1、假设一个排序数组内是1～n的连续数字，找出缺失的那一个
     *
     * input:  int[] array
     * output: int
     * Assume: array != null
     *
     * high level: 使用binary search解答
     * detail level: 数组会分为两部分，一部分num = index + 1，另一部分num = index + 2
     *               求出第一部分的最后一个元素或者第二部分的第一个元素
     *               以下按第二部分的第一个元素求解
     *  1、无法直接在循环中判断出结果，当left + 1 = right的时候结束循环
     *  2、if (array[mid] == mid + 1) left = mid (mid + 1)
     *    else                       right = mid
     *  3、此时还剩下left、right两个元素
     *    if (array[left] == left + 2)         return left + 1
     *    else if (array[right] == right + 2)   return right + 1
     *    else                                  return right + 2
     *
     * time = O(logN)
     * space = O(1)
     */
    public int findMissingNumber(int[] array) {
        if (array == null || array.length == 0) {
            return 1;
        }

        int left = 0;
        int right = array.length - 1;

        // 进入循环是至少有三个元素
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == mid + 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (array[left] == left + 2) {
            return left + 1;
        }
        if (array[right] == right + 2) {
            return right + 1;
        }
        return right + 2;
    }

    /**
     * follow up
     * 2、假设一个排序数组内是1～n的连续数字，找出缺失的那两个
     */
    public int[] findMissingTwoNumber(int[] array) {
        if (array == null || array.length == 0) {
            return new int[] {1, 2};
        }

        int[] res = new int[2];

        int left = 0;
        int right = array.length - 1;

        // 进入循环是至少有三个元素
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= mid + 2) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (array[left] == left + 3) {
            res[1] = left + 2;
        } else if (array[right] == right + 3) {
            res[1] = right + 2;
        } else {
            res[1] = right + 3;
        }

        left = 0;
        right = array.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= mid + 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (array[left] == left + 2) {
            res[0] = left + 1;
        } else if (array[right] == right + 2) {
            res[0] = right + 1;
        } else {
            res[0] = right + 2;
        }

        return res;
    }

    /**
     * follow up
     * 3、假设一个排序数组内是1～n的连续数字，找出缺失的那K个
     */
    public int[] findMissingKNumber(int[] array, int k) {
        int[] res = new int[k];
        if (array == null || array.length == 0) {
            for (int i = 0; i < k; i++) {
                res[i] = i + 1;
            }
            return res;
        }

        int left = 0;
        int right = array.length - 1;

        for (int i = 0; i < k; i++) {
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (array[mid] <= mid + i + 1) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (array[left] == left + i + 2) {
                res[i] = left + i + 1;
            } else if (array[right] == right + i + 2) {
                res[i] = right + i + 1;
            } else {
                res[i] = right + i + 2;
            }
            right = array.length - 1;
        }

        return res;
    }
}
