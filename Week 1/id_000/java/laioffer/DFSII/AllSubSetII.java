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
     * 假设：set != null
     * 如果不符合假设，那么对于null来说，是不存在subset的，返回空list
     * input: string || char[] (需要求subset的元素集合)
     * output: List<String> (返回subset的全部可能性)
     * high level: 用DFS中的subset方案解答
     * mid level: 使用subset的方案解答，不过需要将输入的string转成char array进行排序
     * 然后对char array计算subset的时候，如果不选择某个元素，就将相同元素全部skip掉
     * 1、递归树最多有多少层？ 假设input的string有n个字符，那么就有n层，每层决定一个字符。
     * 2、每个node最多有几种case？每个node最多有两种case，选择或者不选择
     * time = O(2^n * n + nlogn) = O(2^n * n)
     * 递归树最后一层有2^n个node，每个node里面需要花费O(n)的时间遍历subset结果放入list中
     * space = O(n) call stack
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
