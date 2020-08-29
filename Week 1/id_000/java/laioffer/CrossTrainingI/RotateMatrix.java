package laioffer.CrossTrainingI;

public class RotateMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{-85, 56, 37, 48}, {-25, -78, -29, 62}, {18, -60, -74, -84}, {90, 44, 5, 1}};
        new RotateMatrix().rotate(matrix);
    }

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
     * time = O(N^2)
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
            rotate(matrix, offset, offset + i, size);
        }

        helper(matrix, offset + 1, size - 2);
    }

    private void rotate(int[][] matrix, int row, int col, int size) {
        int temp = matrix[row][col];
        matrix[row][col] = matrix[size - 1 - col][row];
        matrix[size - 1 - col][row] = matrix[size - 1 - row][size - 1 - col];
        matrix[size - 1 - row][size - 1 - col] = matrix[col][size - 1 - row];
        matrix[col][size - 1 - row] = temp;
    }
}
