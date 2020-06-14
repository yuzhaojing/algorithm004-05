/**
 * 1、两数之和
 */
public class Leetcode_309_BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(method2(prices));
    }

    /**
     * 动态规划
     * 定义i为天数，k为可交易次数，s为当前是否持有股票
     * DP方程：dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
     * <p>
     * 初始化: dp[-1][k][0] = 0
     * dp[-1][k][1] = Integer.MIN_VALUE（表示不可能）
     * dp[i][0][0]  = 0
     * dp[i][0][1]  = Integer.MIN_VALUE
     */
    private static int method1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[1][0] = 0;
        dp[1][1] = 0;

        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    private static int method2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0;

        for (int i = 0; i < prices.length; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }

        return dp_i_0;
    }
}
