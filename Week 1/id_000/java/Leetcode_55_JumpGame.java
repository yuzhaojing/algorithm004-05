import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_55_JumpGame {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(method1(nums));
    }

    private static boolean method1(int[] nums) {
        if (nums == null || nums.length == 0) return true;

        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) return false;
            max = Math.max(max, i + nums[i]);
        }

        return true;
    }
}
