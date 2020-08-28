package laioffer.DynamicProgrammingII;

import java.util.Arrays;

public class QuizLargestSubArraySum {

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 0};
        System.out.println(Arrays.toString(new QuizLargestSubArraySum().largestSum(array)));
    }

    /**
     * 假设：array != null && array.length > 0
     * 如果不符合假设，那么需要和面试官讨论，那么该数组的最大和应该是0，左右边界不存在，暂定返回-1
     * high level: 使用一维DP进行解答
     * mid level: linear scan回头看，每次回头看前一位的最大和
     *
     *  1、M[i]表示从[0, i]并且以array[i]作为最后一记录每次更新globalMax的时候的左右偏移量个元素的最大子数组和
     *  2、base case: M[0] = array[0]
     *  3、induction rule: M[i] = M[i - 1] < 0 ? array[i] : M[i - 1] + array[i]
     *
     * time = O(n)
     * space = O(1) 由于回头看的个数为常数，可以用变量存储
     */
    public int[] largestSum(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return new int[] {-1, -1};
        }

        int prev = array[0];
        int globalMax = prev;

        int curLeft = 0;
        // curRight永远等于i，所以省略一个变量
        int globalLeft = 0;
        int globalRight = 0;

        for (int i = 1; i < array.length; i++) {
            if (prev < 0) {
                prev = array[i];
                curLeft = i;
            } else {
                prev += array[i];
            }

            // 只有当数组和超过globalMax的时候
            // 才对globalMax，globalLeft，globalRight进行更新
            if (prev > globalMax) {
                globalMax = prev;
                globalLeft = curLeft;
                globalRight = i;
            }
        }

        return new int[] {globalMax, globalLeft, globalRight};
    }
}
