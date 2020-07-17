package laioffer.recursionI_and_sorting_algorithms;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 8};
        new SelectionSort().solve(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Time = O(n^2)
     * i = 0: Inner loop (0~n-1)     = n - 1
     * i = 1: Inner loop (1~n-1)     = n - 2
     * ...
     * i = n-3: Inner loop (n-3~n-1) = 2
     * i = n-2: Inner loop (n-2~n-1) = 1
     * Time = 1 + 2 + ... + n - 2 + n - 1 = n * n / 2 -> n^2 = O(n^2)
     * Space = O(1)
     */
    public int[] solve(int[] array) {
        // Write your solution here
        // Base Case
        if (array == null || array.length <= 1) {
            return array;
        }

        for(int i = 0; i < array.length - 1; i++) {
            // 确定遍历次数
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                // 确定每次遍历的次数
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            swap(array, i, minIndex);
        }

        return array;
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
