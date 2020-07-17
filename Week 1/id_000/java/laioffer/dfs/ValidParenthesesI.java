package laioffer.dfs;


import java.util.ArrayList;
import java.util.List;

public class ValidParenthesesI {

    public static void main(String[] args) {
        System.out.println(validParentheses(3));
    }

    /**
     * 时间复杂度O(2^n)
     * 空间复杂度O(n)
     */
    private static List<String> validParentheses(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) return res;

        validParenthesesHelper(n, 0, 0, "", res);
        return res;
    }

    private static void validParenthesesHelper(int n, int left, int right, String tmp, List<String> res) {
        if (left == n && right == n) {
            res.add(tmp);
            return;
        }

        if (left < n) {
            validParenthesesHelper(n, left + 1, right, tmp.concat("("), res);
        }

        if (right < left) {
            validParenthesesHelper(n, left, right + 1, tmp.concat(")"), res);
        }
    }
}
