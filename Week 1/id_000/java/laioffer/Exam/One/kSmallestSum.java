package laioffer.Exam.One;


import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class kSmallestSum {

    public static void main(String[] args) {
        int[] one = {1, 3, 5};
        int[] two = {4, 8};
        System.out.println(new kSmallestSum().kSmallestSum1(one, two, 6));
    }

    /**
     * 考试写出的方法，使用小顶堆存储元素，最后取第k个
     * <p>
     * time = O(n * m * lognm + (k - 1)lognm) 非常昂贵，不能使用
     * space = O(nm) 非常昂贵，不能使用
     */
    public int kSmallestSum(int[] one, int[] two, int k) {
        if (one == null || one.length == 0) {
            if (two != null && two.length >= k) {
                return two[k - 1];
            } else {
                return -1;
            }
        } else if (two == null || two.length == 0) {
            if (one.length >= k) {
                return one[k - 1];
            } else {
                return -1;
            }
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < one.length; i++) {
            for (int j = 0; j < two.length; j++) {
                minHeap.offer(one[i] + two[j]);
            }
        }

        for (int x = 0; x < k - 1; x++) {
            minHeap.poll();
        }

        return minHeap.peek();
    }

    /**
     * 正确的解题方式，参照KthSmallestSortedMatrix的做法
     * 只是输入的是两个一维数组，在逻辑上通过加法组成二维数组
     *
     * time = O(klogk)
     *
     * space = O(k)
     */
    public int kSmallestSum1(int[] one, int[] two, int k) {
        if (one == null || one.length == 0) {
            if (two != null && two.length >= k) {
                return two[k - 1];
            } else {
                return -1;
            }
        } else if (two == null || two.length == 0) {
            if (one.length >= k) {
                return one[k - 1];
            } else {
                return -1;
            }
        }

        int rows = one.length;
        int cols = two.length;

        if (k > rows * cols) {
            return -1;
        }

        PriorityQueue<Cell> minHeap = new PriorityQueue<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                if (o1.value == o2.value) {
                    return 0;
                }
                return o1.value < o2.value ? -1 : 1;
            }
        });
        HashSet<String> visited = new HashSet<>();

        minHeap.offer(new Cell(0, 0, one[0] + two[0]));
        visited.add(0 + "#" + 0);

        for (int i = 0; i < k - 1; i++) {
            Cell cur = minHeap.poll();
            if (cur.row + 1 < rows && !visited.contains((cur.row + 1) + "#" + cur.col)) {
                minHeap.offer(new Cell(cur.row + 1, cur.col, one[cur.row + 1] + two[cur.col]));
                visited.add((cur.row + 1) + "#" + cur.col);
            }

            if (cur.col + 1 < cols && !visited.contains(cur.row + "#" + (cur.col + 1))) {
                minHeap.offer(new Cell(cur.row, cur.col + 1, one[cur.row] + two[cur.col + 1]));
                visited.add(cur.row + "#" + (cur.col + 1));
            }
        }

        return minHeap.peek().value;
    }
}

class Cell {
    public int row;
    public int col;
    public int value;

    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
}
