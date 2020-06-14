import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_279_PerfectSquares {

    public static void main(String[] args) {
        System.out.println(method1(12));
    }

    private static int method1(int n) {
        if (n <= 1) return n;

        int maxSquare = (int) Math.sqrt(n);
        int[] nums = new int[maxSquare];

        for (int i = 0; i < maxSquare; i++) {
            nums[i] = (int) Math.pow(i + 1, 2);
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int j = 1; j <= n; j++) {
            for (int num : nums) {
                if (j >= num) dp[j] = Math.min(dp[j], dp[j - num] + 1);
            }
        }

        return dp[n] > n ? -1 : dp[n];

    }
}
