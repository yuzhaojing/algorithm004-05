import java.util.Arrays;

/**
 * 26. 删除排序数组中的重复项
 */
public class Leetcode_26_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int length = method(nums);
        System.out.println("length = " + length);
        System.out.println("nums = " + Arrays.toString(nums));
    }

    private static int method(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}

