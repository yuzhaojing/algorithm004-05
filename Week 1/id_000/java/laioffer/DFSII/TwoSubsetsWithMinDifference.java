package laioffer.DFSII;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSubsetsWithMinDifference {

    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        System.out.println(new TwoSubsetsWithMinDifference().minDifference(array));
    }

    /**
     * 假设：array != null
     * 如果不符合假设，那么array没有subset，也就无法计算最小差了
     * 返回值暂定-1，正常的返回值会取绝对值
     * high level: 使用DFS的subset方法进行解答
     * mid level: 每层分几个叉 - 2个/ 共有几层 - n层
     *  1、使用加元素不加元素的方法，计算subset
     *  2、如果当前的subset集合的元素个数大于等于总数的一半，计算两个subset和的差
     *     (totalSum - curSum) - curSum
     *  3、如果当前subset集合的元素个数不到总数的一半，并且已经到底了，那么不做处理，直接返回
     *     此时不符合题意，所以不需要计算
     * time = O(2^n)
     * space = O(n)
     * heap: subset集合 O(n)
     * call stack: 递归n层 O(n)
     */
    public int minDifference(int[] array) {
        // Write your solution here
        if (array == null) {
            return -1;
        }

        int totalSum = 0;
        for (int i : array) {
            totalSum += i;
        }
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;

        subsets(array, 0, new ArrayList<>(), 0, totalSum, res);
        return res[0];
    }

    private void subsets(int[] array, int index, List<Integer> cur, int curSum, int totalSum, int[] res) {
        // 当前的subset集合的元素个数大于等于总数的一半，计算两个subset和的差
        if (cur.size() >= array.length / 2) {
            res[0] = Math.min(res[0], Math.abs(totalSum - 2 * curSum));
            return;
        }

        // 不符合题意，不需要计算
        if (index == array.length) {
            return;
        }

        cur.add(array[index]);
        curSum += array[index];
        subsets(array, index + 1, cur, curSum, totalSum, res);
        cur.remove(cur.size() - 1);
        curSum -= array[index];
        subsets(array, index + 1, cur, curSum, totalSum, res);
    }
}
