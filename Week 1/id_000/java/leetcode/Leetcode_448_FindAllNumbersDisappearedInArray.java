package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1、两数之和
 */
public class Leetcode_448_FindAllNumbersDisappearedInArray {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(method2(nums));
    }

    private static List<Integer> method1(int[] nums) {
        List<Integer> res = new ArrayList<>();

        if (nums == null || nums.length == 0) return res;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 1; i <= nums.length; i++) {
            if (!map.containsKey(i)) res.add(i);
        }

        return res;
    }

    private static List<Integer> method2(int[] nums) {
        List<Integer> res = new ArrayList<>();

        if (nums == null || nums.length == 0) return res;

        for (int i = 0; i < nums.length; i++) {
             int num = Math.abs(nums[i]) - 1;
             nums[num] = -Math.abs(nums[num]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) res.add(i + 1);
        }

        return res;
    }
}
