package laioffer.CrossTrainingII;

import java.util.*;

public class TwoSumAllPairII {

    public static void main(String[] args) {
        int[] array = {3, 9, 1, 2, 3};
        System.out.println(new TwoSumAllPairII().allPairs(array, 4));

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
     *  1、使用hashmap将遍历过的数字全部存储起来
     *     key: 对应的数字  value: 是否被使用
     *  2、每次遍历到一个数字的时候，查看hashmap中是否存在target - num的元素，如果存在，那么以当前的数和之前的数拼成答案
     *     并且map中两个数的value为true，表示这两个数已经被使用
     *
     * time = O(n)
     * space = O(n)
     */
    public List<List<Integer>> allPairs(int[] array, int target) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        if (array == null || array.length < 2) {
            return res;
        }

        Map<Integer, Boolean> map = new HashMap<>();
        for (int num : array) {
            Boolean used = map.get(target - num);

            if (used == null) {
                map.put(num, false);
            } else if (!used) {
                res.add(Arrays.asList(num, target - num));
                map.put(num, true);
                map.put(target - num, true);
            }
        }

        return res;
    }
}
