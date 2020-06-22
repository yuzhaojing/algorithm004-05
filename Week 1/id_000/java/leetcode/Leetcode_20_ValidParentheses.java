package leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_20_ValidParentheses {

    public static void main(String[] args) {
        System.out.println(method1("{}[]()"));
    }

    private static boolean method1(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                } else return false;
            }
        }

        return stack.isEmpty();
    }

}
