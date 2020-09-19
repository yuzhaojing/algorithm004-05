package laioffer.BFSII;

import java.util.*;

public class KthSmallest {

    public static void main(String[] args) {
        System.out.println(new KthSmallest().kth(40));
    }

    /**
     * input:  int k
     * output: long
     * Assume: k > 0
     * 如果k不符合假设，那么没有符合题意的解，返回-1L
     *
     * high level: 使用BFS2解答
     * detail level:
     *  1、initial state: (0, 0, 0)
     *  2、generation rule:
     *     expand (x, y, z) generation (x + 1, y, z) (x, y + 1, z) (x, y, z + 1)
     *  3、termination condition: pop k-th element
     *
     * p-queue 出一个，进三个，每次最多增加两个元素，执行k次，p-queue内最多有2k + 1个元素
     * 出来log(2k) 进去3log(2k) 每次操作4(log2k) 总共 4klog2k
     * time = O(klogk)
     * space = 2k + 3k = O(k)
     * p-queue最多需要存储2k + 1个元素，去重最多需要存储3k + 1个元素
     */
    public long kth(int k) {
        // Write your solution here
        if (k <= 0) {
            return -1L;
        }

        PriorityQueue<Cell> minHeap = new PriorityQueue<>(
                new Comparator<Cell>() {
                    @Override
                    public int compare(Cell c1, Cell c2) {
                        if (c1.value == c2.value) {
                            return 0;
                        }
                        return c1.value < c2.value ? -1 : 1;
                    }
                }
        );

        Set<String> visited = new HashSet<>();
        minHeap.offer(new Cell(1, 1, 1));
        visited.add(1 + "#" + 1 + "#" + 1);

        while (k > 1) {
            Cell cur = minHeap.poll();
            if (visited.add((cur.x + 1) + "#" + cur.y + "#" + cur.z)) {
                minHeap.offer(new Cell(cur.x + 1, cur.y, cur.z));
            }
            if (visited.add(cur.x + "#" + (cur.y + 1) + "#" + cur.z)) {
                minHeap.offer(new Cell(cur.x, cur.y + 1, cur.z));
            }
            if (visited.add(cur.x + "#" + cur.y + "#" + (cur.z + 1))) {
                minHeap.offer(new Cell(cur.x, cur.y, cur.z + 1));
            }
            k--;
        }

        return minHeap.poll().value;
    }

    class Cell {
        int x;
        int y;
        int z;
        long value;

        public Cell(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.value = (long) (Math.pow(3, x) * Math.pow(5, y) * Math.pow(7, z));
        }
    }
}
