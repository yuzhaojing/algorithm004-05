package laioffer.stringII;

import java.util.*;

public class AllAnagrams {

    public static void main(String[] args) {
        System.out.println(new AllAnagrams().allAnagrams("aab", "ababacbbaac"));
    }

    /**
     * 滑动窗口求同构词出现的起始角标
     * 1.将同构词遍历，并记录在HashMap里
     * 2.遍历给定字符串，使用一个与同构词等长的滑动窗口
     * 3.在HashMap中根据出现的字符次数增加或者减少对应字符的count
     * 4.如果HashMap中所有value都为0，则表示出现同构词
     *
     *
     * time = O(n + m) 假设n为较长的数组
     * 两个字符串各遍历一次 O(n) + O(m)
     * 在遍历长度为n的数组中，最多比较2n次HashMap
     * HashMap中的key数量为常数，与m的长度无直接关系 O(2n) * O(1)
     * total time = O(n) + O(m) + O(2n) = O(3n + m) = O(n + m)
     *
     * space = O(1)
     * HashMap中的key数量为常数，与m的长度无直接关系
     */
    public List<Integer> allAnagrams(String sh, String lo) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (sh == null || lo == null || lo.length() < sh.length()) {
            return res;
        }

        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < sh.length(); i++) {
            char c = sh.charAt(i);
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }

        int matchSize = 0;
        for (int j = 0; j < lo.length(); j++) {
            char c = lo.charAt(j);
            Integer count = counter.get(c);
            if (count != null) {
                if (count == 1) {
                    matchSize++;
                } else if (count == 0) {
                    matchSize--;
                }

                counter.put(c, count - 1);
            }

            if (j >= sh.length()) {
                char r = lo.charAt(j - sh.length());
                count = counter.get(r);
                if (count != null) {
                    if (count == 0) {
                        matchSize--;
                    } else if (count == -1) {
                        matchSize++;
                    }

                    counter.put(r, count + 1);
                }
            }

            if (matchSize == counter.size()) {
                res.add(j - sh.length() + 1);
            }
        }

        return res;
    }
}
