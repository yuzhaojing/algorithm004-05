package laioffer.recursionII;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseII {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}};
        System.out.println(new SpiralOrderTraverseII().spiral(matrix));
    }

    /**
     * 递归
     * 这是一个尾递归，所有的尾递归都可以转换为迭代
     *
     * time = O(n * m)
     * 每一个节点都遍历一遍，并以O(1)的时间复杂度加入list中
     *
     * space = O(Max(n, m))
     */
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
                res.add(matrix[offset + i][offset]);
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
