package laioffer.bfs;

import java.util.*;

public class Bipartite {

    public static void main(String[] args) {
        GraphNode graphNode0 = new GraphNode(0);
        GraphNode graphNode1 = new GraphNode(1);
        GraphNode graphNode2 = new GraphNode(2);
        GraphNode graphNode3 = new GraphNode(3);
        GraphNode graphNode4 = new GraphNode(4);
        GraphNode graphNode5 = new GraphNode(5);
        GraphNode graphNode6 = new GraphNode(6);
        graphNode0.neighbors = new ArrayList<GraphNode>() {{
            add(graphNode1);
        }};
        graphNode1.neighbors = new ArrayList<GraphNode>() {{
            add(graphNode2);
        }};
        graphNode2.neighbors = new ArrayList<GraphNode>() {{
            add(graphNode3);
        }};
//        graphNode3.neighbors = new ArrayList<GraphNode>();
        graphNode4.neighbors = new ArrayList<GraphNode>() {{
            add(graphNode5);
            add(graphNode6);
        }};
        graphNode5.neighbors = new ArrayList<GraphNode>() {{
            add(graphNode4);
            add(graphNode6);
        }};
        graphNode6.neighbors = new ArrayList<GraphNode>() {{
            add(graphNode4);
            add(graphNode5);
        }};

        System.out.println(new Bipartite().isBipartite((new ArrayList<GraphNode>() {{
            add(graphNode0);
            add(graphNode1);
            add(graphNode2);
            add(graphNode3);
            add(graphNode4);
            add(graphNode5);
            add(graphNode6);
        }})));
    }

    public boolean isBipartite(List<GraphNode> graph) {
        // write your solution here
        if (graph == null || graph.size() <= 1) {
            return true;
        }

        Map<GraphNode, Integer> visited = new HashMap<>();
        for (GraphNode graphNode : graph) {
            if (!BFS(graphNode, visited)) {
                return false;
            }
        }

        return true;
    }

    private boolean BFS(GraphNode graphNode, Map<GraphNode, Integer> visited) {
        if (visited.containsKey(graphNode)) {
            return true;
        }

        Queue<GraphNode> queue = new ArrayDeque<>();
        queue.offer(graphNode);
        visited.put(graphNode, 0);

        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            int group = visited.get(cur);
            for (GraphNode neighbor : cur.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    visited.put(neighbor, 1 - group);
                } else if (visited.get(neighbor) == group) {
                    return false;
                }
            }
        }

        return true;
    }
}
