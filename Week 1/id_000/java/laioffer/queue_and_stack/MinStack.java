package laioffer.queue_and_stack;

import java.util.LinkedList;

public class MinStack {

    public static void main(String[] args) {
        MinStack solution = new MinStack();
        solution.push(6);
        System.out.println(solution.min());
        solution.push(5);
        solution.push(9);
        System.out.println(solution.top());
        System.out.println(solution.min());
        System.out.println(solution.pop());
        System.out.println(solution.min());
        System.out.println(solution.top());
        System.out.println(solution.min());
        System.out.println(solution.pop());
        System.out.println(solution.min());
    }

    /**
     * 用两个stack来实现一个功能，可以在O(1)的时间内返回最小值
     * 设计为一个stack为input，用来存放push进来的元素
     * 另一个stack为min，该stack为一个单调栈，用来存最小值以及第一次出现的位置
     * min stack内存储一个长度为2的数组，第一个元素为当前最小值，第二个元素为该最小值第一次出现的位置
     * 每次push一个元素，将其和min stack最上方的第一个元素进行比较，小于则存，大于等于则不存
     * 每次删除一个元素，将stack的size和min stack最上方的第二个元素比较，如果小于则删除min stack最上方数组
     *
     * push:
     * 时间复杂度O(1)
     *
     * pop:
     * 时间复杂度O(1)
     */

    LinkedList<Integer> input;
    LinkedList<int[]> min;

    public MinStack() {
        // write your solution here
        input = new LinkedList<>();
        min = new LinkedList<>();
    }

    public int pop() {
        if (input.isEmpty()) {
            return -1;
        } else {
            int element = input.pop();
            if (!min.isEmpty() && input.size() < min.peek()[1]) {
                min.pop();
            }
            return element;
        }
    }

    public void push(int element) {
        input.push(element);
        if (min.isEmpty() || min.peek()[0] > element) {
            int[] arr = {element, input.size()};
            min.push(arr);
        }
    }

    public int top() {
        if (input.isEmpty()) {
            return -1;
        }
        return input.peek();
    }

    public int min() {
        if (min.isEmpty()) {
            return -1;
        }
        return min.peek()[0];
    }
}
