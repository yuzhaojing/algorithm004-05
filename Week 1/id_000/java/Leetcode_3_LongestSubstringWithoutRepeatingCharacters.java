import java.util.HashMap;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_3_LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(method1("dvdf"));
    }

    private static int method1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxSize = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }

            map.put(s.charAt(i), i);
            maxSize = Math.max(maxSize, i - left + 1);
        }

        return maxSize;
    }

}
