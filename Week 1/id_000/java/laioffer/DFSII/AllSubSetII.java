package laioffer.DFSII;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSubSetII {

    public static void main(String[] args) {
        String set = "abc";
        System.out.println(new AllSubSetII().subSets(set));
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
     * 最开始的数组排序需要nlogn的时间
     *
     * worst case 每一个字符都不一样，所以递归复杂度和subSet是一样的
     * 输入的字符串有几个字符，就需要递归几次，所以深度为n
     * 每层每个节点有两种可能，加上下一个字符或者不加，所以每层每个节点有2个子节点
     * time = 2 + 2^2 + 2^3 + ... + 2^n = O(2^n) + O(nlogn) = O(2^n + nlogn) * O(n)
     *
     * Space = O(n)
     * recursion tree 有n层，根据冯诺伊曼体系，递归n层的空间复杂度为O(n)
     *
     *
     * 额外思考题：如果不使用排序怎么做？
     */
    public List<String> subSets(String set) {
        // Write your solution here.
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }

        char[] input = set.toCharArray();
        // 必须排序，否则以下方法无法使用
        Arrays.sort(input);
        subSets(input, 0, new StringBuilder(), res);
        return res;
    }

    private void subSets(char[] input, int level, StringBuilder cur, List<String> res) {
        if (level == input.length) {
            res.add(cur.toString());
            return;
        }

        cur.append(input[level]);
        subSets(input, level + 1, cur, res);
        cur.deleteCharAt(cur.length() - 1);

        // 以上都是正常的subSet流程，但是在不选择一个元素之后，接下来的同一个元素也都不选择使用
        while (level < input.length - 1 && input[level + 1] == input[level]) {
            level++;
        }
        subSets(input, level + 1, cur, res);
    }
}
