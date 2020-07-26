package laioffer.bfs;

import laioffer.tree.TreeNode;

import java.util.*;

public class KthSmallestSortedMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {2, 4, 8, 9}, {3, 5, 11, 15}, {6, 8, 13, 18}};
        System.out.println(new KthSmallestSortedMatrix().kthSmallest1(matrix, 14));
    }

    /**
     * 1 3 5  7
     * 2 4 8  9
     * 3 5 11 15
     * 6 8 13 18
     */
    public int kthSmallest(int[][] matrix, int k) {
        // Write your solution here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k <= 0) {
            return -1;
        }

        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        // int[0] = row
        // int[1] = col
        // int[2] = cost
        Queue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        minHeap.offer(new int[]{0, 0, matrix[0][0]});
        HashSet<String> set = new HashSet<>();
        set.add(0 + "#" + 0);

        for (int i = 0; i < k - 1; i++) {
            int[] cur = minHeap.poll();
            int row = cur[0];
            int col = cur[1];
            int value = cur[2];

            if (row + 1 < rowLen && !set.contains((row + 1) + "#" + col)) {
                minHeap.offer(new int[]{row + 1, col, matrix[row + 1][col]});
                set.add((row + 1) + "#" + col);
            }

            if (col + 1 < colLen && !set.contains(row + "#" + (col + 1))) {
                minHeap.offer(new int[]{row, col + 1, matrix[row][col + 1]});
                set.add(row + "#" + (col + 1));
            }
        }

        return minHeap.peek()[2];
    }

    public int kthSmallest1(int[][] matrix, int k) {
        // Write your solution here
        // base case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k <= 0) {
            return -1;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        if (k > rows * cols) {
            return -1;
        }

        // 建立小顶堆，将value最小的元素放在堆顶
        Queue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });

        // 建立hashset，用于去重可能重复放入queue中的元素
        HashSet<String> visited = new HashSet<>();

        minHeap.offer(new Node(0, 0, matrix[0][0]));
        visited.add(0 + "#" + 0);

        // 取第k小的元素，先循环k - 1次，最后再将堆顶元素返回
        for (int i = 0; i < k - 1; i++) {
            Node curNode = minHeap.poll();

            // 下方还有元素，并且没有被访问过
            // 将元素加入minHeap中，并且加入visited中，表示已经访问过
            if (curNode.row + 1 < rows && !visited.contains((curNode.row + 1) + "#" + curNode.col)) {
                minHeap.offer(new Node(curNode.row + 1, curNode.col, matrix[curNode.row + 1][curNode.col]));
                visited.add((curNode.row + 1) + "#" + curNode.col);
            }

            // 右方还有元素，并且没有被访问过
            // 将元素加入minHeap中，并且加入visited中，表示已经访问过
            if (curNode.col + 1 < cols && !visited.contains(curNode.row + "#" + (curNode.col + 1))) {
                minHeap.offer(new Node(curNode.row, curNode.col + 1, matrix[curNode.row][curNode.col + 1]));
                visited.add(curNode.row + "#" + (curNode.col + 1));
            }
        }

        // k - 1次循环结束，这次是第k次取出数据，所以是第k小的元素
        return minHeap.peek().value;
    }
}

class Node {
    int row;
    int col;
    int value;

    public Node(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
}
