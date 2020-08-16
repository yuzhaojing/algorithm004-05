package laioffer.DynamicProgrammingII;

import java.util.Arrays;

public class QuizLargestSubArraySum {

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 0};
        System.out.println(Arrays.toString(new QuizLargestSubArraySum().largestSum(array)));
    }

    /**
     * 由于dp数组中每次只需要和前一位进行比较
     * 所以可以使用一个变量储存前一位的值
     *
     * time = O(n)
     *
     * space = O(1)
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
