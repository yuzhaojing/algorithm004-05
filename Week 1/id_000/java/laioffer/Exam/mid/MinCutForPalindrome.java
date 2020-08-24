package laioffer.Exam.mid;

import java.util.Arrays;

public class MinCutForPalindrome {

    public static void main(String[] args) {
        System.out.println(new MinCutForPalindrome().minCutForPalindrome("ababbbabbababa"));
    }

    /**
     * 假设：input != null
     * 如果input == null 那么无法构成回文字符串，和面试官商量返回值
     * 暂定 -1
     * <p>
     * 如果input.length() <= 1,不需要切割就是回文字符串，返回0
     * <p>
     * high level: 使用DP来进行解题
     * mid level: 使用一维DP数组进行解答
     * dp物理意义: M[i]的物理意义是前i个字符中，最少需要切几次
     * base case: M[0] = 0 (如果是空串，那么不需要切)
     *            M[1] = 0 (如果只有一个字符，那么也不需要切)
     *
     * induction rule:
     *      M[i] = for j in [0, i)
     *      M[i] = Math.min(M[i], M[j] + if (input.substring(j, i) is palindrome) 1 else skip)
     * time = O(n^3)
     * 枚举字符串长度 O(n)
     * 枚举左大段长度 O(n)
     * 截取[i, j)子串判断是否是回文串 O(n)
     * <p>
     * space = O(n) dp数组
     */

    public int minCutForPalindrome(String input) {
        if (input == null) {
            return -1;
        }

        if (input.length() <= 1) {
            return 0;
        }

        int[] M = new int[input.length() + 1];
        // 由于字符长切分成回文串不存在无法切分的问题，所以可以不需要填上-1
        Arrays.fill(M, -1);
        M[0] = 0;
        M[1] = 0;

        for (int i = 2; i <= input.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (M[j] != -1 && isPalindrome(input.substring(j, i))) {
                    if (M[i] == -1 || M[i] > M[j] + 1) {
                        M[i] = M[j] + 1;
                    }
                }
            }
        }

        return M[input.length()];
    }

    private boolean isPalindrome(String input) {
        int left = 0;
        int right = input.length() - 1;

        while (left < right) {
            if (input.charAt(left++) != input.charAt(right--)) {
                return false;
            }
        }

        return true;
    }
}
