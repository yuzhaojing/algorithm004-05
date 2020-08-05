package laioffer.stringII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().longest("ACBCD"));
    }

    /**
     * 使用滑动窗口求无重复最长子串
     * 1.fast指针每次循环移动一步，记录slow～fast最大长度以及出现过的元素
     * 2.当元素出现重复的时候，将slow指针向右移动，直到没有重复元素
     *
     * time = O(n)
     *
     * space = O(n)
     */
    public int longest(String input) {
        // Write your solution here
        if (input == null) {
            return -1;
        }

        if (input.length() <= 1) {
            return input.length();
        }

        Set<Character> distinct = new HashSet<>();
        int slow = 0;
        int maxLen = 0;
        for (int i = 0; i < input.length(); i++) {
            if (distinct.contains(input.charAt(i))) {
                while (slow < i) {
                    distinct.remove(input.charAt(slow++));
                    if (!distinct.contains(input.charAt(i))) {
                        break;
                    }
                }
            }
            maxLen = Math.max(maxLen, i - slow + 1);
            distinct.add(input.charAt(i));
        }

        return maxLen;
    }
}
