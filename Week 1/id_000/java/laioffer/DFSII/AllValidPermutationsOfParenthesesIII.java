package laioffer.DFSII;


import java.util.*;

public class AllValidPermutationsOfParenthesesIII {

    public static void main(String[] args) {
        System.out.println(new AllValidPermutationsOfParenthesesIII().validParenthesesIII(1, 1, 0));
    }

    /**
     * input: l int (小括号对数)
     *        m int (尖括号对数)
     *        n int (大括号对数)
     * output: List<String> (返回有效的括号集合)
     * 假设：l >= 0 && m >= 0 && n >= 0
     * 如果不符合假设，则有括号的对数是负数的时候，无法拼凑括号，返回空list
     *
     * high level: 使用DFS的生成有效括号方案解答
     * mid level: 在之前的方案之上，加一个stack，用于保存未匹配的括号。放入右括号的时候，只有和栈顶左括号匹配上才能
     *            还需要加一个优先级的概念，使用hashmap存储左括号以及对应的优先级
     *            每次放入左括号的时候，需要确定栈顶括号的优先级大于自己
     *  1、recursion tree 最多有多少层？            2l + 2m + 2n
     *  2、recursion tree 每个node最多有几种case？  最多有6种case
     *
     * time = O(6^(2l + 2m + 2n))
     * space = O(2l + 2m + 2n) = O(l + m + n)
     * heap: stack (2l + 2m + 2n)
     *       stringBuilder (2l + 2m + 2n)
     *       PS、remain O(1)
     * call stack: O(2l + 2m + 2n)
     */
    public List<String> validParenthesesIII(int l, int m, int n) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (l < 0 || m < 0 || n < 0) {
            return res;
        }

        String[] PS = {"(", ")", "<", ">", "{", "}"};
        int[] remain = {l, l, m, m, n, n};
        int targetLen = 2 * l + 2 * m + 2 * n;

        Map<String, Integer> priorityMap = new HashMap<>();
        priorityMap.put("(", 1);
        priorityMap.put("<", 2);
        priorityMap.put("{", 3);

        validParentheses(PS, remain, new ArrayDeque<>(), priorityMap, targetLen, new StringBuilder(), res);
        return res;
    }

    private void validParentheses(String[] PS, int[] remain, Deque<String> stack, Map<String, Integer> priorityMap, int targetLen, StringBuilder cur, List<String> res) {
        if (cur.length() == targetLen) {
            res.add(cur.toString());
            return;
        }

        for (int i = 0; i < PS.length; i++) {
            if (i % 2 == 0) {
                //左括号
                if (remain[i] > 0) {
                    // 当对应的左括号还有剩余时可以进入判断
                    String ps = stack.peekFirst();
                    // 只有栈顶没有元素或者栈顶括号优先级大于当前括号时，才可以放入当前括号
                    if (ps == null || priorityMap.get(ps) > priorityMap.get(PS[i])) {
                        cur.append(PS[i]);
                        stack.offerFirst(PS[i]);
                        remain[i]--;
                        validParentheses(PS, remain, stack, priorityMap, targetLen, cur, res);
                        cur.deleteCharAt(cur.length() - 1);
                        stack.pollFirst();
                        remain[i]++;
                    }
                }
            } else {
                // 右括号
                if (remain[i - 1] < remain[i] && stack.peekFirst() == PS[i - 1]) {
                    // 当对应的左扩号剩余个数小于右括号，并且栈顶括号是对应的左括号的时候，可以进入判断
                    cur.append(PS[i]);
                    stack.pollFirst();
                    remain[i]--;
                    validParentheses(PS, remain, stack, priorityMap, targetLen, cur, res);
                    cur.deleteCharAt(cur.length() - 1);
                    stack.offerFirst(PS[i - 1]);
                    remain[i]++;
                }
            }
        }
    }
}
