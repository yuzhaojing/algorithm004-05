package laioffer.BFSII;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LargestProductOfLength {

    public static void main(String[] args) {
        String[] dict = {"abcde", "abcd", "ade", "xy"};
        System.out.println(new LargestProductOfLength().largestProduct(dict));
    }

    /**
     * input:  String[] dict
     * output: int
     * Assume: dict != null && dict.length > 2
     * 不符合假设的话，不存在符合题意的解，返回0
     *
     * high level: 使用两个for循环解答
     * detail level:
     *  1、使用两个for循环遍历所有的两两配对的可能性，然后记录没有重复字符的最大乘积
     *  2、判断两个字符串没有重复字符，可以使用bit operation
     *     由于word都是小写字母，所以可以使用26位二进制表示，即int就可以表示
     *     遍历每个字符串，将出现的字符以在字母表中的顺序放在int中，如果两个int与为0，那么说明没有重复字符
     *
     * 假设dict.length = n, word平均长度为m
     * time = O(n*m + n^2)
     * 计算出每一个word的二进制表示，放入hashMap中 O(n*m)
     * 遍历所有单词对，查询二进制并进行与计算       O(n^2)
     * space = O(n) 使用hashMap缓存了所有的单词及对应的二进制
     *
     * 如果需要early return，可以使用nlogn的时间sort以下dict
     * 这样在乘积小于max的时候可以跳过内层循环
     */
    public int largestProduct(String[] dict) {
        // Write your solution here
        if (dict == null || dict.length < 2) {
            return 0;
        }

        Map<String, Integer> wordToBitMask = new HashMap<>();
        init(wordToBitMask, dict);
        int max = 0;

//        Arrays.sort(dict, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                if (o1.length() == o2.length()) {
//                    return 0;
//                }
//
//                return o1.length() < o2.length() ? 1 : -1;
//            }
//        });

        for (int i = 0; i < dict.length - 1; i++) {
            for (int j = i + 1; j < dict.length; j++) {
//                if (dict[i].length() * dict[j].length() < max) break;
                if ((wordToBitMask.get(dict[i]) & wordToBitMask.get(dict[j])) == 0) {
                    max = Math.max(max, dict[i].length() * dict[j].length());
                }
            }
        }
        return max;
    }

    private void init(Map<String, Integer> wordToBitMask, String[] dict) {
        for (String word : dict) {
            int bitMask = 0;
            for (int i = 0; i < word.length(); i++) {
                bitMask |= 1 << (word.charAt(i) - 'a');
            }
            wordToBitMask.put(word, bitMask);
        }
    }
}
