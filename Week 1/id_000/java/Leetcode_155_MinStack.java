import java.util.Arrays;
import java.util.Stack;

/**
 * 1、两数之和
 */
public class Leetcode_155_MinStack {

    public static void main(String[] args) {
        Leetcode_155_MinStack stack = new Leetcode_155_MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
    }

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public Leetcode_155_MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        if (!minStack.isEmpty() && minStack.peek() < x) {
            minStack.push(minStack.peek());
        } else {
            minStack.push(x);
        }

        stack.push(x);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
