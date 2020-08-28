package laioffer.DFSII;


import java.util.*;

public class AllSubsetsIIOfSizeK {

    public static void main(String[] args) {
        String set = "aaaab";
        System.out.println(new AllSubsetsIIOfSizeK().subSetsIIOfSizeK1(set, 2));
    }

    /**
     * input: set string (求subset的元素集合)
     *        k int (返回长度为k的subset结果)
     * output: List<String> (返回长度为k的subset结果)
     * 假设：set != null && k >= 0
     * 如果不符合假设，则没有符合要求的subset，那么返回空list
     *
     * high level: 使用DFS的subset方案解答
     * mid level: 在使用之前的方案的时候，加一个base case和一个跳过规则
     *            加一个base case，当cur.length() == k的时候，加入结果集
     *            加一个跳过规则，当某个元素不被选择的时候，跳过后续同样的元素
     *  1、recursion tree 最多有多少层？            如果set的长度为n，那么最多就有n层
     *  2、recursion tree 每个node最多有几种case？  2种，选择或者不选择
     *
     * time = O(2^n * k + nlogn)
     * space = O(n)
     * heap: cur list (n)
     * call stack: O(n)
     */
    public List<String> subSetsIIOfSizeK(String set, int k) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (set == null || k < 0) {
            return res;
        }

        char[] input = set.toCharArray();
        Arrays.sort(input);
        subSets(input, 0, new StringBuilder(), res, k);
        return res;
    }

    private void subSets(char[] input, int index, StringBuilder cur, List<String> res, int k) {
        if (cur.length() == k) {
            res.add(cur.toString());
            return;
        }

        if (input.length == index) {
            return;
        }

        cur.append(input[index]);
        subSets(input, index + 1, cur, res, k);
        cur.deleteCharAt(cur.length() - 1);

        while (index < input.length - 1 && input[index + 1] == input[index]) {
            index++;
        }

        subSets(input, index + 1, cur, res, k);
    }

    // 假设：set != null && k >= 0
    // 如果set == null，那么将没有subset。如果k < 0,显而易见不可能有size < 0的subset
    // 所以这两种情况都不可能有result，暂定返回空的list

    // hisg level: 使用DFS中的subset方案解答
    // mid level: 每层分几个叉 - 2个 / 总共多少层 - set的长度，假设为n，那就是n层
    //  1、正常的subset计算流程
    //  2、添加一个base case，如果subset的长度达到k，直接假如结果集并返回
    //  3、将输入元素进行排序
    //  4、在不选这个元素的时候可以将后面相同的元素全部跳过去

    // time = O(2^n * n) + O(nlogn) = O(2^n * n)
    // 排序 nlogn
    // 递归 2^n 最后一层需要n的时间将元素copy进数组 2^n * n
    // space = O(n)

    // 如果不排序
    // 3、使用一个hashset存储不选择的元素，当第一次不选这个元素的时候，将该元素放入hashset
    // 4、每次遇到hashset中有的元素的时候，不挑选该元素

    // 相比排序的做法，少了O(nlogn)的排序时间，以及O(n)的数组空间
    // 但是需要多用一个O(n)的hashset
    // 以下做法有重复问题，以后思考解决
    public List<String> subSetsIIOfSizeK1(String set, int k) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (set == null || k < 0) {
            return res;
        }

        char[] input = set.toCharArray();
        subSets(input, 0, new StringBuilder(), res, k, new HashSet<>());
        return res;
    }

    private void subSets(char[] input, int index, StringBuilder cur, List<String> res, int k, Set<Character> skipElements) {
        if (cur.length() == k) {
            res.add(cur.toString());
            return;
        }

        if (input.length == index) {
            return;
        }

        if (!skipElements.contains(input[index])) {
            cur.append(input[index]);
            subSets(input, index + 1, cur, res, k, skipElements);
            cur.deleteCharAt(cur.length() - 1);
        }
        skipElements.add(input[index]);
        subSets(input, index + 1, cur, res, k, skipElements);
        skipElements.remove(input[index]);
    }
}
