package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_49_GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(method1(strs));
    }

    private static List<List<String>> method1(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0) return res;

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str: strs) {
            int[] counts = new int[26];
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                counts[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int count: counts) {
                sb.append(count).append("#");
            }

            if (map.containsKey(sb.toString())) {
                map.get(sb.toString()).add(str);
            } else {
                ArrayList<String> list = new ArrayList<String>(){{ add(str); }};
                map.put(sb.toString(), list);
            }
        }

        res.addAll(map.values());
        return res;
    }

}
