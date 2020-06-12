import java.util.Arrays;
import java.util.HashMap;

/**
 * 1、两数之和
 */
public class Leetcode_5_LongestPalindromicSubstring {

    public static void main(String[] args) {
        String res = method3("cbbd");
        System.out.println(res);
    }

    // 暴力解法 时间复杂度：O(N^3)
    private static String method1(String s) {
        if (s == null || s.length() < 2) return s;

        int maxLen = 1;
        int begin = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                int len = j - i + 1;
                if (len > maxLen && vaildPalindromic(s, i, j)) {
                    maxLen = len;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    private static boolean vaildPalindromic(String s, int begin, int end) {
        char[] chars = s.toCharArray();
        while (begin < end) {
            if (chars[begin] != chars[end]) return false;

            begin++;
            end--;
        }

        return true;
    }

     /**
     * 动态规划 时间复杂度：O(N^2)
     * <p>
     * DP方程：dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]
     * <p>
     * 边界条件：1、j - i + 1 <= 3 (代表i-j长度小于等于3，这时候只需要判断头尾就行，不需要再继续判断)
     * 2、i <= j
     */
    private static String method2(String s) {
        if (s == null || s.length() < 2) return s;

        int maxLen = 1;
        int begin = 0;
        int length = s.length();
        char[] chars = s.toCharArray();

        Boolean[][] dp = new Boolean[length][length];

        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                int len = j - i + 1;
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (len <= 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] =  dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    /**
     * 中心扩散法 时间复杂度：O(N^2)
     * 枚举每个可能成为最长回文子串的节点
     */
    private static String method3(String s) {
        if (s == null || s.length() < 2) return s;

        int maxLen = 1;
        String res = s.substring(0, 1);

        for (int i = 1; i < s.length() - 1; i++) {
            String s1 = centerSpread(s, i, i);
            String s2 = centerSpread(s, i, i + 1);

            String maxStr = s1.length() > s2.length() ? s1 : s2;
            if (maxStr.length() > maxLen) {
                maxLen = maxStr.length();
                res = maxStr;
            }
        }

        return res;
    }

    private static String centerSpread(String s, int left, int right) {
        char[] chars = s.toCharArray();
        while (left >= 0 && right < s.length()) {
            if (chars[left] == chars[right]) {
                left--;
                right++;
            } else break;
        }

        return s.substring(left + 1, right);
    }
}
