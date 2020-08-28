package laioffer.DynamicProgrammingIII;

public class LargestSquareOfMatches {

    public static void main(String[] args) {

    }

    /**
     * 假设：matrix != null && matrix.length > 0 && matrix[0].length > 0
     * 如果不符合假设条件，matrix内没有元素，则返回值必然为0
     *
     * high level: 使用二维DP解答
     * mid level: 使用两个二维DP，存储最长连续1。
     *            因为我们只能知道右侧和下侧节点是否与当前节点相连，所以从右侧和下侧计算连续最长1
     *  1、right[i][j]表示在当前行，从右到左以当前坐标为终点的右侧有几个连接的点
     *     down[i][j]表示在当前行，从下到上以当前坐标为终点的下侧有几个连接的点
     *  2、base case: right[i][cols - 1] = 0  down[i][j] = down[rows - 1][j] = 0;
     *  3、induction rule:
     *       if (matrix[i][j] == 1) right[i][j] = right[i][j + 1] + 1
     *       if (matrix[i][j] == 2) down[i][j] = down[i + 1][j] + 1
     *       if (matrix[i][j] == 3) both of 1 and 2
     *
     * time = O(n^3)
     * space = O(n^2)
     */
    public int largestSquareOfMatches(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // 由于在当前节点，只能知道是否与右侧和下侧的节点连接
        // 所以dp数组也就从右往左，从下往上进行计算连续最长1

        // 多建一行一列，这一行一列都为0，因为他们的下侧与右侧没有节点与其相连
        // 用于减少base case的判断代码

        // 物理意义：以当前节点为终点，右侧最多有多少个点与当前点相连
        int[][] right = new int[rows + 1][cols + 1];
        // 物理意义：以当前节点为终点，下侧最多有多少个点与当前点相连
        int[][] down = new int[rows + 1][cols + 1];

        int result = 0;

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                } else if (matrix[i][j] == 2) {
                    down[i][j] = down[i + 1][j] + 1;
                } else if (matrix[i][j] == 3) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;

                    // 只有当前点同时与右侧和下层连接，才有可能作为正方形的左上角
                    int maxLen = Math.min(right[i][j], down[i][j]);

                    // 只要连续节点的数量大于已知的结果，就继续遍历
                    while (maxLen > result) {
                        // 从最大的连续节点开始判断，如果符合要求就更新result并进入下一个循环
                        if (right[i + maxLen][j] >= maxLen && down[i][j + maxLen] >= maxLen) {
                            result = maxLen;
                            break;
                        }
                        maxLen--;
                    }
                }
            }
        }

        return result;
    }
}
