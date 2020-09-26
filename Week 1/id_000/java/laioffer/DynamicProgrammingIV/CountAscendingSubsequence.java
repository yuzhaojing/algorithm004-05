package laioffer.DynamicProgrammingIV;

public class CountAscendingSubsequence {

    /**
     * input:  int[] array
     * output: int
     * Assume: array != null && array.length > 0
     *
     * high level: 使用一维DP，linear scan回头看解答
     * detail level:
     *  1、M[i] 表示以array[i]为结尾的元素，所构成的升序子序列数量
     *     base case: M[0] = 1;
     *     induction rule: M[i] = sum(M[j]) + 1 where array[j] < array[i]
     *
     * time = O(n^2)
     * space = O(n)
     */
    public int numIncreasingSubsequences(int[] a) {
        // Write your solution here
        if (a == null || a.length == 0) {
            return 0;
        }

        int[] M = new int[a.length];
        M[0] = 1;

        int res = M[0];

        for (int i = 1; i < a.length; i++) {
            // 表示自己单独作为子序列
            M[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    M[i] += M[j];
                }
            }

            res += M[i];
        }

        return res;
    }
}
