//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法

// 优秀解法：

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<List<Integer>> combine(int n, int k) {

        res = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        generateCombinations(n, k, 1, list);

        return res;

    }

    private ArrayList<List<Integer>> res;

    // 求解C(n,k), 当前已经找到的组合存储在c中, 需要从start开始搜索新的元素
    private void generateCombinations(int n, int k, int start, List<Integer> list) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        //减枝  i <= n-(k-list.size())+1
        for (int i = start; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            generateCombinations(n, k, i + 1, list);
            list.remove(list.size() - 1);

        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
