/**
 * 1、两数之和
 */
public class Leetcode_240_Search2dMatrixII {

    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(method2(matrix, 5));
        System.out.println(method2(matrix, 20));
    }

    /**
     * 二分法
     */
    private static boolean method1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        for (int i = 0; i < matrix.length; i++) {
            int left = 0;
            int right = matrix[i].length - 1;

            while (left <= right) {
                int mid = left + ((right - left) >> 1);

                if (matrix[i][mid] == target) {
                    return true;
                } else if (matrix[i][mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    /**
     * 观察规律，从右上角开始查找
     */
    private static boolean method2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int rowIndex = 0;
        int colIndex = matrix[0].length - 1;

        while (rowIndex < matrix.length && colIndex >= 0) {
            if (matrix[rowIndex][colIndex] == target) {
                return true;
            } else if (matrix[rowIndex][colIndex] > target) {
                colIndex--;
            } else rowIndex++;
        }

        return false;
    }
}
