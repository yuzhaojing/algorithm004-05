package laioffer.CrossTrainingII;

import java.util.HashMap;
import java.util.Map;

class RandomListNode {
    public int value;
    public RandomListNode next;
    public RandomListNode random;
    public RandomListNode(int value) {
        this.value = value;
    }
}

public class DeepCopyLinkedListWithRandomPointer {

    public static void main(String[] args) {

    }

    /**
     * input: head RandomListNode
     * output: RandomListNode (复制后的头节点)
     * 假设：head != null
     * 如果不符合假设，那么链表为空，则返回null
     *
     * high level: 可以使用DFS或者BFS1进行解答
     * mid level: 依次访问每个node，将copy的node放在hashMap中，后面再遇到同一个node的时候从haspMap中取，防止重复复制
     *
     * time = O(n)
     * space = O(n)
     */
    public RandomListNode copy(RandomListNode head) {
        // Write your solution here.
        if (head == null) {
            return null;
        }

        Map<RandomListNode, RandomListNode> oldToNew = new HashMap<>();
        RandomListNode cur = head;
        while (cur != null) {
            // 到map中找复制好的cur
            RandomListNode newCur = oldToNew.get(cur);
            if (newCur == null) {
                // 如果没有就copy一份，并放入map中
                newCur = new RandomListNode(cur.value);
                oldToNew.put(cur, newCur);
            }

            // 如果有next，copy next
            if (cur.next != null) {
                RandomListNode newNext = oldToNew.get(cur.next);
                if (newNext == null) {
                    newNext = new RandomListNode(cur.next.value);
                    oldToNew.put(cur.next, newNext);
                }
                newCur.next = newNext;
            }

            // 如果有random，copy random
            if (cur.random != null) {
                RandomListNode newRandom = oldToNew.get(cur.random);
                if (newRandom == null) {
                    newRandom = new RandomListNode(cur.random.value);
                    oldToNew.put(cur.random, newRandom);
                }
                newCur.random = newRandom;
            }

            cur = cur.next;
        }

        return oldToNew.get(head);
    }
}
