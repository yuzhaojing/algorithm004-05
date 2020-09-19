package laioffer.DynamicProgrammingI;

import java.util.HashSet;
import java.util.Set;

public class DictionaryWordI {

    public static void main(String[] args) {
        String[] array = {"abc","bcd","def"};
        System.out.println(new DictionaryWordI().canBreak("bcdbcdabc", array));
    }

    /**
     * 假设：input != null && dict != null
     * 如果不符合假设，那么要么无法切割，要么无法匹配，最后都不能找到解，所以返回false
     * high level: 使用一维DP解答
     * mid level: linear scan回头看，每次回头看之前记录的所有字符长度的情况
     *  1、M[i]表示输入的字符串是input的前i个字符的时候，是否能被切割成都是字典内的单词
     *  2、base case: M[0] = true (这里可以和面试官商量，如果““要比较的话，后面切割的时候就应该不考虑空串的情况)
     *  3、inductin rule: M[i] = for j in [0, i)
     *                       M[j] && (input.substring(j, i) is in dict)
     * time = O(n^2)
     * space = O(n)
     */
    public boolean canBreak(String input, String[] dict) {
        // Write your solution here
        // corner case
        if (input == null || input.length() == 0 || dict == null || dict.length == 0) {
            return false;
        }

        // 建立hashSet，用于存储字典
        Set<String> dicts = new HashSet<>();
        for (String s : dict) {
            dicts.add(s);
        }

        // 建立dp数组，用于存放当输入input长度为对应值的时候
        // 是否可以分成n段，每段都在dicts内
        boolean[] M = new boolean[input.length() + 1];

        // base case
        // 当输入长度为0的时候，不需要去比较
        // 由于需要左大段和右小段同时为true才能为true，所以默认为true
        M[0] = true;

        // 从input的长度为1开始进行递推
        for (int i = 1; i <= input.length(); i++) {
            // 每次将所有分割的可能性都列举出来
            // 只要有一种可能为true，则当前输入长度的dp数值就为true
            // 表示在这个输入长度的时候可以进行分割，并满足题意
            for (int j = 0; j < i; j++) {
                if (M[j] && dicts.contains(input.substring(j, i))) {
                    M[i] = true;
                }
            }
        }

        return M[M.length - 1];
    }
}
