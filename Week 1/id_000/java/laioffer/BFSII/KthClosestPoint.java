package laioffer.BFSII;

import java.util.*;

public class KthClosestPoint {

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = {2, 4};
        int[] c = {1, 2};
        System.out.println(new KthClosestPoint().closest(a, b, c, 10));
    }

    /**
     * input:  int[] a
     *         int[] b
     *         int[] c
     * output: List<Integer> (顺序按照a、b、c来一次放入)
     * Assume: a、b、c三个数组不为空，并且为升序
     *
     * high level: 使用BFS2解答
     * detail level:
     *  1、initial state: (a[0], b[0], c[0])
     *  2、generation rule:
     *     expand (a[i], b[j], c[k]) generation (a[i + 1], b[j], c[k]) (a[i], b[j + 1], c[k]) (a[i], b[j], c[k + 1])
     *  3、temination condition: pop k-th element
     *
     * time = O(klogk)
     * space = O(k)
     */
    public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (a == null || a.length == 0 || b == null || b.length == 0 || c == null || c.length == 0) {
            return res;
        }

        PriorityQueue<Cell> minHeap = new PriorityQueue<>(
                new Comparator<Cell>() {
                    @Override
                    public int compare(Cell c1, Cell c2) {
                        if (c1.distance == c2.distance) {
                            return 0;
                        }
                        return c1.distance < c2.distance ? -1 : 1;
                    }
                }
        );
        Set<String> visited = new HashSet<>();
        visited.add(0 + "#" + 0 + "#" + 0);
        minHeap.offer(new Cell(0, 0, 0, getDistance(a[0], b[0], c[0])));

        while (k > 1) {
            Cell cur = minHeap.poll();
            if (cur.i < a.length - 1 && visited.add((cur.i + 1) + "#" + cur.j + "#" + cur.k)) {
                minHeap.offer(new Cell(cur.i + 1, cur.j, cur.k, getDistance(a[cur.i + 1], b[cur.j], c[cur.k])));
            }
            if (cur.j < b.length - 1 && visited.add(cur.i + "#" + (cur.j + 1) + "#" + cur.k)) {
                minHeap.offer(new Cell(cur.i, cur.j + 1, cur.k, getDistance(a[cur.i], b[cur.j + 1], c[cur.k])));
            }
            if (cur.k < c.length - 1 && visited.add(cur.i + "#" + cur.j + "#" + (cur.k + 1))) {
                minHeap.offer(new Cell(cur.i, cur.j, cur.k + 1, getDistance(a[cur.i], b[cur.j], c[cur.k + 1])));
            }
            k--;
        }
        Cell cell = minHeap.poll();
        res.add(a[cell.i]);
        res.add(b[cell.j]);
        res.add(c[cell.k]);

        return res;
    }

    private double getDistance(int x, int y, int z) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    class Cell {
        int i;
        int j;
        int k;
        double distance;

        public Cell(int i, int j, int k, double distance) {
            this.i = i;
            this.j = j;
            this.k = k;
            this.distance = distance;
        }
    }
}
