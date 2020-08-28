package laioffer.DFSII;


import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {

    public static void main(String[] args) {
        System.out.println(new FactorCombinations().combinations(100));
    }

    /**
     * input: int
     * output: List<List<String>> (返回可以通过乘法构成target的公因数集合)
     * 假设：target > 1
     * 如果不符合假设，则没有大于1的公因数，那么此题无解，返回空list
     *
     * high level: 使用DFS的99cent方案解答
     * mid level: 在使用之前的方案之前，需要先求出构成的硬币
     *  1、recursion tree 最多有多少层？            公因数的个数
     *  2、recursion tree 每个node最多有几种case？  target / 大于1的最小公因数
     *
     * time = O(level^case * (case) + target / 2)
     * space = O(level + case)
     * heap: factor list (level)
     *       cur list (case)
     * call stack: O(level)
     */
    public List<List<Integer>> combinations(int target) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        if (target <= 1) {
            return res;
        }

        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i < target; i++) {
            if (target % i == 0) {
                factors.add(i);
            }
        }

        combinations(target, 0, factors, new ArrayList<>(), res);
        return res;
    }

    private void combinations(int target, int index, List<Integer> factors, List<Integer> cur, List<List<Integer>> res) {
        if (factors.size() == index) {
            if (target == 1) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }

        // 不选这个公因数
        combinations(target, index + 1, factors, cur, res);

        int factor = factors.get(index);
        int size = cur.size();
        // 当target还能被factor除尽的时候，进行循环
        while (target % factor == 0) {
            // 每次循环往cur中add一个factor
            cur.add(factor);
            target /= factor;
            combinations(target, index + 1, factors, cur, res);
        }

        // 删除cur中的记录
        cur.subList(size, cur.size()).clear();
    }
}
