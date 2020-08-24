package laioffer.DynamicProgrammingIII;

public class LargestSquareSurroundedByOne {

    public static void main(String[] args) {

    }

    // time = O(n^3)
    public int largestSquareSurroundedByOne(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // 物理意义：在当前行，从左到右以当前列为终点的最长1是多少
        int[][] left = new int[rows][cols];
        int[][] up = new int[rows][cols];
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 && j == 0) {
                        left[i][j] = 1;
                        up[i][j] = 1;
                    } else if (i == 0) {
                        left[i][j] = left[i][j - 1] + 1;
                        up[i][j] = 1;
                    } else if (j == 0) {
                        left[i][j] = 1;
                        up[i][j] = up[i - 1][j] + 1;
                    } else {
                        left[i][j] = left[i][j - 1] + 1;
                        up[i][j] = up[i - 1][j] + 1;
                    }
                }

                // 由于是将当前节点作为右下角，所以可以直接进行判断，不需要等left和up表格填好
                int maxLen = Math.min(left[i][j], up[i][j]);

                while (maxLen > result) {
                    // 由于maxLen是到当前位置的1的个数，但是i已经占据了一个1
                    // 所以对i来说距离maxLen的点是i - maxLen + 1
                    if (left[i - maxLen + 1][j] >= maxLen && up[i][j - maxLen + 1] >= maxLen) {
                        result = maxLen;
                        break;
                    }
                    maxLen--;
                }
            }
        }

        return result;
    }
}