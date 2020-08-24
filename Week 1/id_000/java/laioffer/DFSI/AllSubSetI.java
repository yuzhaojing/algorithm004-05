package laioffer.DFSI;


import java.util.ArrayList;
import java.util.List;

public class AllSubSetI {

    public static void main(String[] args) {
        String set = "abc";
        System.out.println(new AllSubSetI().subSets(set));
    }

    /**
     * 总共有几层？每层存储了什么东西？
     * 总共有n层 每层存储当前层选择的字符（选择字符或者不选择）
     *
     * 每层有几个可能（每个节点会有几个分支）
     * 每层有2个可能，即两个分支
     *
     * Time = O(2^n * n)
     * 分析思路:
     * 输入的字符串有几个字符，就需要递归几次，所以深度为n
     * 每层每个节点有两种可能，加上下一个字符或者不加，所以每层每个节点有2个子节点
     * time = 2 + 2^2 + 2^3 + ... + 2^n = O(2^n) * O(n) = O(2^n * n)
     *
     * Space = O(n)
     * recursion tree 有n层，根据冯诺伊曼体系，递归n层的空间复杂度为O(n)
     */
    public List<String> subSets(String set) {
        // Write your solution here.
        List<String> res = new ArrayList<>();
        if (set == null || set.length() == 0) {
            return res;
        }

        subSets(set, new StringBuilder(), 0, res);
        return res;
    }

    private void subSets(String set, StringBuilder cur, int index, List<String> res) {
        // 当index越界之后才将cur记录到res中
        // 因为只有index越界之后才没有继续判断的必要
        if (set.length()== index) {
            res.add(cur.toString());
            return;
        }

        // cur添加当前元素，表示在当前层选择了该元素
        cur.append(set.charAt(index));
        subSets(set, cur, index + 1, res);

        // cur删除当前元素，表示在当前层没有选择该元素
        cur.deleteCharAt(cur.length() - 1);
        subSets(set, cur, index + 1, res);
    }
}
