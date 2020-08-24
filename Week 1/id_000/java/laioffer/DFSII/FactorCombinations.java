package laioffer.DFSII;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorCombinations {

    public static void main(String[] args) {
        System.out.println(new FactorCombinations().combinations(100));
    }

    /**
     * 假设：target > 1
     * 如果target <= 1的话，target将不存在非自身和1的公因数，暂定返回空list
     * high level: 使用DFS来进行解答
     * mid level:
     *  1、遍历0～target，找出公因数
     *  2、对每个公因数进行遍历，枚举出小于target的所有可能性
     *     每层分几个叉 target/最小公因数
     *     一共分几层   公因数的个数
     * time = O(m^n + target)
     * m = target/最小公因数 n = 公因数的个数
     * space = O(n)
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
        if (factors.size() - 1 == index) {
            int factor = factors.get(factors.size() - 1);
            if (target % factor == 0) {
                for (int j = 1; j <= target % factor; j++) {
                    cur.add(factor);
                }
                res.add(new ArrayList<>(cur));
                for (int j = 1; j <= target % factor; j++) {
                    cur.remove(cur.size() - 1);
                }
            }
            return;
        }

        for (int i = 0; i <= target / factors.get(index); i++) {
            if (i != 0) {
                for (int j = 1; j <= i; j++) {
                    cur.add(factors.get(index));
                }
                target /= (i * factors.get(index));
            }
            combinations(target, index + 1, factors, cur, res);
            if (i != 0) {
                for (int j = 1; j <= i; j++) {
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }
}
