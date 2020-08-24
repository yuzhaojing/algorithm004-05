package laioffer.DFSII;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSubsetsOfSizeK {

    public static void main(String[] args) {
        String set = "abc";
        System.out.println(new AllSubsetsOfSizeK().subSetsOfSizeK(set, 2));
    }

    // time = O(n^2)
    // worst case k的长度与set.length()一样
    // space = O(n) 递归n层
    public List<String> subSetsOfSizeK(String set, int k) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (set == null || k < 0) {
            return res;
        }

        subSetsOfSizeK(set.toCharArray(), 0, new StringBuilder(), res, k);
        return res;
    }

    private void subSetsOfSizeK(char[] input, int level, StringBuilder cur, List<String> res, int k) {
        // 由于每次决策都不一样，所以每次递归调用的时候cur的值也不一样
        // 所以只需要将符合题意的，即长度为k的结果筛选出来就可以了
        if (cur.length() == k) {
            res.add(cur.toString());
            return;
        }
        // 如果没有符合题意的答案，并且走到最后一层了，直接返回即可
        if (level == input.length) {
            return;
        }

        cur.append(input[level]);
        subSetsOfSizeK(input, level + 1, cur, res, k);
        cur.deleteCharAt(cur.length() - 1);
        subSetsOfSizeK(input, level + 1, cur, res, k);
    }
}
