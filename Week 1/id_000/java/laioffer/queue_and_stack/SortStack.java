package laioffer.queue_and_stack;

import java.util.LinkedList;

public class SortStack {

    public static void main(String[] args) {
        LinkedList<Integer> s1 = new LinkedList<>();
        s1.add(3);
        s1.add(6);
        s1.add(3);
        s1.add(1);
        s1.add(2);
        s1.add(5);

        new SortStack().sort(s1);

        while (!s1.isEmpty()) {
            System.out.println(s1.pollFirst());
        }
    }

    /**
     * 使用两个stack，排序输入的stack中的元素
     * 1.将s1中的元素一个一个输入s2，并记录最小值
     * 2.将s2中的大于等于最小值的元素取出，不等于最小值的元素一个一个输入s1，
     * 等于最小值的元素记录出现次数counter
     * 3.将最小值全部输入s2
     * 4.重复上面的过程
     *
     * 时间复杂度O(n^2) 模拟选择排序
     * 空间复杂度O(n)
     */

    public void sort(LinkedList<Integer> s1) {
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        // Write your solution here.
        if (s1 == null || s1.size() == 0) {
            return;
        }
        sort1(s1, s2);
    }

    private void sort(LinkedList<Integer> input, LinkedList<Integer> cache) {
        while (!input.isEmpty()) {
            int globalMin = Integer.MAX_VALUE;
            int counter = 0;

            while (!input.isEmpty()) {
                int element = input.pop();
                globalMin = Math.min(globalMin, element);
                cache.push(element);
            }

            while (!cache.isEmpty() && cache.peek() >= globalMin) {
                int element = cache.pop();
                if (element != globalMin) {
                    input.push(element);
                } else {
                    counter++;
                }
            }

            while (counter-- > 0) {
                cache.push(globalMin);
            }
        }

        while (!cache.isEmpty()) {
            input.push(cache.pop());
        }
    }

    private void sort1(LinkedList<Integer> input, LinkedList<Integer> buffer) {
        while (!input.isEmpty()) {
            int globalMin = Integer.MAX_VALUE;
            int counter = 0;

            while (!input.isEmpty()) {
                int element = input.poll();
                globalMin = Math.min(globalMin, element);
                buffer.offerFirst(element);
            }

            while (!buffer.isEmpty() && buffer.peek() >= globalMin) {
                int bufferElement = buffer.poll();
                if (bufferElement == globalMin) {
                    counter++;
                } else {
                    input.offerFirst(bufferElement);
                }
            }

            while (counter --> 0) {
                buffer.offerFirst(globalMin);
            }
        }

        while (!buffer.isEmpty()) {
            input.offerFirst(buffer.poll());
        }
    }
}
