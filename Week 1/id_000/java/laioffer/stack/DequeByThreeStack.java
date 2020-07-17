package laioffer.stack;

import java.util.Deque;
import java.util.LinkedList;

public class DequeByThreeStack {

    public static void main(String[] args) {
        DequeByThreeStack solution = new DequeByThreeStack();
        solution.offerFirst(9);
        solution.offerLast(190);
        solution.offerLast(23);
        solution.offerLast(167);
        solution.offerLast(77);
        solution.offerFirst(93);
        System.out.println(solution.peekFirst());
    }

    /**
     * 使用三个stack实现一个deque
     * 1.定义三个stack
     *   left为从左边push或者pop的stack
     *   right为从右边push或者pop的stack
     *   buffer为做两个stack之间倒数据的缓存
     * 2.实现四个方法，分别为offerFirst、offerLast、pollFirst、pollLast
     *   offerFirst: 直接往left中push元素即可 time = O(1)
     *   offerLast: 直接往right中push元素即可 time = O(1)
     *   pollFirst:
     *     case1: left中有元素,直接pop即可 time = O(1)
     *     case2: left中没有元素，需要从right中倒一半的数据过来 time = O(1) (amortize time)
     *   pollFirst:
     *     case1: right中有元素,直接pop即可 time = O(1)
     *     case2: right中没有元素，需要从left中倒一半的数据过来 time = O(1) (amortize time)
     *
     * amortize 时间复杂度分析
     *
     * 以pollFirst的case2举例: (假设right中有n个元素，left没有元素)
     *  1.从right中取一半元素，放入buffer time = O(n / 2) + O(n / 2) = O(n)
     *  2.从right中取剩余一半元素，放入left time = O(n / 2) + O(n / 2) = O(n)
     *  3.从buffert中取全部元素，放入right time = O(n / 2) + O(n / 2) = O(n)
     *  4.从left中取第一个元素返回 time = O(1)
     *  5.接下来的n / 2 - 1个元素的poll操作都为O(1)
     *
     *  在这一次操作中，总共能确定操作时间复杂度为n / 2个元素
     *
     *  amortize time = (n + n + n + 1 + (n / 2 - 1) * 1) / (n / 2) = 3.5n / 0.5n = 7 = O(1)
     */

    LinkedList<Integer> left;
    LinkedList<Integer> right;
    LinkedList<Integer> buffer;

    public DequeByThreeStack() {
        // Write your solution here.
        left = new LinkedList<Integer>();
        right = new LinkedList<Integer>();
        buffer = new LinkedList<Integer>();
    }

    public void offerFirst(int element) {
        left.offer(element);
    }

    public void offerLast(int element) {
        right.offer(element);
    }

    public Integer pollFirst() {
        move(right, left);
        return left.isEmpty() ? null : left.pollLast();
    }

    public Integer pollLast() {
        move(left, right);
        return right.isEmpty() ? null : right.pollLast();
    }

    public Integer peekFirst() {
        move(right, left);
        return left.isEmpty() ? null : left.peekLast();
    }

    public Integer peekLast() {
        move(left, right);
        return right.isEmpty() ? null : right.peekLast();
    }

    public int size() {
        return left.size() + right.size();
    }

    public boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }

    private void move(Deque<Integer> src, Deque<Integer> dest) {
        if (!dest.isEmpty() || src.isEmpty()) {
            return;
        }

        int mid = src.size() / 2;
        for (int i = 0; i < mid; i++) {
            buffer.offer(src.pollLast());
        }

        while (!src.isEmpty()) {
            dest.offer(src.pollLast());
        }

        while (!buffer.isEmpty()) {
            src.offer(buffer.pollLast());
        }
    }
}
