package laioffer.dfs;


import java.util.ArrayList;
import java.util.List;

public class ValidParenthesesI {

    public static void main(String[] args) {
        System.out.println(new ValidParenthesesI().validParentheses(3));
    }

    /**
     * 总共有几层？每层存储了什么东西？
     * 总共有2n层 每层存储当前层选择的括号
     *
     * 每层有几个可能（每个节点会有几个分支）
     * 每层有2个可能，即两个分支
     *
     * Time = O(4^n * n)
     * 分析过程:
     * 给定n对括号，那么总共有2n个，那么就有2n层需要判断，每层判断当前选择左括号还是右括号
     * 每层有2种可能性，选择左括号或者选择右括号
     * time = 2 + 2^2 + 2^3 + ... + 2^2n = O(2^2n) = O(4^n)
     * 每个叶子节点都需要用O(n)的时间将数据拷贝到结果集中
     *
     * Space = O(2n) = O(n)
     */
    public List<String> validParentheses(int n) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }

        validParentheses(n, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void validParentheses(int n, int left, int right, StringBuilder cur, List<String> res) {
        // 可以简化为 right == n
        // 因为left永远小于n
        if (left == n && right == n) {
            res.add(cur.toString());
            return;
        }

        // 当左括号个数小于括号总对数的时候，可以添加左括号
        if (left < n) {
            cur.append("(");
            validParentheses(n, left + 1, right, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }

        // 当右括号个数小于左括号个数的时候，可以添加右括号
        if (right < left) {
            cur.append(")");
            validParentheses(n, left, right + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
