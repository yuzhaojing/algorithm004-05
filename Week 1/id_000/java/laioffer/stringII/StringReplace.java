package laioffer.stringII;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class StringReplace {

    public static void main(String[] args) {
        System.out.println(new StringReplace().replace1(
                "mozambiquemayotterussiayemeniranyemenyemenyemenguatemalayemen",
                "yemen","nauruu"));
    }

    /**
     * 需要和面试官讨论sb和inplace的方案以及优劣！！！
     * 使用inplace的做法，需要判断target和source之间的长度关系
     * 因为如果target比source长度长的话，原先数组的长度就不够了
     * 因为java中数组长度不可变，如果面试官没有特别要求
     * 可以在target.length <= source.length的时候使用inplace
     * 在target.length > source.length的时候使用StringBuilder
     *
     * time = O(n)
     *
     * space = O(n)
     * 如果传入的是char array,那么space就需要分情况讨论了
     * 1、target.length <= source.length
     *    space = O(1) 直接用inplace的方法做，前提是input允许修改
     * 2、target.length > source.length
     *    space = O(n) 这种情况下必须扩容，在java中array为定长
     *    所以无论使用array还是StringBuilder都需要额外的空间
     */
    public String replace(String input, String source, String target) {
        // Write your solution here
        if (input == null || input.length() == 0 || source == null || target == null) {
            return input;
        }

        Queue<Integer> replaceStarts = new ArrayDeque<>();

        char[] inputArray = input.toCharArray();
        char[] sourceArray = source.toCharArray();
        char[] targetArray = target.toCharArray();

        for (int i = 0; i <= inputArray.length - sourceArray.length; i++) {
            boolean isMatch = true;
            for (int j = 0; j < sourceArray.length; j++) {
                if (inputArray[i + j] != sourceArray[j]) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                replaceStarts.offer(i);
            }
        }

        if (target.length() > source.length()) {
            // 使用StringBuilder，O(n)的额外空间
            // 如果target.length() > source.length()
            // 还将多使用 出现次数*(target.length() - source.length())的空间
            int fast = 0;
            StringBuilder sb = new StringBuilder();
            while (fast < inputArray.length) {
                if (replaceStarts.peek() == null ||fast < replaceStarts.peek()) {
                    sb.append(inputArray[fast++]);
                } else {
                    // 当fast指针到达需要替换的角标起始点的时候
                    // 将该角标起始点从queue中poll出来
                    // 然后将target append到StringBuilder中
                    // 最后将fast指针移动到被替换的字符串之后
                    replaceStarts.poll();
                    sb.append(target);
                    fast += sourceArray.length;
                }
            }
            return sb.toString();
        } else {
            int slow = 0;
            int fast = 0;
            while (fast < inputArray.length) {
                if (replaceStarts.peek() == null || fast < replaceStarts.peek()) {
                    inputArray[slow++] = inputArray[fast++];
                } else {
                    replaceStarts.poll();
                    fast += sourceArray.length;
                    // 之前和StringBuilder方案相同
                    // 不同点在于没有使用StringBuilder
                    // 将target中的元素一次copy到input中
                    for (int i = 0; i < targetArray.length; i++) {
                        inputArray[slow++] = targetArray[i];
                    }
                }
            }
            // 返回的时候，在slow左侧不包括slow的都是需要返回的结果
            return new String(inputArray, 0, slow);
        }
    }

    /**
     * 全部使用inplace做法
     */
    public String replace1(String input, String source, String target) {
        // Write your solution here
        if (input == null || input.length() == 0 || source == null || target == null) {
            return input;
        }

        char[] array = input.toCharArray();
        char[] sourceArray = source.toCharArray();
        char[] targetArray = target.toCharArray();
        Deque<Integer> indices = new ArrayDeque<>();

        // 原数组长度不会增大
        if (sourceArray.length >= targetArray.length) {
            for (int i = 0; i <= array.length - sourceArray.length; i++) {

                // 检查是否是子串
                boolean isMatch = true;
                for (int j = 0; j < sourceArray.length; j++) {
                    if (array[i + j] != sourceArray[j]) {
                        isMatch = false;
                    }
                }

                // 如果是子串则放进队列中
                if (isMatch) {
                    indices.offer(i);
                }
            }

            // slow: slow的左边不包含slow都是要返回的结果
            // fast: 当fast的角标不是子串起点的时候，copy元素到slow
            //       当fast的角标是子串起点的时候，将target copy到slow，然后将fast移动sourceArray.length
            int slow = 0;
            int fast = 0;
            while (fast < array.length) {
                if (indices.peek() != null && fast == indices.peek()) {
                    for (char t : targetArray) {
                        array[slow++] = t;
                    }
                    fast += sourceArray.length;
                    indices.poll();
                } else {
                    array[slow++] = array[fast++];
                }
            }

            return new String(array, 0, slow);
        } else {
            for (int i = 0; i <= array.length - sourceArray.length; i++) {
                boolean isMatch = true;
                for (int j = 0; j < sourceArray.length; j++) {
                    if (array[i + j] != sourceArray[j]) {
                        isMatch = false;
                    }
                }

                // 此时应该放入栈中，使用deque模拟
                if (isMatch) {
                    indices.offerFirst(i + sourceArray.length - 1);
                }
            }

            // 计算新的数组长度，并创建新数组
            int newLength = array.length + (targetArray.length - sourceArray.length) * indices.size();
            char[] res = new char[newLength];

            // slow: slow的右边不包含slow都是要返回的结果
            // fast: 当fast的角标不是子串终点的时候，copy元素到slow
            //       当fast的角标是子串终点的时候，将target copy到slow，然后将fast移动sourceArray.length
            int slow = newLength - 1;
            int fast = array.length - 1;
            while (fast >= 0) {
                if (indices.peekFirst() != null && indices.peekFirst() == fast) {
                    // 由于slow是从右往左移动，所以copy的时候需要将target倒过来
                    for (int i = targetArray.length - 1; i >= 0; i--) {
                        res[slow--] = targetArray[i];
                    }
                    fast -= sourceArray.length;
                    indices.pollFirst();
                } else {
                    res[slow--] = array[fast--];
                }
            }

            return new String(res);
        }
    }
}
