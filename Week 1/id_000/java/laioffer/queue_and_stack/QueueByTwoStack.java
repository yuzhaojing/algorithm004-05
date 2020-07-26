package laioffer.queue_and_stack;

import java.util.LinkedList;

public class QueueByTwoStack {

    public static void main(String[] args) {
        QueueByTwoStack solution = new QueueByTwoStack();
        solution.offer(9);
        solution.offer(190);
        solution.offer(23);
        solution.offer(167);
        solution.offer(77);
        solution.offer(93);
        System.out.println(solution.peek());
    }

    /**
     * 使用两个LinkedList来模拟stack，实现queue的功能
     * 1、定义两个LinkedList，一个in作为写入stack，一个out作为输出stack
     * 2、写入时只往in里写入
     * 3、case1: out中还有元素，直接读取
     *    case2: out中没有元素，将in中的元素全部倒过来，然后读取
     *    case3: 都没有元素，返回null
     *
     * push:
     * 时间复杂度O(1)
     *
     * pop:
     * 时间复杂度O(1) (amortize time)
     * 分析过程:
     *  1.out里面还有元素 O(1)
     *  2.out里面没有元素 (假设in里面有n个元素)
     *    (1)从in里面pop出来，每个元素O(1) time = O(1 * n)
     *    (2)将元素push倒out里面去，每个元素O(1) time = O(1 * n)
     *    (3)将out中的第一个元素pop出来 time= O(1)
     *    (4)后续n - 1个元素pop出来 time = O(1 * n - 1)
     *  total = (n + n + 1 + n - 1) / n = 3n / n = 3 = O(1)
     */

    LinkedList<Integer> in;
    LinkedList<Integer> out;

    public QueueByTwoStack() {
        // Write your solution here.
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    public Integer poll() {
        move();
        return out.isEmpty() ? null : out.pop();
    }

    public void offer(int element) {
        in.push(element);
    }

    public Integer peek() {
        move();
        return out.isEmpty() ? null : out.peek();
    }

    public int size() {
        return in.size() + out.size();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

    private void move() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }
}
