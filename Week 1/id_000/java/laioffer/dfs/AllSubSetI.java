package laioffer.dfs;


import java.util.ArrayList;
import java.util.List;

public class AllSubSetI {

    public static void main(String[] args) {
        String set = "abc";
        System.out.println(allSubSet(set));
    }

    /**
     * 时间复杂度O(2^n)
     * 分析思路:
     * 输入的字符串有几个字符，就需要递归几次，所以深度为n
     * 每层每个节点有两种可能，加上下一个字符或者不加，所以每层每个节点有2个子节点
     *
     * 空间复杂度O(n)
     */
    private static List<String> allSubSet(String set) {
        List<String> res = new ArrayList<>();
        if (set == null || set.length() == 0) return res;

        allSubSetHelper(set, res, "", 0);
        return res;
    }

    private static void allSubSetHelper(String set, List<String> res, String tmp, int index) {
        if (set.length() == index) {
            res.add(tmp);
            return;
        }

        allSubSetHelper(set, res, tmp.concat(String.valueOf(set.charAt(index))), index + 1);
        allSubSetHelper(set, res, tmp, index + 1);
    }
}
