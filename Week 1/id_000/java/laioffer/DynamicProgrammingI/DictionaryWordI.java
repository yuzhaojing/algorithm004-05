package laioffer.DynamicProgrammingI;

import java.util.HashSet;
import java.util.Set;

public class DictionaryWordI {

    public static void main(String[] args) {
        String[] array = {"abc","bcd","def"};
        System.out.println(new DictionaryWordI().canBreak("bcdbcdabc", array));
    }


    /**
     * 使用dp求一个数组能否从初始点跳跃到终点
     *
     * time = O(n^3)
     * substring time = O(n)
     *
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
