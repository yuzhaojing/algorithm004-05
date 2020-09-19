package laioffer.BFSI;

import java.util.*;

public class KthSmallestSortedMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {2, 4, 8, 9}, {3, 5, 11, 15}, {6, 8, 13, 18}};
        System.out.println(new KthSmallestSortedMatrix().kthSmallest(matrix, 14));
    }

    /**
     * time = O(klogk)
     * 分析过程:
     * 1.总共执行了k - 1次循环，因为每次循环取出一个元素，最多放入两个元素，
     *   所以p-queue里面最多有k个元素
     * 2.每次循环中
     *   取出一个元素 time = O(logk)
     *   最多放入两个元素 time = O(2logk)
     *   time = O(logk) + O(2logk) = O(3logk)
     * total time = O((k - 1) * 3logk) = O(klogk)
     *
     * space = O(k)
     * 分析过程:
     * 1.总共执行了k - 1次循环，因为每次循环取出一个元素，最多放入两个元素，
     *   所以p-queue里面最多有k个元素 space = O(k)
     * 2.声明了一个hashset用于去重，每次循环最多放入两个元素，循环k - 1次
     *   space = O((k - 1) * 2) = O(k)
     * total space = O(k) + O(k) = O(2k) = O(k)
     */
    public int kthSmallest(int[][] matrix, int k) {
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
