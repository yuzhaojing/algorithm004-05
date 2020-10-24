package laioffer.Exam.two;

import laioffer.tree.ArrayToTree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DictionaryWordIII {

    public static void main(String[] args) {
//        String[] array = {"abc","bcd","def", "bcdbcd"};
        String[] array1 = {"cat", "cats", "sand", "and"};
//        System.out.println(new DictionaryWordIII().numOfWaysToCutWord1("bcdbcdabc", array));
        System.out.println(new DictionaryWordIII().numOfWaysToCutWord1("catsand", array1));
    }

    /**
     * input: String input
     *        String[] dict
     * output: int
     * Assume: input != null && dict != null
     *
     * high level: 使用一维DP解答
     * mid level:
     *  1、将dict遍历，并放在set中，用于查询
     *  2、将input分为左大段和右小段，左大段使用已计算的结果查询，右小段为不可分割的部分
     *  3、DP[i]表示单词长度为i的时候，有多少种切的方法可以满足题意
     *  4、base case:       DP[0] = true
     *     induction rule:  DP[i] = for each DP[0~(i-1)] count(true)
     *
     * time: O(n^2)
     * space: O(n)
     */
    public int numOfWaysToCutWord(String input, String[] dict) {
        if (input == null || dict == null) {
            return 0;
        }

        Set<String> dictSet = new HashSet<>();
        for (String word : dict) {
            dictSet.add(word);
        }

        int res = 0;

        boolean[] M = new boolean[input.length() + 1];
        M[0] = true;
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (M[j] && dictSet.contains(input.substring(j, i))) {
                    M[i] = true;
                    if (i == input.length()) {
                        res++;
                    }
                }
            }
        }

        return res;
    }

    public int numOfWaysToCutWord1(String input, String[] dict) {
        if (input == null || input.length() == 0 || dict == null || dict.length == 0) {
            return 0;
        }

        Set<String> dictSet = new HashSet<>();
        for (String word : dict) {
            dictSet.add(word);
        }

        int[] M = new int[input.length() + 1];
        Arrays.fill(M, -1);
        M[0] = 1;

        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (M[j] != -1 && dictSet.contains(input.substring(j, i))) {
                    M[i] = M[i] == -1 ? M[j] : M[i] + M[j];
                }
            }

        }

        return M[input.length()];
    }
}
