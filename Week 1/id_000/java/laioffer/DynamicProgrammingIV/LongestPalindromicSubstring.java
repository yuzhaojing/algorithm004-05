package laioffer.DynamicProgrammingIV;

/**
 * 1、两数之和
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String res = new LongestPalindromicSubstring().longestPalindromicSubstring("cbbd");
        System.out.println(res);
        res = new LongestPalindromicSubstring().longestPalindromicSubstring("babad");
        System.out.println(res);
    }

    /**
     * input:  String input
     * output: String
     * Assume: input != null && input.length > 0
     *
     * high level: 使用二维DP，两头凑的方式解答
     * detail level:
     *  1、M[i][j]表示从input[i]到input[j]之间是否是回文串
     *  2、base case: M[i][i] = true;
     *               M[i][i + 1] = true;  if (input[i] == input[i + 1])
     *               M[i][i + 1] = falss; else
     *     induction rule: M[i][j] = M[i + 1][j - 1] if (input.charAt(i) == input.charAt(j))
     *                             = false
     *
     * time = O(n^2)
     * space = O(n^2)
     */
    public String longestPalindromicSubstring(String input) {
        if (input == null || input.length() < 2) {
            return input;
        }

        boolean[][] M = new boolean[input.length()][input.length()];

        int maxLen = 0;
        int begin = 0;

        for (int j = 1; j < input.length(); j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    M[i][j] = true;
                } else if (input.charAt(i) != input.charAt(j)) {
                    M[i][j] = false;
                } else {
                    if (i + 1 == j) {
                        M[i][j] = true;
                    } else {
                        M[i][j] = M[i + 1][j - 1];
                    }
                }

                int len = j - i + 1;
                if (M[i][j] && len > maxLen) {
                    maxLen = len;
                    begin = i;
                }
            }
        }

        return input.substring(begin, begin + maxLen);
    }
}
