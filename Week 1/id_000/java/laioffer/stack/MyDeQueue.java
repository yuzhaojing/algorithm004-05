package laioffer.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class MyDeQueue {

    /**
     * 1 2 3 4 5 | 6 7 8 9 10
     */

    public static void main(String[] args) {
        MyDeQueue deQueue = new MyDeQueue();
        deQueue.addFirst(5);
        deQueue.addFirst(4);
        deQueue.addFirst(3);
        deQueue.addFirst(2);
        deQueue.addFirst(1);

        deQueue.addLast(6);
        deQueue.addLast(7);
        deQueue.addLast(8);
        deQueue.addLast(9);
        deQueue.addLast(10);

        while (!deQueue.empty()) {
            System.out.println(deQueue.popLast());
        }
    }

    private Stack<Integer> stackLeft;
    private Stack<Integer> stackRight;
    private Stack<Integer> stackCache;

    public MyDeQueue() {
        this.stackLeft = new Stack<>();
        this.stackRight = new Stack<>();
        this.stackCache = new Stack<>();

        Deque<Object> deque = new ArrayDeque<>();
    }


    public void addFirst(int x) {
        stackLeft.push(x);
    }

    public void addLast(int x) {
        stackRight.push(x);
    }

    public int popFirst() {
        if (stackLeft.empty() && !stackRight.empty()) {
            if (stackRight.size() == 1) stackLeft.push(stackRight.pop());
            int mid = stackRight.size() / 2;
            while (stackRight.size() > mid) {
                stackCache.push(stackRight.pop());
            }

            while (!stackRight.empty()) {
                stackLeft.push(stackRight.pop());
            }

            while (!stackCache.empty()) {
                stackRight.push(stackCache.pop());
            }
        }

        return stackLeft.pop();
    }

    public int popLast() {
        if (stackRight.empty() && !stackLeft.empty()) {
            if (stackLeft.size() == 1) stackRight.push(stackLeft.pop());
            int mid = stackLeft.size() / 2;
            while (stackLeft.size() > mid) {
                stackCache.push(stackLeft.pop());
            }

            while (!stackLeft.empty()) {
                stackRight.push(stackLeft.pop());
            }

            while (!stackCache.empty()) {
                stackLeft.push(stackCache.pop());
            }
        }

        return stackRight.pop();
    }

//    public int peek() {
//
//    }
//
    public boolean empty() {
        return stackLeft.empty() && stackRight.empty();
    }
}
