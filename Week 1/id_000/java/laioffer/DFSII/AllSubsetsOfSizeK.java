package laioffer.DFSII;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSubsetsOfSizeK {

    public static void main(String[] args) {
        String set = "abc";
        System.out.println(new AllSubsetsOfSizeK().subSetsOfSizeK(set, 2));
    }

    /**
     * input: string (需要求subset的字符串)
     *        int (限制每个subset结果的长度)
     * output: List<String> (返回符合要求的subset集合)
     * 假设：set != null && k >= 0
     * 如果不符合假设，不存在符合要求的subset，返回空list
     * high level: 使用DFS的subset方案解答
     * mid level: 使用DFS的subset方案解答，加一个base case，当subset结果size==k的时候，提前结束
     *  1、recursion tree 最多有多少层？            假设set的长度为n，那么最多有n层
     *  2、recursion tree 每个node最多有几种case？  有选择和不选两种case
     * time = O(2^n * k)
     * space = O(n) heap: stringBuilder && call stack
     */
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
        // 这个base case要写在前面，因为有可能k == input.length()
        // 在这种情况下，写在后面的话就不会加入结果集了
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
