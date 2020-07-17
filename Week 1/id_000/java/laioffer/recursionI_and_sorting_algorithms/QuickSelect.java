package laioffer.recursionI_and_sorting_algorithms;

import java.util.Arrays;

public class QuickSelect {

    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 2, 0, 0};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) return;

        quickSortHelper(nums, 0, nums.length - 1);
    }

    private static void quickSortHelper(int[] nums, int start, int end) {
        // base case
        if (start >= end) return;

        int pivot = partition(nums, start, end);
        quickSortHelper(nums, start, pivot - 1);
        quickSortHelper(nums, pivot + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int counter = start;
        int pivot = end;

        for (int i = start; i < end; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, counter);
                counter++;
            }
        }

        swap(nums, counter, pivot);
        return counter;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
