package laioffer.recursionI_and_sorting_algorithms;

import java.util.Arrays;

public class RainbowSortI {

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, -1, 0, 1, -1};
        new RainbowSortI().rainbowSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public int[] rainbowSort(int[] array) {
        // Write your solution here
        if (array == null || array.length <= 1) {
            return array;
        }

        int neg = 0;
        int zero = 0;
        int one = array.length - 1;

        while (zero <= one) {
            if (array[zero] == -1) {
                swap(array, neg++, zero++);
            } else if (array[zero] == 0) {
                zero++;
            } else {
                swap(array, zero, one--);
            }
        }

        return array;
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
