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
     * input: array int[]
     * output: int (返回将input array分成两半的subset中，差值最小的差)
     * 假设：array != null && array.length > 1
     * 如果不符合假设，则没有两个包含int的subset，那么也就无法求差，所以返回Integer.MIN_VALUE
     *
     * high level: 使用DFS的subset方案解答
     * mid level: 在使用之前的方案的时候，加一个base case，当cur.size() >= n / 2的时候，计算差值并尝试更新全
     *  1、recursion tree 最多有多少层？            如果array的长度为n，那么最多就有n层
     *  2、recursion tree 每个node最多有几种case？  2种，选择或者不选择
     *
     * time = O(2^n)
     * space = O(n)
     * heap: cur list (n)
     * call stack: O(n)
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
