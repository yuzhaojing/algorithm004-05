package divideAndConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_22_GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(method1(2));
    }

    private static List<String> method1(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) return res;

        generateParentheses(res, "", n, n);
        return res;
    }

    private static void generateParentheses(List<String> res, String str, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }

        if (left > 0) {
            generateParentheses(res, str.concat("("), left - 1, right);
        }

        if (left < right) {
            generateParentheses(res, str.concat(")"), left, right - 1);
        }
    }
}
