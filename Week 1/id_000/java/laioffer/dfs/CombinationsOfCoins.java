package laioffer.dfs;


import java.util.ArrayList;
import java.util.List;

public class CombinationsOfCoins {

    public static void main(String[] args) {
        int[] coins = {25, 10, 5, 2};
        System.out.println(new CombinationsOfCoins().combinations(99, coins));
    }

    /**
     * 假设有n个币值，总共需要凑出m元
     *
     * 总共有几层？每层存储了什么东西？
     * 不确定，最多有 (m / 最小币值) 层
     * 每层存储使用了几枚当前币值
     *
     * 每层有几个可能（每个节点会有几个分支）
     * 每层有n个可能，即有多少种币值就有多少个分支
     *
     * 假设最大层数level = (m / 最小币值)
     *
     * Time = O(n^level)
     * 分析思路:
     * recursion tree中，每层最多可以有n个分支
     * time = n + n^2 + n^3 + ... + n^level = O(n^level)
     *
     *
     * Space = O(level)
     */
    public List<List<Integer>> combinations(int target, int[] coins) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        if (coins == null || coins.length == 0 || target <= 0) {
            return res;
        }

        combinations(target, coins, new ArrayList<Integer>(), 0, res);
        return res;
    }

    private void combinations(int target, int[] coins, List<Integer> cur, int index, List<List<Integer>> res) {
        // 可以使用这个退出条件，但是提前一层判断，
        // 然后在最后一层自己使用target / coins[index]更加快速
        // 节省了recursion tree最后一层的消耗
        // if (coins.length == index) {
        //   if (target == 0) {
        //     res.add(new ArrayList<>(cur));
        //   }
        //   return;
        // }

        // 提前一层退出，最后一层手动计算
        if (coins.length - 1 == index) {
            // 如果target % coins[index] != 0
            // 则代表无法以整数的形式将target拼出来
            if (target % coins[index] == 0) {
                cur.add(target / coins[index]);
                res.add(new ArrayList<Integer>(cur));
                // 这里也需要回退状态，不然会影响后面的计算
                cur.remove(cur.size() - 1);
            }
            return;
        }

        for (int i = 0; i <= target / coins[index]; i++) {
            cur.add(i);
            combinations(target - coins[index] * i, coins, cur, index + 1, res);
            cur.remove(cur.size() - 1);
        }
    }
}
