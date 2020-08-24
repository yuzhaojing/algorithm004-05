package laioffer.DynamicProgrammingIII;

public class LargestXOf1s {

    public static void main(String[] args) {

    }

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
                if (matrix[i][j] == 1) {
                    left[i][j] = getNumber(left, i - 1, j - 1) + 1;
                    up[i][j] = getNumber(up, i - 1, j + 1) + 1;
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
                if (matrix[i][j] == 1) {
                    right[i][j] = getNumber(right, i + 1, j + 1) + 1;
                    down[i][j] = getNumber(down, i + 1, j - 1) + 1;
                }
            }
        }

        merge(right, down, rows, cols);
        return right;
    }

    private int getNumber(int[][] input, int i, int j) {
        if (i < 0 || i >= input.length || j < 0 || j >= input[0].length) {
            return 0;
        }

        return input[i][j];
    }
}
