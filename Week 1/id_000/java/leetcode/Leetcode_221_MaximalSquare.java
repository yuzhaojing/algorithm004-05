package leetcode;

import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_221_MaximalSquare {

    public static void main(String[] args) {
        char[][] ints = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(method1(ints));
    }

    /**
     * 动态规划
     * DP方程：dp[i][j] = 1 + Min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
     * dp[i][j]代表以（i，j）右下角的正方形最大边长
     *
     * 10100
     * 10111
     * 11111
     * 10010
     */
    private static int method1(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int columns = matrix[0].length;
        int maxSide = 0;

        int[][] dp = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    }
                }

                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }

        return maxSide * maxSide;
    }
}
