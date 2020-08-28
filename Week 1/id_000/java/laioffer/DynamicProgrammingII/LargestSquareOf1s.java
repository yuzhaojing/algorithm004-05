package laioffer.DynamicProgrammingII;

public class LargestSquareOf1s {

    public static void main(String[] args) {
        int[][] array = {{1, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}};
        System.out.println(new LargestSquareOf1s().largest(array));
    }

    /**
     * 假设：matrix != null && matrix.length > 0 && matrix[0].length > 0
     * 如果不符合假设，那么matrix内没有元素，也就不存在1来构成正方形，返回0
     *
     * high level: 使用二维DP进行解答
     * mid level: 选取任意一个1作为正方形的右下角，以它构成的正方形的边长取决于它的左、上、左上三个1作为正方形的边长的最小
     *  1、M[i][j]表示以这个坐标作为正方形的右下角，所构成正方形的最大边长
     *  2、base case: if (matrix[i][j] == 1) M[i][0] = 1, M[0][j] = 1
     *  3、induction rule: if (matrix[i][j] == 1) M[i][j] = Min(M[i - 1][j - 1], M[i - 1][j], M[i][j - 1]) + 1
     *
     * time = O(n^2)
     * space = O(n^2)
     */
    public int largest(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] M = new int[rows][cols];
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        M[i][j] = 1;
                    } else {
                        M[i][j] = 1 + Math.min(M[i - 1][j - 1], Math.min(M[i - 1][j], M[i][j - 1]));
                    }

                    result = Math.max(result, M[i][j]);
                }
            }
        }

        return result;
    }
}
