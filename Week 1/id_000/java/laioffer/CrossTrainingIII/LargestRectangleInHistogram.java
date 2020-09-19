package laioffer.CrossTrainingIII;

import java.util.*;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(new LargestRectangleInHistogram().largest(new int[] {1, 2, 1}));
    }

    /**
     * input: int[] array
     * output: int
     * Assume: array != null && array.length > 0
     * 如果不符合假设，没有元素返回0
     *
     * high level: 使用单调栈的方法
     * detail level:
     *  1、将数组内的元素的角标依次放入栈中，保证栈内元素为升序
     *  2、如果发现新加入元素小于栈顶元素，表示栈内小于当前元素的边界已经确定，可以进行计算
     *     左边界:栈内前一个元素角标，右边界:当前元素角标
     *  3、将所有元素都遍历完之后，计算依然留在栈内的元素
     *     左边界:栈内前一个元素角标，右边界:整个数组的长度
     *
     * time: 2n = O(n)
     * 将数组遍历一遍，对每个元素只进行两次操作，每次的时间为O(1)
     * space: O(n)
     */
    public int largest(int[] array) {
        // Write your solution here
        if (array == null || array.length  == 0) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        // 先放入-1，让stack内保持升序，并且stack不会为空
        stack.offerLast(-1);
        int result = 0;

        for (int i = 0; i < array.length; i++) {
            // 当栈顶的元素不为-1并且当前元素小于等于栈顶元素，取出栈顶元素
            // 算出以栈顶元素为高的面积，并尝试更新最大值
            while (stack.peekLast() != -1 && array[stack.peekLast()] > array[i]) {
                int cur = stack.pollLast();
                // i - 1是右边界，栈顶元素之前的元素的角标是左边界
                int arena = array[cur] * (i - 1 - stack.peekLast());
            }
            // 将比当前元素大的元素都计算完之后，放入当前元素的角标
            stack.offerLast(i);
        }

        // 遍历完数组，处理stack中剩余的元素
        while (stack.peekLast() != -1) {
            int cur = stack.pollLast();
            // array.length - 1是右边界，栈顶元素之前的元素的角标是左边界
            result = Math.max(result, array[cur] * (array.length - 1 - stack.peekLast()));
        }

        return result;
    }
}
