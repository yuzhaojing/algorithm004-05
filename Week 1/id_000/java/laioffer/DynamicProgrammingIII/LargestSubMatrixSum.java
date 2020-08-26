package laioffer.DynamicProgrammingIII;

public class LargestSubMatrixSum {

    public static void main(String[] args) {

    }

    /**
     * 假设：matrix != null && matrix.length > 0 && matrix[0].length > 0
     * 如果不符合假设条件，matrix内没有元素，则返回值必然为0
     * high level: 使用一维DP解答
     * mid level: 枚举上下边界，将同一列的数据加在一起，形成一个一维数组
     *            然后对这个一维数组求最大子数组和
     *  dp三要素和最大子数组和一样
     * time = O(n^3)
     * space = O(n^2)
     */
    public int largest(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int result = Integer.MIN_VALUE;

        // 两层for循环，枚举上边界和下边界
        for (int i = 0; i < matrix.length; i++) {
            int[] cur = new int[matrix[0].length];
            for (int j = i; j < matrix.length; j++) {
                // 将两个边界内的数据压缩进一个一维数组
                for (int k = 0; k < cur.length; k++) {
                    cur[k] += matrix[j][k];
                }
                // 对该一维数组求最大和即可
                result = Math.max(result, getLargestSum(cur));
            }
        }

        return result;
    }

    private int getLargestSum(int[] cur) {
        int prev = cur[0];
        int globalMax = prev;

        for (int i = 1; i < cur.length; i++) {
            prev = prev < 0 ? cur[i] : prev + cur[i];
            globalMax = Math.max(globalMax, prev);
        }

        return globalMax;
    }

    /**
     * 假设：matrix != null && matrix.length > 0 && matrix[0].length > 0
     * 如果不符合假设条件，matrix内没有元素，则返回值必然为0
     * high level: 使用二维DP(二维前缀和)解答
     * mid level: 先将二维前缀和求出来，然后通过前缀和之差求出结果
     *  1、prefixSum2D[i][j]表示从(0, 0)到(i, j)中所有元素之和
     *  2、base case: prefixSum1D[0][0] = matrix[0][0]
     *                if (i == 0) prefixSum2D[i][j] = prefixSum1D[i][j]
     *  3、induction rule: prefixSum1D[i][j] = prefixSum1D[i][j - 1] + matrix[i][j]
     *                     prefixSum2D[i][j] = prefixSum2D[i][j - 1] + prefixSum1D[i - 1][j] + matrix[i][j]
     * time = O(n^4)
     * space = O(n^2)
     */
    public int largest1(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int result = Integer.MIN_VALUE;

        int[] prefixSum1D = new int[cols];
        int[][] prefixSum2D = new int[rows][cols];

        // 填写二维前缀和
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    prefixSum1D[j] = matrix[i][0];
                    prefixSum2D[i][j] = prefixSum1D[j];
                } else if (i == 0) {
                    prefixSum1D[j] = prefixSum1D[j - 1] + matrix[i][j];
                    prefixSum2D[i][j] = prefixSum1D[j];
                } else if (j == 0) {
                    prefixSum1D[j] = matrix[i][0];
                    prefixSum2D[i][j] = prefixSum2D[i - 1][j] + prefixSum1D[j];
                } else {
                    prefixSum1D[j] = prefixSum1D[j - 1] + matrix[i][j];
                    prefixSum2D[i][j] = prefixSum2D[i - 1][j] + prefixSum1D[j];
                }
            }
        }

        // (i, j) 左上角 (k, t) 右上角
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = i; k < rows; k++) {
                    for (int t = j; t < cols; t++) {
                        int sum = 0;
                        if (i == 0 && j == 0) {
                            sum = prefixSum2D[k][t];
                        } else if (i == 0) {
                            sum = prefixSum2D[k][t] - prefixSum2D[k][j - 1];
                        } else if (j == 0) {
                            sum = prefixSum2D[k][t] - prefixSum2D[i - 1][t];
                        } else {
                            sum = prefixSum2D[k][t] - prefixSum2D[i - 1][t] - prefixSum2D[k][j - 1] + prefixSum2D[i - 1][j - 1];
                        }
                        result = Math.max(result, sum);
                    }
                }
            }
        }

        return result;
    }
}
