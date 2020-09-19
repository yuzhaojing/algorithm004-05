package laioffer.CrossTrainingIII;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleOf1s {

    public static void main(String[] args) {
    }

    /**
     * input: int[][] matrix
     * output: int
     * Assume: matrix != null && matrix.length > 0 && matrix[0].length > 0
     * 如果不符合假设，matrix内没有元素，返回0
     *
     * high level: 使用连续最长1和直方图最大矩形结合解答
     * detail level:
     *  1、遍历二维数组，用一个数组记录连续最长1
     *  2、然后对每层记录的连续最长1，调用直方图最大矩形的算法
     *
     * time: N * 2M = O(NM)
     * space: O(n)
     */
    public int largest(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int result = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] M = new int[cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                M[j] = matrix[i][j] == 0 ? 0 : M[j] + 1;
            }

            result = Math.max(result, helper(M));
        }

        return result;
    }

    private int helper(int[] array) {
        int maxArena = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerLast(-1);

        for (int i = 0; i < array.length; i++) {
            while (stack.peekLast() != -1 && array[stack.peekLast()] >= array[i]) {
                int cur = stack.pollLast();
                maxArena = Math.max(maxArena, array[cur] * (i - stack.peekLast() - 1));
            }
            stack.offerLast(i);
        }

        while (stack.peekLast() != -1) {
            int cur = stack.pollLast();
            maxArena = Math.max(maxArena, array[cur] * (array.length - stack.peekLast() - 1));
        }

        return maxArena;
    }
}
