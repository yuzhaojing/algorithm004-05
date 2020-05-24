package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 15、三数之和
 */
public class Leetcode_15_ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = method1(nums);
        System.out.println("res = " + res);
    }

    private static List<List<Integer>> method1(int[] nums) {
        if (nums == null || nums.length < 3) return null;

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 ) continue;
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                int sum = nums[i] + nums[j] + nums[k];
                if (nums[i] + nums[j] + nums[k] == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[++j]) {}
                    while (j < k && nums[k] == nums[--k]) {}
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return res;
    }

}
