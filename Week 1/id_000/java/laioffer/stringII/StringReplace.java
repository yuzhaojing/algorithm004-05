package laioffer.stringII;

import java.util.ArrayDeque;
import java.util.Queue;

public class StringReplace {

    public static void main(String[] args) {
        System.out.println(new StringReplace().replace(
                "mozambiquemayotterussiayemeniranyemenyemenyemenguatemalayemen",
                "yemen","naur"));
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
}
