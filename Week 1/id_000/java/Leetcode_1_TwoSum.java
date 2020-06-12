import java.util.Arrays;
import java.util.HashMap;

/**
 * 1、两数之和
 */
public class Leetcode_1_TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] ints = method2(nums, 24);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    private static int[] method1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            int element = target - nums[i];
            if (map.containsKey(element)) {
                return new int[]{map.get(element), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    private static int[] method2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(target - nums[i], i);
            } else {
                return new int[]{map.get(nums[i]), i};
            }
        }
        return new int[]{};
    }
}
