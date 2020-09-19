package laioffer.CrossTrainingII;

import laioffer.BFSI.GraphNode;

import java.util.*;

public class DeepCopyUndirectedGraph {

    public static void main(String[] args) {

    }

    /**
     * input: graph List<GraphNode>
     * output: List<GraphNode> (复制后的graph)
     * 假设：graph != null && graph.size() > 0
     * 如果不符合假设，那么图为空，返回null
     *
     * high level: 可以使用DFS或者BFS1进行解答
     * mid level: 依次访问每个node，将copy的node放在hashMap中，后面再遇到同一个node的时候从haspMap中取，防止重复复制
     *  DFS
     *    1、将当前node放入map
     *    2、将不在map中的neighbor都进行recursion
     *    3、将这些neighbor放入复制好的当前node上
     *    4、返回复制好的当前node
     *  BFS
     *    1、将root复制好，并放入queue中，queue中放置的全部都是已经copy好的node
     *    2、如果queue中还有node，就一直进行循环。每次取出一个node，并将他的neighbors全部copy好之后放入queue
     *
     * time = O(v + e)
     * space = O(v + e)
     */
    public List<GraphNode> copyDFS(List<GraphNode> graph) {
        // Write your solution here.
        if (graph == null || graph.size() == 0) {
            return graph;
        }

        Map<GraphNode, GraphNode> oldToNew = new HashMap<>();
        List<GraphNode> res = new ArrayList<>();
        for (GraphNode graphNode : graph) {
            res.add(DFS(graphNode, oldToNew));
        }
        return res;
    }

    private GraphNode DFS(GraphNode graphNode, Map<GraphNode, GraphNode> oldToNew) {
        GraphNode newCur = new GraphNode(graphNode.key);
        oldToNew.put(graphNode, newCur);
        for (GraphNode neighbor : graphNode.neighbors) {
            if (!oldToNew.containsKey(neighbor)) {
                DFS(neighbor, oldToNew);
            }
            newCur.neighbors.add(neighbor);
        }
        return newCur;
    }

    public List<GraphNode> copyBFS(List<GraphNode> graph) {
        // Write your solution here.
        List<GraphNode> res = new ArrayList<>();
        if (graph == null || graph.size() == 0) {
            return res;
        }

        Map<GraphNode, GraphNode> oldToNew = new HashMap<>();
        Queue<GraphNode> queue = new ArrayDeque<>();

        for (GraphNode graphNode : graph) {
            oldToNew.put(graphNode, new GraphNode(graphNode.key));
            queue.offer(graphNode);

            while (!queue.isEmpty()) {
                GraphNode cur = queue.poll();
                GraphNode newCur = oldToNew.get(cur);
                for (GraphNode nei : cur.neighbors) {
                    GraphNode newNei = oldToNew.get(nei);
                    if (newNei == null) {
                        newNei = new GraphNode(nei.key);
                        oldToNew.put(nei, newNei);
                        queue.offer(nei);
                    }
                    newCur.neighbors.add(newNei);
                }
            }

            res.add(oldToNew.get(graphNode));
        }

        return res;
    }
}
