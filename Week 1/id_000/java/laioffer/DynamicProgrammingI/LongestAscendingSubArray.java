package laioffer.DynamicProgrammingI;

public class LongestAscendingSubArray {

    public static void main(String[] args) {

    }

    /**
     * 假设：array != null && array.length > 0
     * 如果不符合假设，那么数组中没有元素，结果只能为0
     * high level: 使用一维DP解答
     * mid level: linear scan回头看，每次看前面一个元素
     *  1、M[i]表示在[0, i]这个区间内，以array[i]这个元素作为结尾的连续最长上升子数组
     *  2、base case: M[0] = 1
     *  3、induction rule: if (M[i] > M[i - 1]) M[i] = M[i - 1] + 1
     *                     else                 M[i] = 1
     * time = O(n)
     * space = O(1) 由于回头看的个数为常数，可以用变量存储
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
