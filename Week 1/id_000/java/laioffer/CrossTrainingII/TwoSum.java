package laioffer.CrossTrainingII;

import java.util.*;

public class TwoSum {

    public static void main(String[] args) {

    }

    /**
     * input: array int[]
     *        target int
     * output: boolean (target是否在array中有两个数之和的解)
     * 假设：array != null && array.length > 2
     * 如果不符合假设，那么array中少于两个元素，必然无解，返回false
     *
     * high level: 可以使用hashset进行解答
     * mid level:
     *  1、使用hashset将遍历过的数字全部存储起来
     *  2、每次遍历到一个数字的时候，查看hashset中是否存在target - num的元素
     *
     * time = O(n)
     * space = O(n)
     */
    public boolean existSum(int[] array, int target) {
        // Write your solution here
        if (array == null || array.length < 2) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (set.contains(target - num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
