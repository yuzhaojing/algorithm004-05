package laioffer.AfternoonClass.class4_RecursionI;

import java.util.ArrayDeque;
import java.util.Deque;
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
     * 使用两个stack排序元素
     *
     * input:  Deque<Integer> input
     * output: Deque<Integer>
     * Assume: input != null && input.size() > 1
     * 如果不满足假设，不需要对input进行操作，直接返回
     *
     * high level: 使用两个stack，一个用于存储待处理元素，一个用于存储结果并且缓存元素
     * detail level:
     *  1、将input中的元素依次放入output中，并且记录最小值和出现次数
     *  2、当input中为空的时候，将output中大于等于最小值的元素取出，并将大于最小值的元素放回input
     *  3、将所有最小值放入output中
     *  4、当input为空的时候，将output的元素全部导入input
     *
     * time = n^2 + n = O(n^2)
     * space = O(n)
     */
    public void sort(Deque<Integer> input) {
        if (input == null || input.size() < 2) {
            return;
        }

        Deque<Integer> output = new ArrayDeque<>();
        helper(input, output);
    }

    private void helper(Deque<Integer> input, Deque<Integer> output) {
        while (!input.isEmpty()) {
            int min = Integer.MAX_VALUE;
            int count = 1;
            while (!input.isEmpty()) {
                int element = input.pollFirst();
                if (element < min) {
                    min = element;
                    count = 1;
                } else if (element == min) {
                    count++;
                }
                output.offerFirst(element);
            }

            while (!output.isEmpty() && output.peekFirst() >= min) {
                int element = output.pollFirst();
                if (element > min) {
                    input.offerFirst(element);
                }
            }

            while (count > 0) {
                output.offerFirst(min);
                count--;
            }
        }

        while (!output.isEmpty()) {
            input.offerFirst(output.pollFirst());
        }
    }
}
