package laioffer.stack;

import com.sun.prism.Image;
import org.omg.CORBA.INTERNAL;

import java.util.Stack;

public class SortStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(6);
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(5);

        stack = sortStack(stack);

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    public static Stack<Integer> sortStack(Stack<Integer> stack) {
        Stack<Integer> res = new Stack<>();

        while (!stack.empty()) {
            /**
             * 将元素放入辅助栈，并且记录最小值（最大值）
             * 在stack中的元素取完之后，将辅助栈中大于等于最小值的元素放回stack中
             * 然后将记录的最小值和最小值对应的数量放回辅助栈
             * 最后返回辅助栈
             */

            sortStackHelper(stack, res);
        }

        return res;
    }

    public static void sortStackHelper(Stack<Integer> stack, Stack<Integer> res) {
        int minValue = Integer.MAX_VALUE;
        int count = 0;

        while (!stack.empty()) {
            Integer value = stack.pop();
            res.push(value);
            minValue = Math.min(minValue, value);
        }

        while (!res.empty() && res.peek() >= minValue) {
            Integer value = res.pop();
            if (value > minValue) {
                stack.push(value);
            } else {
                count++;
            }
        }

        for (int i = 0; i < count; i++) {
            res.push(minValue);
        }
    }
}
