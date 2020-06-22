package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 1、两数之和
 */
public class Leetcode_139_WordBreak {

    public static void main(String[] args) {
        String[] nums = {"apple", "pen"};
        System.out.println(method1("applepenapple", Arrays.asList(nums)));
    }

    /**
     * 动态规划
     * DP方程：f[n] = f[0] && wordDict.contains(0, n) || ... f[n - 1] && wordDict.contains(0, n - 1)
     */
    private static boolean method1(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

}
