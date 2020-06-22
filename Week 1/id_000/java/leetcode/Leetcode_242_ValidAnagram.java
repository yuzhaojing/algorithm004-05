package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_242_ValidAnagram {

    public static void main(String[] args) {
        System.out.println(method2("car", "cat"));
    }

    private static boolean method1(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts[c - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            counts[c - 'a']--;
        }

        for (int count: counts) {
            if (count != 0) return false;
        }

        return true;
    }

    private static boolean method2(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c ,map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map.containsKey(c) && map.get(c) > 0) {
                map.put(c ,map.get(c) - 1);
            } else {
                return false;
            }
        }

        return true;
    }

}
