package laioffer.BFSII;

import java.util.*;

public class PlaceToPutTheChairI {

    public static void main(String[] args) {
        char[][] gym = {{'C', 'C', 'E', 'O', 'C'}, {'C', 'C', 'O', 'C', 'E'}, {'C', 'C', 'E', 'E', 'C'}, {'C', 'O', 'C', 'E', 'E'}, {'C', 'C', 'O', 'C', 'C'}};
        System.out.println(new PlaceToPutTheChairI().putChair(gym));
    }

    // input:  char[][] gym
    // output: List<Integer>
    // Assume: gym != null && gym.length > 0 && gym[0].length > 0
    //         gym内至少有一个E

    // high level: 使用BFS1解答
    // detail level: 先找到E所在的坐标，从E开始generation每个非E的坐标，如果碰到O所在的坐标，那么不generation
    //               将每次计算的结果累加在一个matrix中，最后遍历这个matrix得到一个最小值
    //  1、initial state: for E in List<E> (x, y)
    //  2、generation rule: expand (x, y) generation (x + 1, y) (x - 1, y) (x, y + 1) (x, y - 1)
    //  3、terminated condition: queue is empty

    // 寻找所有的E,假设E的个数为k个  MN
    // heap最多装M*N个元素，每次BFS1 MN + MN * 4
    // 遍历matrix，选出最小的坐标 MN
    // time = MN + 5 * MN * k + MN = O(kMN)

    /**
     * input:  char[][] gym
     * output: List<Integer>
     * Assume: gym != null && gym.length > 0 && gym[0].length > 0
     *         gym内至少有一个E
     *
     * high level: 使用BFS1解答
     * detail level: 先找到E所在的坐标，从E开始generation每个非E的坐标，如果碰到O所在的坐标，那么不generation
     *               将每次计算的结果累加在一个matrix中，最后遍历这个matrix得到一个最小值
     *  1、initial state: for E in List<E> (x, y)
     *  2、generation rule: expand (x, y) generation (x + 1, y) (x - 1, y) (x, y + 1) (x, y - 1)
     *  3、terminated condition: queue is empty
     *
     * 寻找所有的E,假设E的个数为k个  MN
     * heap最多装M*N个元素，每次BFS1 MN + MN * 4
     * 遍历matrix，选出最小的坐标 MN
     * time = MN + 5 * MN * k + MN = O(kMN)
     */
    public List<Integer> putChair(char[][] gym) {
        // Write your solution here
        List<Integer> res = Arrays.asList(-1, -1);
        if (gym == null || gym.length == 0 || gym[0].length == 0) {
            return res;
        }

        int[][] canMoves = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] cost = new int[gym.length][gym[0].length];
        for (int i = 0; i < gym.length; i++) {
            for (int j = 0; j < gym[0].length; j++) {
                if (gym[i][j] == 'E') {
                    if (!BFS(gym, i, j, cost, canMoves)) {
                        return res;
                    }
                }
            }
        }

        for (int i = 0; i < gym.length; i++) {
            for (int j = 0; j < gym[0].length; j++) {
                if (gym[i][j] != 'O' && gym[i][j] != 'E') {
                    if (res.get(0) == -1 || res.get(1) == -1 || cost[i][j] < cost[res.get(0)][res.get(1)]) {
                        res.set(0, i);
                        res.set(1, j);
                    }
                }
            }
        }

        return res;
    }

    private boolean BFS(char[][] gym, int row, int col, int[][] cost, int[][] canMoves) {
        boolean[][] visited = new boolean[gym.length][gym[0].length];
        Queue<Cell> queue = new ArrayDeque<>();
        queue.offer(new Cell(row, col));
        visited[row][col] = true;
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Cell cur = queue.poll();
                for (Cell nei : getNeighbors(gym, cur, canMoves)) {
                    if (!visited[nei.x][nei.y]) {
                        queue.offer(nei);
                        visited[nei.x][nei.y] = true;
                        cost[nei.x][nei.y] += level + 1;
                    }
                }
            }

            level++;
        }

        for (int i = 0; i < gym.length; i++) {
            for (int j = 0; j < gym[0].length; j++) {
                if (gym[i][j] == 'E' && !visited[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private List<Cell> getNeighbors(char[][] gym, Cell cell, int[][] canMoves) {
        List<Cell> neighbors = new ArrayList<>();

        int rows = gym.length;
        int cols = gym[0].length;

        int x = cell.x;
        int y = cell.y;

        for (int[] canMove : canMoves) {
            int nx = x + canMove[0];
            int ny = y + canMove[1];

            if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && gym[nx][ny] != 'O') {
                neighbors.add(new Cell(nx, ny));
            }
        }

        return neighbors;
    }

    class Cell {
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
