package laioffer.CrossTrainingII;

import java.util.*;

public class TwoSumAllPairI {

    public static void main(String[] args) {
        int[] array = {3, 9, 1, 2, 3};
        System.out.println(new TwoSumAllPairI().allPairs(array, 4));

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
     *     key: 对应的数字  value: 出现的角标
     *  2、每次遍历到一个数字的时候，查看hashmap中是否存在target - num的元素，如果存在，那么以当前的角标和之前的角标拼成答案
     *
     * time = O(n^2)
     * space = O(n)
     *
     * 注意：Arrays.asList(...)构建出来的ArrayList是abstractList的子类，没有add方法
     */
    public List<List<Integer>> allPairs(int[] array, int target) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        if (array == null || array.length < 2) {
            return res;
        }

        Map<Integer, List<Integer>> indicesMap = new HashMap<>();


        for (int i = 0; i < array.length; i++) {
            List<Integer> indices = indicesMap.get(target - array[i]);
            if (indices != null) {
                for (int index : indices) {
                    res.add(Arrays.asList(i, index));
                }
            }

            if (!indicesMap.containsKey(array[i])) {
                indicesMap.put(array[i], new ArrayList<>());
            }

            indicesMap.get(array[i]).add(i);
        }

        return res;
    }
}
