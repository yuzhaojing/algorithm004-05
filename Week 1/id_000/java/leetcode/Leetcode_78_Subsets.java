package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_78_Subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(method1(nums));
    }

    private static List<List<Integer>> method1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;

        subSets(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    private static void subSets(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index) {
        if (nums.length == index) {
            res.add(tmp);
            return;
        }

        subSets(res, tmp, nums, index + 1);
        tmp = new ArrayList<Integer>(tmp) {{ add(nums[index]); }};
        subSets(res, tmp, nums, index + 1);
    }
}
