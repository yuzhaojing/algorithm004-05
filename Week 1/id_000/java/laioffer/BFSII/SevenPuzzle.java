package laioffer.BFSII;

import java.util.*;

public class SevenPuzzle {

    public static void main(String[] args) {
    }

    /**
     * input:  int[] values
     * output: int
     * Assume: values != null && value.length > 1
     *
     * high level: 使用BFS1解答
     * detail level: 需要使用一个hashMap存储所有的state和对应的步数
     *  1、initial state: 最终结果
     *  2、generation rule:
     *     expand values, generation 0交换位置之后的数组
     *  3、termination condition: 元素与给定的values位置相同
     *
     * time = V + E = 8! + 4 * 8! = O(8!)
     * 将其构建成一个图，最多有8!个vertex，每个vertex最多有四个edge
     * worst case每个vertex和edge访问一次 time = 8! + 4 * 8!
     *
     * space = 2V = 2 * 8! = O(8!)
     * queue最多存不超过vertex的个数的元素 O(8!)
     * hashMap最多存不超过vertex的个数的元素 O(8!)
     */
    public int numOfSteps(int[] values) {
        // Write your solution here
        if (values == null || values.length != 8) {
            return -1;
        }

        Map<State, Integer> stateToStep = new HashMap<>();
        Queue<State> queue = new ArrayDeque<>();
        int[][] canMoves = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        State start = new State(new int[] {0, 1, 2, 3, 4, 5, 6, 7});
        State target = new State(values);
        queue.offer(start);
        stateToStep.put(start, 0);

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            int step = stateToStep.get(cur);
            if (cur.equals(target)) {
                return step;
            }

            List<State> neighbors = getNeighbors(cur, canMoves);
            for (State nei : neighbors) {
                if (!stateToStep.containsKey(nei)) {
                    stateToStep.put(nei, step + 1);
                    queue.offer(nei);
                }
            }
        }

        return -1;
    }

    private List<State> getNeighbors(State state, int[][] canMoves) {
        List<State> neighbors = new ArrayList<>();

        int row = state.zeroPos / 4;
        int col = state.zeroPos % 4;

        for (int[] canMove : canMoves) {
            int x = row + canMove[0];
            int y = col + canMove[1];

            if (x >= 0 && x <= 1 && y >= 0 && y <= 3) {
                int[] values = state.values.clone();
                swap(values, state.zeroPos, x * 4 + y);
                neighbors.add(new State(values));
            }
        }

        return neighbors;
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    class State {
        int[] values;
        int hashCode = 0;
        int zeroPos = 0;

        public State(int[] values) {
            this.values = values;
            for (int i = 0; i < values.length; i++) {
                hashCode = hashCode * 10 + values[i];
                if (values[i] == 0) {
                    zeroPos = i;
                }
            }
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof State)) {
                return false;
            }

            State other = (State) obj;
            return this.hashCode == other.hashCode();
        }
    }
}
