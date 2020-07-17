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
        graphNode0.neighbors = new ArrayList<GraphNode>() {{ add(graphNode1);}};
        graphNode1.neighbors = new ArrayList<GraphNode>() {{ add(graphNode2);}};
        graphNode2.neighbors = new ArrayList<GraphNode>() {{ add(graphNode3);}};
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

        System.out.println(isBipartite(new ArrayList<GraphNode>() {{
            add(graphNode0);
            add(graphNode1);
            add(graphNode2);
            add(graphNode3);
            add(graphNode4);
            add(graphNode5);
            add(graphNode6);
        }}));
    }

    private static boolean isBipartite(List<GraphNode> graph) {
        // write your solution here
        HashMap<Integer, Integer> visited = new HashMap<>();
        Queue<GraphNode> queue = new LinkedList<>();

        for (GraphNode node : graph) {
            if (!visited.containsKey(node.key)) {
                queue.offer(node);
                visited.put(node.key, 0);
            }

            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    GraphNode graphNode = queue.remove();
                    Integer group = visited.get(graphNode.key);
                    for (int j = 0; j < graphNode.neighbors.size(); j++) {
                        int key = graphNode.neighbors.get(j).key;
                        if (!visited.containsKey(graphNode.neighbors.get(j).key)) {
                            visited.put(key, 1 - group);
                            queue.offer(graphNode.neighbors.get(j));
                        } else if (visited.get(key).intValue() == group) {
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }
}
