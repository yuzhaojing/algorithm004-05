import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1、两数之和
 */
public class Leetcode_215_FindKthLargest {

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(method2(nums, 4));
    }

    private static int method1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) queue.poll();
        }

        return queue.poll();
    }

    private static int method2(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
