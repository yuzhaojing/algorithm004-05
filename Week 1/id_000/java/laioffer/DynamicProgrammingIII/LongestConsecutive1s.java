package laioffer.DynamicProgrammingIII;

public class LongestConsecutive1s {

    public static void main(String[] args) {

    }

    /**
     * 假设：array != null && array.length > 0
     * 如果不符合假设，则数组中不存在1，所以最长连续1就是0
     *
     * high level: 使用一维DP解答
     * mid level: linear scan回头看，每次看前面一个
     *  1、M[i]表示在角标为[0, i]的范围内，以array[i]为最后一个元素的最长连续1的值
     *  2、base case: M[0] = array[0]
     *  3、induction rule: M[i] = array[i] == 0 ? 0 : M[i - 1] + 1
     *
     * time = O(n)
     * space = O(1) // 由于只回头看一个元素，所以可以使用一个变量来记录
     */
    public int longest(int[] array) {
        // Write your solution here
        if (array == null || array.length <= 0) {
            return 0;
        }

        int prev = array[0];
        int maxLen = prev;

        for (int i = 1; i < array.length; i++) {
            prev = array[i] == 0 ? 0 : prev + 1;
            maxLen = Math.max(maxLen, prev);
        }

        return maxLen;
    }
}
