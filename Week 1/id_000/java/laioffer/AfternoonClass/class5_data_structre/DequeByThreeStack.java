package laioffer.AfternoonClass.class5_data_structre;

import java.util.ArrayDeque;
import java.util.Deque;

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
     *
     * deque需要实现的核心功能有一下几个：
     *  offerFirst、offerLast、pollFirst、pollLast
     *
     * high level: 使用两个stack，分别对应first和last的操作。
     *             第三个stack用于处理需要poll的stack没有元素，需要从另一个stack取的缓存stack
     * detail level:
     *  1、建立两个stack，分别对应first和last的操作。第三个stack用于缓存
     *  2、offerFirst leftStack.offer()
     *     offerLast  rightStack.offer();
     *     pollFirst
     *      case1: leftStack is not empty, leftStack.poll()
     *      case2: else, move half element from rightStack
     *     pollLast
     *      case1: rightStack is not empty, rightStack.poll()
     *      case2: else, move half element from leftStack
     *
     * time:
     *  offerFirst O(1)
     *  offerLast  O(1)
     *  pollFirst
     *   case1: O(1)
     *   case2: amortize O(1)
     *          假设leftStack里面没有元素，rightStack里面有n个元素
     *          1、从rightStack poll出n/2个元素 n/2
     *          2、将这n/2个元素放进buffer stack n/2
     *          3、从rightStack poll出剩余n/2个元素 n/2
     *          4、将这n/2个元素放进left stack n/2
     *          5、从buffer stack poll出n/2个元素 n/2
     *          6、将这n/2个元素放进right stack n/2
     *          7、不管后面怎么操作，至少n/2个操作都是O(1)的，总共 n/2
     *          所有amortize time = ((n/2 * 6) + (1 * n/2)) / n/2 = 7 = O(1)
     *  pollLast同理
     *
     * space: O(n)
     */

    private Deque<Integer> left;
    private Deque<Integer> right;
    private Deque<Integer> buffer;

    public DequeByThreeStack() {
        // Write your solution here.
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
        buffer = new ArrayDeque<>();
    }

    public void offerFirst(int element) {
        left.offerFirst(element);
    }

    public void offerLast(int element) {
        right.offerFirst(element);
    }

    public Integer pollFirst() {
        move(right, left);
        return left.isEmpty() ? null : left.pollFirst();
    }

    public Integer pollLast() {
        move(left, right);
        return right.isEmpty() ? null : right.pollFirst();
    }

    public Integer peekFirst() {
        move(right, left);
        return left.isEmpty() ? null : left.peekFirst();
    }

    public Integer peekLast() {
        move(left, right);
        return right.isEmpty() ? null : right.peekFirst();
    }

    public int size() {
        return left.size() + right.size();
    }

    public boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }

    private void move(Deque<Integer> src, Deque<Integer> dest) {
        if (src.isEmpty() || !dest.isEmpty()) {
            return;
        }

        int size = src.size() / 2;
        for (int i = 0; i < size; i++) {
            buffer.offerFirst(src.pollFirst());
        }

        while (!src.isEmpty()) {
            dest.offerFirst(src.pollFirst());
        }

        while (!buffer.isEmpty()) {
            src.offerFirst(buffer.pollFirst());
        }
    }
}
