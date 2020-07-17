package laioffer.dfs;


import java.util.ArrayList;
import java.util.List;

public class CombinationsOfCoins {

    public static void main(String[] args) {
        int[] coins = {25, 10, 5, 2};
        System.out.println(combinations(99, coins));
    }

    /**
     * 时间复杂度O(2^n)
     * 空间复杂度O(n)
     */
    private static List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        if (target < 0) return res;

        combinationsHelper(target, coins, res, new ArrayList<>(), 0);
        return res;
    }

    private static void combinationsHelper(int target, int[] coins, List<List<Integer>> res, ArrayList<Integer> tmp, int index) {
        if (coins.length - 1 == index) {
            if (target % coins[index] == 0) {
                tmp.add(index, target / coins[index]);
                res.add(new ArrayList<>(tmp));
                tmp.remove(index);
            }
            return;
        }

        for (int i = 0; i <= target / coins[index]; i++) {
            tmp.add(index, i);
            combinationsHelper(target - i * coins[index], coins, res, tmp, index + 1);
            tmp.remove(index);
        }
    }
}
