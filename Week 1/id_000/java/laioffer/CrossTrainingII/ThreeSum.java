package laioffer.CrossTrainingII;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        int[] array = {1, 2, 2, 3, 2, 4};
        System.out.println(new ThreeSum().allTriples(array, 8));
    }

    /**
     * input: array int[]
     *        target int
     * output: boolean (target是否在array中有两个数之和的解)
     * 假设：array != null && array.length > 2
     * 如果不符合假设，那么array中少于两个元素，必然无解，返回空list
     *
     * high level: 可以使用hashmap进行解答
     * mid level:
     *  1、将数组排序，方便跳过重复元素
     *  2、遍历数组，固定一个数字，计算剩余元素的two sum = target - num
     *
     * time = O(nlogn + n^2) = O(n^2)
     * space = O(n)
     */
    public List<List<Integer>> allTriples(int[] array, int target) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        if (array == null || array.length < 2) {
            return res;
        }

        // 先将数组排序
        Arrays.sort(array);

        // 枚举第一个元素
        for (int i = 0; i < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;

            // 计算array[left] + array[right] == target - array[i]
            while (left < right) {
                if (array[i] + array[left] + array[right] == target) {
                    // 如果找到答案了，加入结果集，并将left指针跳过重复元素
                    res.add(Arrays.asList(array[i], array[left], array[right]));
                    while (left < right && array[left] == array[left + 1]) {
                        left++;
                    }
                    left++;
                } else if (array[i] + array[left] + array[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }

            // 跳过重复元素
            while (i < array.length - 2 && array[i] == array[i + 1]) {
                i++;
            }
        }

        return res;
    }
}
