package laioffer.CrossTrainingI;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseI {

    public static void main(String[] args) {
        int[][] matrix = {{-85, 56, 37, 48}, {-25, -78, -29, 62}, {18, -60, -74, -84}, {90, 44, 5, 1}};
        System.out.println(new SpiralOrderTraverseI().spiral(matrix));
    }

    /**
     * input: matrix int[][]
     * output: List<Integer> (返回按题意遍历的序列)
     * 假设：matrix != null && matrix.length > 0 && matrix[0].length > 0
     * 如果不符合假设条件，matrix内没有元素，则返回值为空list
     *
     * high level: 使用recursion解答
     * mid level: 使用每一次循环分为四个for遍历，分别以四个顶点作为起点进行遍历，终点为下个起点前一位
     *            进行完一次之后，将起点向内缩一圈，然后继续以上过程
     *
     * time = O(n^2)
     * space = O(n/2) = O(n)
     */
    public List<Integer> spiral(int[][] matrix) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }

        spiral(matrix, 0, matrix.length, res);
        return res;
    }

    private void spiral(int[][] matrix, int offset, int size, List<Integer> res) {
        // base case
        // 当size == 0的时候，代表没有元素了
        if (size == 0) {
            return;
        }

        // base case
        // 当size == 1的时候，说明只有一个元素了
        if (size == 1) {
            res.add(matrix[offset][offset]);
            return;
        }

        // 可以将左上角的坐标看成[offset][offset]
        // 所以该矩阵的四个顶点的坐标分别为
        // [offset][offset]                [offset][offset + size - 1]
        // [offset + size - 1][offset]     [offset + size - 1][offset + size - 1]

        // 12
        for (int i = 0; i < size - 1; i++) {
            // 添加最上方的元素，该元素横坐标不变，纵坐标随着i增大，但是最后一个元素不放进来
            res.add(matrix[offset][offset + i]);
        }

        // 36
        for (int i = 0; i < size - 1; i++) {
            // 添加最右方的元素，该元素纵坐标不变，横坐标随着i增大，但是最后一个元素不放进来
            res.add(matrix[offset + i][offset + size - 1]);
        }

        // 98
        for (int i = size - 1; i > 0; i--) {
            // 添加最下方的元素，该元素横坐标不变，纵坐标随着i减小，但是最后一个元素不放进来
            res.add(matrix[offset + size - 1][offset + i]);
        }

        // 74
        for (int i = size - 1; i > 0; i--) {
            // 添加最左方的元素，该元素纵坐标不变，横坐标随着i减小，但是最后一个元素不放进来
            res.add(matrix[offset + i][offset]);
        }

        spiral(matrix, offset + 1, size - 2, res);
    }
}
