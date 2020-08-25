package laioffer.DynamicProgrammingIII;

public class LongestCrossOf1s {

    public static void main(String[] args) {

    }

    /**
     * 假设：matrix != null && matrix.length > 0 && matrix[0].length > 0
     * 如果不符合假设，那么matrix中将不存在元素，自然也不会存在符合题意的解，暂定返回0
     * high level: 使用二维DP解答
     * mid level: linear scan回头看，将二维DP看成每个方向4个一维DP，一共16个一维DP的组
     *            每个一维DP就是连续最长1的问题，最后合并求每个点的最小值
     *  1、M[i][j]表示在该坐标的上下左右四个方向的连续最长1的最小值
     *  2、base case: 在四个方向都是连续最长1的base case
     *  3、induction rule: 在四个方向都是连续最长1的induction rule
     *
     * time = O(n^2)
     * 分析: leftUp和rightDown内部都是一个n^2的循环加上一个merge
     * merge的时间复杂度也是n^2，但是这两个部分是并行执行的
     * 所以total time = n^2(leftUp上半部分) + n^2(leftUp merge) +
     * n^2(rightDown上半部分) + n^2(rightDown merge) +
     * n^2(leftUp和rightDown merge) = O(5 * n^2) = O(n^2)
     *
     * space = O(n^2)
     */
    public int largest(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] leftUp = leftUp(matrix, rows, cols);
        int[][] rightDown = rightDown(matrix, rows, cols);

        return merge(leftUp, rightDown, rows, cols);
    }

    private int merge(int[][] one, int[][] two, int rows, int cols) {
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                one[i][j] = Math.min(one[i][j], two[i][j]);
                result = Math.max(result, one[i][j]);
            }
        }
        return result;
    }

    private int[][] leftUp(int[][] matrix, int rows, int cols) {
        int[][] left = new int[rows][cols];
        int[][] up = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果matrix[i][j] == 0，则对应的数组内一定是0
                // 由于数组内默认值是0，所以可以不需要处理
                if (matrix[i][j] == 1) {
                    // 这种情况下，无论对于哪个数组，都是base case
                    if (i == 0 && j == 0) {
                        left[i][j] = 1;
                        up[i][j] = 1;
                    } else if (i == 0) {
                        // 横坐标为0，作为up数组的base case
                        left[i][j] = left[i][j - 1] + 1;
                        up[i][j] = 1;
                    } else if (j == 0) {
                        // 纵坐标为0，作为left数组的base case
                        left[i][j] = 1;
                        up[i][j] = up[i - 1][j] + 1;
                    } else {
                        // 不是base case的情况下，都是上一个数的值 + 1
                        left[i][j] = left[i][j - 1] + 1;
                        up[i][j] = up[i - 1][j] + 1;
                    }
                }
            }
        }

        merge(left, up, rows, cols);
        return left;
    }

    private int[][] rightDown(int[][] matrix, int rows, int cols) {
        int[][] right = new int[rows][cols];
        int[][] down = new int[rows][cols];

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                // 如果matrix[i][j] == 0，则对应的数组内一定是0
                // 由于数组内默认值是0，所以可以不需要处理
                if (matrix[i][j] == 1) {
                    if (i == rows - 1 && j == cols - 1) {
                        right[i][j] = 1;
                        down[i][j] = 1;
                    } else if (i == rows - 1) {
                        right[i][j] = right[i][j + 1] + 1;
                        down[i][j] = 1;
                    } else if (j == cols - 1) {
                        right[i][j] = 1;
                        down[i][j] = down[i + 1][j] + 1;
                    } else {
                        right[i][j] = right[i][j + 1] + 1;
                        down[i][j] = down[i + 1][j] + 1;
                    }
                }
            }
        }

        merge(right, down, rows, cols);
        return right;
    }
}
