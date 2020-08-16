package laioffer.DynamicProgrammingII;

public class LargestSquareOf1s {

    public static void main(String[] args) {
        int[][] array = {{1, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}};
        System.out.println(new LargestSquareOf1s().largest(array));
    }

    /**
     * 使用二维DP来解答
     * base case:
     *  当matrix数组中为0时，二维DP数组中的对应坐标也为0
     *  因为由0构成右下角无法形成正方形
     *  if matrix[i][j] == 0    M[i][j] == 0
     *  当matrix数组中为1，并且i或j为0时，代表该坐标在二维数组中的最上面或者最左侧一排
     *  次数由该坐标无法构成边长大于1的正方形
     *  if matrix[i][j] == 1 && (i == 0 || j == 0)  M[i][j] == 1
     * induction rule:
     *  该坐标构成的最大边长正方形为他的左右左上三个坐标构成的最小边长正方形 + 1
     *  M[i][j] = 1 + Math.min(Math.min(M[i - 1][j], M[i][j - 1]), M[i - 1][j - 1])
     */
    public int largest(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // 物理意义: 由该坐标作为右下角时，具有的最大正方形边长
        int[][] M = new int[matrix.length][matrix[0].length];
        int globalMax = 0;

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (matrix[i][j] == 0) {
                    M[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    M[i][j] = 1;
                } else {
                    M[i][j] = 1 + Math.min(Math.min(M[i - 1][j], M[i][j - 1]), M[i - 1][j - 1]);
                }
                globalMax = Math.max(globalMax, M[i][j]);
            }
        }

        return globalMax;
    }
}
