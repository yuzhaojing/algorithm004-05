package laioffer.recursionII;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseII {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}};
        System.out.println(new SpiralOrderTraverseII().spiral(matrix));
    }

    public List<Integer> spiral(int[][] matrix) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }

        spiral(matrix, 0, matrix.length, matrix[0].length, res);
        return res;
    }

    private void spiral(int[][] matrix, int offset, int rowSize, int colSize, List<Integer> res) {
        if (rowSize == 0 || colSize == 0) {
            return;
        }

        if (rowSize == 1) {
            for (int i = 0; i < colSize; i++) {
                res.add(matrix[offset][offset + i]);
            }
            return;
        }

        if (colSize == 1) {
            for (int i = 0; i < rowSize; i++) {
                res.add(matrix[offset + i][offset + colSize - 1]);
            }
            return;
        }

        for (int i = 0; i < colSize - 1; i++) {
            res.add(matrix[offset][offset + i]);
        }

        for (int i = 0; i < rowSize - 1; i++) {
            res.add(matrix[offset + i][offset + colSize - 1]);
        }

        for (int i = colSize - 1; i > 0; i--) {
            res.add(matrix[offset + rowSize - 1][offset + i]);
        }

        for (int i = rowSize - 1; i > 0; i--) {
            res.add(matrix[offset + i][offset]);
        }

        spiral(matrix, offset + 1, rowSize - 2, colSize - 2, res);
    }
}
