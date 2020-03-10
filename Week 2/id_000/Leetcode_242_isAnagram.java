import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Leetcode_242_isAnagram {
    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     */

    public static void main(String[] args) {

        String a = "abcdez";
        String b = "dcaeb";
        boolean flag = isAnagram2(a, b);
        System.out.println(flag);

    }

    /**
     * 既然是异位词，排序之后是相同的字符串
     * 时间复杂度：O(nlog⁡n)O(n \log n)O(nlogn)，假设 nnn 是 sss 的长度，排序成本 O(nlog⁡n)O(n\log n)O(nlogn) 和比较两个字符串的成本 O(n)O(n)O(n)。排序时间占主导地位，总体时间复杂度为 O(nlog⁡n)O(n \log n)O(nlogn)。
     */
    public static boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        Arrays.sort(cs);
        Arrays.sort(ct);
        return Arrays.equals(cs, ct);
    }

    /**
     * 计算每一个字符出现的次数
     */
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // s中再出现的单词位置加1
            counter[s.charAt(i) - 'a']++;
            // t中出现的单词位置加1
            counter[t.charAt(i) - 'a']--;
        }

        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
