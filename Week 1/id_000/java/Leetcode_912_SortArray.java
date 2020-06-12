import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_912_SortArray {

    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 2, 0, 0};
        mergeSort(nums, 0, 5);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 冒泡排序 时间复杂度O(N^2)
     */
    private static int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        return nums;
    }

    /**
     * 选择排序 时间复杂度O(N^2)
     */
    private static int[] selectionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }

            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }

        return nums;
    }

    /**
     * 插入排序 时间复杂度O(N^2)
     */
    private static int[] insertionSort(int[] nums) {

        return nums;
    }

    /**
     * 快速排序 时间复杂度O(NlogN)
     */
    private static int[] quickSort(int[] nums, int begin, int end) {
        if (end <= begin) return nums;

        int pivot = partition(nums, begin, end);

        quickSort(nums, begin, pivot - 1);
        quickSort(nums, pivot + 1, end);

        return nums;
    }

    private static int partition(int[] nums, int begin, int end) {
        int pivot = end;
        int counter = begin;

        for (int i = begin; i < end; i++) {
            if (nums[i] < nums[pivot]) {
                int temp = nums[i];
                nums[i] = nums[counter];
                nums[counter] = temp;

                counter++;
            }
        }

        int temp = nums[pivot];
        nums[pivot] = nums[counter];
        nums[counter] = temp;

        return counter;
    }

    /**
     * 归并排序 时间复杂度O(NlogN)
     */
    private static int[] mergeSort(int[] nums, int left, int right) {
        if (right <= left) return nums;

        int mid = left + (right - left) / 2;

        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        merge(nums, left, mid, right);
        return nums;
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] tmpArr = new int[right - left + 1];

        int i = left; //左边数组起始角标
        int j = mid + 1; // 右边数组起始角标
        int k = 0; // 临时数组起始角标

        while (i <= mid && j <= right) {
            tmpArr[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }

        while (i <= mid) tmpArr[k++] = nums[i++];
        while (j <= right) tmpArr[k++] = nums[j++];

        System.arraycopy(tmpArr, 0, nums, left, tmpArr.length);
    }
}
