package leetcode;

import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_322_CoinChange {

    public static void main(String[] args) {
        int[] nums = {186, 419, 83, 408};
        System.out.println(method1(nums, 6249));
    }

    /**
     * DP方程：f[i] = Min(for coin in coins f[i - coin]) + 1
     * i为金额amount，f[i]为组成金额i的最小数量
     * <p>
     * 初始化：f[0] = 0
     */
    private static int method1(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) return 0;

        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
