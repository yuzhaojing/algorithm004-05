package laioffer.DFSII;


import java.util.*;

public class AllValidPermutationsOfParenthesesII {

    public static void main(String[] args) {
        System.out.println(new AllValidPermutationsOfParenthesesII().validParentheses(1, 1, 0));
    }

    /**
     * 假设 l >= 0 && m >= 0 && n >= 0
     * 如果任意一个括号的对数小于0，无法确定-1对括号怎么进行放置
     *
     * high level: 使用DFS进行解答
     * mid level:
     *  1、使用一个stack存储已经放置的括号
     *  2、每次放置一个右括号，需要先和栈顶的括号进行匹配，如果不匹配则不能放
     *
     * time = O(6^(2l + 2m + 2n) * n)
     * 每层最多有6个分叉，一共2l + 2m + 2n层
     * 最后一层需要花费O(n)的时间进行copy
     *
     * space = O(2l + 2m + 2n)) = O(l + m + n)
     *
     * heap: stack最多存储所有的左括号，即O(l + m + n)
     *       PS和remain数组都是定长，和传入数值无关，可以当成O(1)
     * call stack: 递归最多2l + 2m + 2n层，即O(2(l + m + n))
     */
    public List<String> validParentheses(int l, int m, int n) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (l < 0 || m < 0 || n < 0) {
            return res;
        }

        String[] PS = new String[]{"(", ")", "<", ">", "{", "}"};
        int[] remain = new int[]{l, l, m, m, n, n};
        int targetLen = 2 * l + 2 * m + 2 * n;

        validParentheses(PS, remain, targetLen, new ArrayDeque<>(), new StringBuilder(), res);
        return res;
    }

    private void validParentheses(String[] PS, int[] remain, int targetLen, Deque<String> stack, StringBuilder cur, List<String> res) {
        if (cur.length() == targetLen) {
            res.add(cur.toString());
            return;
        }

        for (int i = 0; i < PS.length; i++) {
            if (i % 2 == 0) {
                if (remain[i] > 0) {
                    cur.append(PS[i]);
                    stack.offerFirst(PS[i]);
                    remain[i]--;
                    validParentheses(PS, remain, targetLen, stack, cur, res);
                    cur.deleteCharAt(cur.length() - 1);
                    stack.pollFirst();
                    remain[i]++;
                }
            } else {
                if (remain[i - 1] < remain[i] && stack.peekFirst() == PS[i - 1]) {
                    cur.append(PS[i]);
                    stack.pollFirst();
                    remain[i]--;
                    validParentheses(PS, remain, targetLen, stack, cur, res);
                    cur.deleteCharAt(cur.length() - 1);
                    stack.offerFirst(PS[i - 1]);
                    remain[i]++;
                }
            }
        }
    }
}
