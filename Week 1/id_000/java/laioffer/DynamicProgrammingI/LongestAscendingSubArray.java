package laioffer.DynamicProgrammingI;

public class LongestAscendingSubArray {

    public static void main(String[] args) {

    }

    /**
     * 使用dp计算最大上升子串
     * dp数组每一个元素代表包含array当前角标元素的最大上升子串
     *
     * time = O(n)
     *
     * space = O(n)
     */
    public int longest(int[] array) {
        // Write your solution here
        // corner case
        if (array == null || array.length == 0) {
            return 0;
        }

        int maxLen = 1;

        // 创建DP数组，M[i]表示包含array[i]在内的最大升序子串
        // 因为只有包含i的情况，当array[i + 1] > array[i]时才能继续累加
        int[] M = new int[array.length];

        // base case
        M[0] = 1;

        for (int i = 1; i < array.length; i++) {
            // induction rule
            M[i] = array[i] > array[i - 1] ? M[i - 1] + 1 : 1;
            maxLen = Math.max(maxLen, M[i]);
        }

        return maxLen;
    }

    /**
     * 因为只需要回头看前一位，所以可以用变量存储
     *
     * space = O(1)
     */
    public int longestBetter(int[] array) {
        // Write your solution here
        // corner case
        if (array == null || array.length == 0) {
            return 0;
        }

        int maxLen = 1;
        int prev = 1;

        for (int i = 1; i < array.length; i++) {
            prev = array[i] > array[i - 1] ? prev + 1 : 1;
            maxLen = Math.max(maxLen, prev);
        }

        return maxLen;
    }
}
