package laioffer.DFSII;


import java.util.ArrayList;
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
