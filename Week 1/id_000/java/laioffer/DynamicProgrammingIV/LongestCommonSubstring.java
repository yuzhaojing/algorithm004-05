package laioffer.DynamicProgrammingIV;

public class LongestCommonSubstring {

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubstring().longestCommon("abcdefg", "bbcefgh"));
    }

    /**
     * input:  String source
     *         String target
     * output: String
     * Assume: source != null && target != null
     *
     * high level: 使用二维DP解答
     * detail level:
     *  1、M[i][j]表示source的前i个字符(以i为结尾)和target的前j个字符(以j为结尾)，最长的公共子串
     *     base case: M[i][0] = 0, M[0][j] = 0 (和空字符串没有公共子串)
     *     induction rule: if (source.charAt(i - 1) == target.charAt(j - 1))  M[i][j] = M[i - 1][j - 1] + 1
     *                     else   M[i][j] = 0 (两个字符串的最后一个字符串不相同，那么以这个字符串为结尾的字符串肯定没有)\
     *
     * time = O(n^2)
     * space = O(n^2)
     */
    public String longestCommon(String source, String target) {
        // Write your solution here
        if (source == null || target == null) {
            return "";
        }

        int[][] M = new int[source.length() + 1][target.length() + 1];
        int maxLen = 0;
        int end = 0;

        for (int i = 0; i <= source.length(); i++) {
            for (int j = 0; j <= target.length(); j++) {
                if (i == 0 || j == 0) {
                    M[i][j] = 0;
                } else if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    M[i][j] = M[i - 1][j - 1] + 1;
                }

                if (M[i][j] > maxLen) {
                    maxLen = M[i][j];
                    end = i;
                }
            }
        }

        // 由于i = 0代表空字符串的情况，所以end对应真正source中的角标是end - 1,start对应end - maxLen - 1
        return maxLen > 0 ? source.substring(end - maxLen, end) : "";
    }
}
