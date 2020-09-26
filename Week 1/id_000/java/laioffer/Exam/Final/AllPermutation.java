package laioffer.Exam.Final;

import java.util.ArrayList;
import java.util.List;

public class AllPermutation {

    public static void main(String[] args) {
        System.out.println(new AllPermutation().recruitingEventSchedules("ABC"));
    }

    public List<String> recruitingEventSchedules(String input) {
        List<String> res = new ArrayList<>();
        if (input == null || input.length() == 0) {
            return res;
        }

        dfs(input, 0, new StringBuilder(), res);
        return res;
    }

    private void dfs(String input, int index, StringBuilder cur, List<String> res) {
        if (index == input.length() - 1) {
            cur.append(input.charAt(index));
            res.add(cur.toString());
            cur.deleteCharAt(cur.length() - 1);
            return;
        }

        // 选择添加当前字符和‘x’
        cur.append(input.charAt(index)).append("x");
        dfs(input, index + 1, cur, res);

        // 只添加当前字符
        cur.deleteCharAt(cur.length() - 1);
        dfs(input, index + 1, cur, res);

        // 清除本层全部状态
        cur.deleteCharAt(cur.length() - 1);
    }

    /**
     * input:  String input
     * output: List<String>
     * Assume: input != null
     * <p>
     * high level: 使用DFS中subset方式解答
     * detail level:
     *  1、对每个字符，有两种加法，加当前字符和空格以及只加当前字符
     *  2、如果选择加了空格，那么将空格的数量减1，后面不加
     *  3、base case: if (index == input.lenght()) res.add(cur.toString())
     *    recursion rule: if (remain > 0)  cur.append(input.charAt(index)).append(" ");
     *                                     dfs(cur, index + 1, remain - 1);
     *                                     cur.deleteCharAt(cur.length() - 1);
     *                                     dfs(cur, index + 1, remain);
     *                                     cur.deleteCharAt(cur.length() - 1);
     *
     *                    else             cur.append(input.charAt(index));
     *                                     dfs(cur, index + 1, remain);
     *                                     cur.deleteCharAt(cur.length() - 1);
     *
     * time = O(2^n)
     * space = O(n)
     */
    public List<String> allPermutation(String input) {
        List<String> res = new ArrayList<>();
        if (input == null) {
            return res;
        }

        helper(input.toCharArray(), 0, new StringBuilder(), res);
        return res;
    }

    private void helper(char[] input, int index, StringBuilder cur, List<String> res) {
        if (index == input.length - 1) {
            cur.append(input[index]);
            res.add(cur.toString());
            cur.deleteCharAt(cur.length() - 1);
            return;
        }

        cur.append(input[index]).append("_");
        helper(input, index + 1, cur, res);
        cur.deleteCharAt(cur.length() - 1);
        helper(input, index + 1, cur, res);
        cur.deleteCharAt(cur.length() - 1);
    }
}
