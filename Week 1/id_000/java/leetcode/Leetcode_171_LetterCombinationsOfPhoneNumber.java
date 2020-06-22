package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_171_LetterCombinationsOfPhoneNumber {

    static HashMap<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public static void main(String[] args) {
        System.out.println(method1("23"));
    }

    private static List<String> method1(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        letterCombina(res, digits, "", 0);
        return res;
    }

    private static void letterCombina(List<String> res, String digits, String str, int index) {
        if (digits.length() == index) {
            res.add(str);
            return;
        }

        String letters = phone.get(String.valueOf(digits.charAt(index)));

        for (int i = 0; i < letters.length(); i++) {
            letterCombina(res, digits, str.concat(String.valueOf(letters.charAt(i))), index + 1);
        }
    }

}
