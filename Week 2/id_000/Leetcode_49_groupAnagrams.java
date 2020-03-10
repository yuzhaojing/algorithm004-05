import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Leetcode_49_groupAnagrams {
    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * <p>
     * 示例:
     * <p>
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * <p>
     * 说明：
     * <p>
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */

    public static void main(String[] args) {
        // 输出是一个二维数组
        String[] a = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams1(a);
        System.out.println(lists.toString());
    }

    /**
     * 排序，相同字符串归纳
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String s1 = String.valueOf(chars);
            if (!map.containsKey(s1)) map.put(s1, new ArrayList<>());
            map.get(s1).add(s);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 记录这个单词的每一个字符出现的次数，相同进行归纳
     */
    public static List<List<String>> groupAnagrams1(String[] strs) {
        if (strs.length == 0) new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; ++i) {
            int[] counter = new int[26];
            String str = strs[i];
            for (char c : str.toCharArray()) {
                counter[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int count : counter) {
                sb.append(count);
            }

            if (!map.containsKey(sb.toString())) {
                map.put(sb.toString(), new ArrayList<>());
            }

            map.get(sb.toString()).add(str);
        }

        return new ArrayList<>(map.values());

    }
}
