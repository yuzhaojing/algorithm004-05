package leetcode;

import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_200_NumberOfIslands {

    public static void main(String[] args) {
        char[][] nums = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        int res = method1(nums);
        System.out.println(res);
    }

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    /**
     * 11000
     * 11000
     * 00100
     * 00011
     * <p>
     * 111
     * 010
     * 111
     */

    private static int method1(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int islands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') continue;

                sink(grid, i, j);
                islands++;
            }
        }

        return islands;
    }

    private static void sink(char[][] grid, int i, int j) {
        if (grid[i][j] == '0') return;

        grid[i][j] = '0';

        for (int k = 0; k < dx.length; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            if (x >= 0 && x < grid.length && y >= 0 && y < grid[i].length) {
                if (grid[x][y] != '0') sink(grid, x, y);
            }
        }
    }
}
