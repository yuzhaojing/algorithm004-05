package laioffer.DynamicProgrammingIII;

public class LargestSubMatrixSum {

    public static void main(String[] args) {

    }

    // time = O(n^3)
    // space = O(n)
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
}
