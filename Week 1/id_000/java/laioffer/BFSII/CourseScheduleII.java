package laioffer.BFSII;

import java.util.*;

public class CourseScheduleII {

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(new CourseScheduleII().findOrder(4, prerequisites)));
    }

    // input:  int numCourses
    //         int[][] prerequisites
    // output: int[]
    // Assume: numCourses > 1 && prerequisites.length > 0 && prerequisites[0].length > 0

    // high level: 使用DFS解答
    // detail level: 本质上就是拓扑排序，将prerequisites组装成为Graph，然后进行DFS

    //
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Write your solution here
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] dep : prerequisites) {
            graph.get(dep[1]).add(dep[0]);
        }

        return BFS(graph);
    }

    private int[] BFS(List<List<Integer>> graph) {
        int[] res = new int[graph.size()];
        int[] incomeEdges = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            for (int dep : graph.get(i)) {
                incomeEdges[dep]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < incomeEdges.length; i++) {
            if (incomeEdges[i] == 0) {
                queue.offer(i);
            }
        }

        int curIndex = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            res[curIndex++] = i;
            for (int dep : graph.get(i)) {
                if (--incomeEdges[dep] == 0) {
                    queue.offer(dep);
                }
            }
        }

        return curIndex == graph.size() ? res : new int[0];
    }
}
