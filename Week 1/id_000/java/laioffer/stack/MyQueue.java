package laioffer.stack;

import java.util.Stack;

public class MyQueue {

    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    public MyQueue() {
        this.stackIn = new Stack<>();
        this.stackOut = new Stack<>();
    }

    /**
     * 时间复杂度O(1)
     * 只需要一直往stackIn里面push就行了
     */
    public void push(int x) {
        stackIn.push(x);
    }

    /**
     * 时间复杂度O(1)
     * 计算过程:
     * 第一个pop的元素，需要将所有的元素从stackIn中取出来
     * 然后push进stackOut里面去，然后再从stackOut里面pop出来
     * 假设stackIn里面有n个元素，耗时为2n + 1
     * 但是后面n - 1个元素每个耗时为1
     * 所以时间复杂度 = (2n + 1 + (n - 1)) / n = O(3) = O(1)
     */
    public int pop() {
        if (stackOut.empty()) {
            while (!stackIn.empty()) {
                stackOut.push(stackIn.pop());
            }
        }

        return stackOut.pop();
    }

    /**
     * 时间复杂度O(1),理由同上
     */
    public int peek() {
        if (stackOut.empty()) {
            while (!stackIn.empty()) {
                stackOut.push(stackIn.pop());
            }
        }

        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.empty() && stackOut.empty();
    }
}
