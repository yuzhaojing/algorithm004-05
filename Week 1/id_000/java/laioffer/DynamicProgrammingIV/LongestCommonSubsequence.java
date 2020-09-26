package laioffer.DynamicProgrammingIV;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubsequence().longest("abcdefg", "bbcefgh"));
    }

    /**
     * input:  String source
     *         String target
     * output: int
     * Assume: source != null && target != null
     *
     * high level: 使用二维DP解答
     * detail level:
     *  1、M[i][j]表示source的前i个字符和target的前j个字符的最长公共子序列长度
     *     base case: M[i][0] = 0, M[0][j] = 0
     *     induction rule: if (source.charAt(i - 1) == target.charAt(j - 1))  M[i][j] = M[i - 1][j - 1] + 1
     *                     else   M[i][j] = max(M[i - 1][j], M[i][j - 1])
     *
     * time = O(n^2)
     * space = O(n^2)
     */
    public int longest(String source, String target) {
        // Write your solution here
        if (source == null || target == null) {
            return 0;
        }

        int[][] M = new int[source.length() + 1][target.length() + 1];
        int maxLen = 0;

        for (int i = 0; i <= source.length(); i++) {
            for (int j = 0; j <= target.length(); j++) {
                if (i == 0 || j == 0) {
                    M[i][j] = 0;
                } else {
                    if (source.charAt(i - 1) == target.charAt(j - 1)) {
                        M[i][j] = M[i - 1][j - 1] + 1;
                    } else {
                        M[i][j] = Math.max(M[i - 1][j], M[i][j - 1]);
                    }
                }

                maxLen = Math.max(maxLen, M[i][j]);
            }
        }

        return maxLen;
    }
}
