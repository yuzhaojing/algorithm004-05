import java.util.*;

/**
 * 1、两数之和
 */
public class Leetcode_347_TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(method1(nums, 2));
    }

    private static int[] method1(int[] nums, int k) {
        int[] res = new int[k];

        if (nums == null || nums.length == 0) return res;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((v1, v2) -> map.get(v2) - map.get(v1));
        heap.addAll(map.keySet());

        int index = 0;
        while (!heap.isEmpty() && k > 0) {
            res[index] = heap.poll();
            k--;
            index++;
        }

        return res;
    }
}
