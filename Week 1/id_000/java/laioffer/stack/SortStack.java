package laioffer.stack;

import com.sun.prism.Image;
import org.omg.CORBA.INTERNAL;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

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
        sort(s1, s2);
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

//    private void sort(LinkedList<Integer> input, LinkedList<Integer> buffer) {
//        while (!input.isEmpty()) {
//            int globalMax = Integer.MIN_VALUE;
//            int counter = 0;
//
//            while (!input.isEmpty()) {
//                int element = input.pollLast();
//                globalMax = Math.max(globalMax, element);
//                buffer.addLast(element);
//            }
//
//            while (!buffer.isEmpty() && buffer.peekLast() <= globalMax) {
//                int bufferElement = buffer.pollLast();
//                if (bufferElement != globalMax) {
//                    input.addLast(bufferElement);
//                } else {
//                    counter++;
//                }
//            }
//
//            while (counter --> 0) {
//                buffer.addLast(globalMax);
//            }
//        }
//
//        while (!buffer.isEmpty()) {
//            input.addLast(buffer.pollLast());
//        }
//    }

    /* 做法正确，但是输出的input是降序的，laicode上要求升序，所以使用每次取最大值的方式
    private void sort(LinkedList<Integer> input, LinkedList<Integer> buffer) {
        while (!input.isEmpty()) {
            int globalMin = Integer.MAX_VALUE;
            int counter = 0;

            while (!input.isEmpty()) {
                int element = input.pollLast();
                globalMin = Math.min(globalMin, element);
                buffer.addLast(element);
            }

            while (!buffer.isEmpty() && buffer.peekLast() >= globalMin) {
                int bufferElement = buffer.pollLast();
                if (bufferElement != globalMin) {
                    input.addLast(bufferElement);
                } else {
                    counter++;
                }
            }

            while (counter --> 0) {
                buffer.addLast(globalMin);
            }
        }

        while (!buffer.isEmpty()) {
            input.addLast(buffer.pollLast());
        }
    }*/
}
