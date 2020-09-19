package laioffer.CrossTrainingI;

public class RotateMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{80, 28, 59, 98, 88, 21, 85}, {54, 6, 63, 65, 75, 85, 44}, {10, 64, 26, 54, 81, 37, 35}, {63, 83, 55, 8, 21, 44, 3}, {83, 94, 39, 70, 79, 99, 86}, {69, 47, 57, 10, 14, 75, 77}, {91, 43, 27, 69, 62, 96, 79}};
        new RotateMatrix().rotateIterator(matrix);
    }

    /**
     * expected [[80, 28, 59, 98, 88, 21, 85], [54, 6, 63, 65, 75, 85, 44], [10, 64, 26, 54, 81, 37, 35], [63, 83, 55, 8, 21, 44, 3], [83, 94, 39, 70, 79, 99, 86], [69, 47, 57, 10, 14, 75, 77], [91, 43, 27, 69, 62, 96, 79]]
     * but was: [[80, 28, 59, 98, 88, 21, 85], [54, 6, 63, 65, 75, 85, 44], [10, 64, 81, 21, 79, 37, 35], [63, 83, 54, 8, 70, 44, 3], [83, 94, 26, 55, 39, 99, 86], [69, 47, 57, 10, 14, 75, 77], [91, 43, 27, 69, 62, 96, 79]]
     */

    /**
     * input: matrix int[][]
     * output: none
     * 假设：matrix != null && matrix.length > 0 && matrix[0].length > 0
     * 如果不符合假设，那么matrix内没有元素，所以不需要处理
     *
     * high level: 使用剥洋葱的方法，一层一层遍历
     * mid level: 在遍历的时候，计算每个元素旋转后的位置，进行替换
     *  1、对应旋转90度之后的点(k, t), 与(i, j)有以下关系
     *     k = j (旋转90度之后，原先的纵坐标等于现在的横坐标)
     *     i + j = size - 1
     *  2、按旋转顺序，四个点的坐标依次为(i, j)、(j, size - 1 - i)、(size - 1 - i, size - 1 - j)、(size - 1 - j, i)
     *
     * time = O(N)
     * space = O(N/2) = O(N)
     */
    public void rotate(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        helper(matrix, 0, matrix.length);
    }

    private void helper(int[][] matrix, int offset, int size) {
        if (size <= 1) {
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            rotate(matrix, offset, offset + i);
        }

        helper(matrix, offset + 1, size - 2);
    }

    public void rotateIterator(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int size = matrix.length;

        for (int i = 0; i < matrix.length / 2; i++) {

            for (int j = 0; j < size - 1; j++) {
                rotate(matrix, i, i + j);
            }
            size -= 2;
        }
    }

    private void rotate(int[][] matrix, int row, int col) {
        int size = matrix.length;
        int temp = matrix[row][col];
        matrix[row][col] = matrix[size - 1 - col][row];
        matrix[size - 1 - col][row] = matrix[size - 1 - row][size - 1 - col];
        matrix[size - 1 - row][size - 1 - col] = matrix[col][size - 1 - row];
        matrix[col][size - 1 - row] = temp;
    }
}
