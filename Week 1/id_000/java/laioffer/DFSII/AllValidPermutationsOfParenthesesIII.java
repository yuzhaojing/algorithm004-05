package laioffer.DFSII;


import java.util.*;

public class AllValidPermutationsOfParenthesesIII {

    public static void main(String[] args) {
        System.out.println(new AllValidPermutationsOfParenthesesIII().validParenthesesIII(1, 1, 0));
    }

    // 假设：l >= 0 && m >= 0 && n >= 0
    // 如果这三个参数有一个小于0，那么-1对括号无法表示，所以暂定返回空list

    // high level: 使用DFS中的生成有效括号的方法解决
    // mid level: 每层最多几个叉 - 6个 / 共有多少层 - 2(l + m + n)
    //  1、使用一个stack记录放入的左括号
    //  2、加左括号的时候，确认栈顶的元素是优先级大于自己的左括号
    //  3、加右括号的时候，确认栈顶元素是对应的左括号

    /**
     * 假设：l >= 0 && m >= 0 && n >= 0
     * 如果这三个参数有一个小于0，那么-1对括号无法表示，所以暂定返回空list
     * high level: 使用DFS中的生成有效括号的方法解决
     * mid level: 每层最多几个叉 - 6个 / 共有多少层 - 2(l + m + n)
     *  1、使用一个stack记录放入的左括号
     *  2、加左括号的时候，确认栈顶的元素是优先级大于自己的左括号
     *  3、加右括号的时候，确认栈顶元素是对应的左括号
     *
     * time = O(2^n * n)
     * space = O(l + m + n)
     * PS、remain、map存储的数据大小都是常量，space = O(1)
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
