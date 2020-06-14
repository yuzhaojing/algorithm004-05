/**
 * 1、两数之和
 */
public class Leetcode_64_MinimumPathSum {

    public static void main(String[] args) {
        int[][] nums = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(method1(nums));
    }

    /**
     * DP方程：f[i][j] = Min(f[i - 1][j], f[i][j - 1]) + grid[i][j]
     *
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     */
    private static int method1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int[][] dp = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0){
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }
}
