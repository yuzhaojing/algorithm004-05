package laioffer.CrossTrainingIII;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MaxWaterTrappedII {

    public static void main(String[] args) {
    }

    /**
     * input: int[][] matrix
     * output: int
     * Assume: matrix != null && matrix.length > 0 && matrix[0].length > 0
     * 如果不符合假设，没有元素返回0
     *
     * high level: 使用BFS2解答
     * detail level:
     *  1、将最外面一圈的数字看成墙，将他们放入一个min heap中
     *  2、每次拿出最小的数字，即最矮的墙
     *  3、将与当前数字相邻的未访问过的数字加入min heap，并记录可以装的水
     *
     * time: O(MN)
     * space: O(MN)
     */
    public int maxTrapped(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int sum = 0;

        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(
                new Comparator<Cell>() {
                    @Override
                    public int compare(Cell c1, Cell c2) {
                        if (c1.height == c2.height) {
                            return 0;
                        }

                        return c1.height < c2.height ? -1 : 1;
                    }
                }
        );
        for (int j = 0; j < cols; j++) {
            minHeap.offer(new Cell(0, j, matrix[0][j]));
            minHeap.offer(new Cell(rows - 1, j, matrix[rows - 1][j]));
            visited[0][j] = true;
            visited[rows - 1][j] = true;
        }

        for (int i = 1; i < rows - 1; i++) {
            minHeap.offer(new Cell(i, 0, matrix[i][0]));
            minHeap.offer(new Cell(i, cols - 1, matrix[i][cols - 1]));
            visited[i][0] = true;
            visited[i][cols - 1] = true;
        }

        while (!minHeap.isEmpty()) {
            Cell cur = minHeap.poll();
            List<Cell> neighbors = getNeighbors(matrix, cur);
            for (Cell neighbor : neighbors) {
                if (!visited[neighbor.row][neighbor.col]) {
                    sum += Math.max(0, cur.height - neighbor.height);
                    visited[neighbor.row][neighbor.col] = true;
                    // 取最高的墙，装水的上限取决于最高的墙
                    neighbor.height = Math.max(cur.height, neighbor.height);
                    minHeap.offer(neighbor);
                }
            }
        }

        return sum;
    }

    class Cell {
        int row;
        int col;
        int height;

        public Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.height = value;
        }
    }

    private List<Cell> getNeighbors(int[][] matrix, Cell cur) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<Cell> neighbors = new ArrayList<>();
        if (cur.row - 1 >= 0) {
            neighbors.add(new Cell(cur.row - 1, cur.col, matrix[cur.row - 1][cur.col]));
        }

        if (cur.row + 1 <= rows - 1) {
            neighbors.add(new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]));
        }

        if (cur.col - 1 >= 0) {
            neighbors.add(new Cell(cur.row, cur.col - 1, matrix[cur.row][cur.col - 1]));
        }

        if (cur.col + 1 <= cols - 1) {
            neighbors.add(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]));
        }

        return neighbors;
    }
}
