package laioffer.queue_and_stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackByQueue {

    public static void main(String[] args) {
        StackByQueue solution = new StackByQueue();
        solution.push(9);
        solution.push(190);
        solution.push(23);
        solution.push(167);
        solution.push(77);
        solution.push(93);
        System.out.println(solution.top());
    }

    /**
     * 使用queue来模拟stack
     * queue是FIFO的数据结构，要实现stack的FILO的情况，
     * 1.每次push往queue中直接push
     * 2.每次pop则将queue的size记录一下取出size - 1个元素并重新加入queue，最后取出queue的元素
     *
     * push:
     * 时间复杂度 O(1)
     *
     * pop:
     * 时间复杂度 O(n)
     * 取出n - 1个元素，放入n - 1个元素，取出最后一个元素返回
     * time = n - 1 + n - 1 + 1 / n = 2n - 1 / n = O(n)
     */

    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public StackByQueue() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public Integer pop() {
        if (queue.isEmpty()) {
            return null;
        }

        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }

        return queue.poll();
    }

    /** Get the top element. */
    public Integer top() {
        if (queue.isEmpty()) {
            return null;
        }

        int element = pop();
        push(element);
        return element;
    }

    /** Returns whether the stack is empty. */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
