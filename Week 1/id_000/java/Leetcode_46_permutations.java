import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 1、两数之和
 */
public class Leetcode_46_permutations {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> list = method1(nums);
        System.out.println(list);
    }

    private static List<List<Integer>> method1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;

        permute(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    private static void permute(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index) {
        if (nums.length == index) {
            res.add(tmp);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmp.contains(nums[i])) continue;
            List<Integer> newTmp = new ArrayList<>(tmp);
            newTmp.add(nums[i]);
            permute(res, newTmp, nums, index + 1);
        }
    }
}
