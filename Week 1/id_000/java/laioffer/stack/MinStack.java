package laioffer.stack;

import java.util.Stack;

class Struct {
    int value;
    int count;

    public Struct(int v, int c) {
        this.value = v;
        this.count = c;
    }
}

public class MinStack {

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }

    private Stack<Integer> stack;
    private Stack<Struct> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * 时间复杂度O(1)
     * 空间复杂度O(n)
     */
    /*public void push(int x) {
        if (!stack.empty() && minStack.peek() < x) {
            minStack.push(minStack.peek());
        } else {
            minStack.push(x);
        }

        stack.push(x);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }*/

    /**
     * 优化空间复杂度O(1)
     */
    public void push(int x) {
        if (!stack.empty()) {
            if (minStack.peek().value > x) {
                minStack.push(new Struct(x, 1));
            } else {
                Struct minStruct = minStack.pop();
                minStruct.count += 1;
                minStack.push(minStruct);
            }
        } else {
            minStack.push(new Struct(x, 1));
        }

        stack.push(x);
    }

    public void pop() {
        stack.pop();
        Struct minStruct = minStack.pop();
        if (minStruct.count != 1) {
            minStruct.count -= 1;
            minStack.push(minStruct);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek().value;
    }
}
