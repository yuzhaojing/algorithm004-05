package laioffer.DynamicProgrammingIV;

public class WildcardMatching {

    /**
     * https://app.laicode.io/app/problem/290
     *
     * input:  String input
     *         String pattern
     * output: boolean
     * Assume: input != null && pattern != null
     *
     * high level: 使用二维DP解答
     * detail level:
     *  1、M[i][j]表示input的前i个字符和pattern的前j个字符能否匹配上
     *     base case: M[0][0] = true, M[i][0] = false,
     *                if (pattern.charAt(j - 1) == '*') M[0][j] = M[0][j - 1]
     *     induction rule: if (input.charAt(i - 1) == pattern.charAt(j - 1))  M[i][j] = M[i - 1][j - 1]
     *                     else if (pattern.charAt(j - 1) == '?')             M[i][j] = M[i - 1][j - 1]
     *                     else if (pattern.charAt(j - 1) == '*')             M[i][j] = M[i - 1][j] || M[i][j - 1]
     *                                                                                  (匹配一个字符)   (匹配0个字符)
     *  2、返回M[input.length()][pattern.length()]
     *
     * time = O(n^2)
     * space = O(n^2)
     */
    public boolean match(String input, String pattern) {
        // Write your solution here
        if (input == null || pattern == null) {
            return false;
        }

        boolean[][] M = new boolean[input.length() + 1][pattern.length() + 1];
        M[0][0] = true; // 两个空字符串可以匹配

        for (int j = 1; j <= pattern.length(); j++) {
            if (pattern.charAt(j - 1) == '*') {
                // *可以匹配任意字符，所以只需要看前面是否能匹配上
                M[0][j] = M[0][j - 1];
            }
        }

        for (int i = 1; i <= input.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                // 当前字符能够匹配上
                if (input.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?') {
                    M[i][j] = M[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    // M[i - 1][j]表示*匹配一个字符
                    // M[i][j - 1]表示*匹配0个字符
                    // 由于j没有移动，所以前面已经计算过*匹配任意字符的情况了，只需要将之前的结果拿过来就可以
                    M[i][j] = M[i - 1][j] || M[i][j - 1];
                }
            }
        }

        return M[input.length()][pattern.length()];
    }
}
