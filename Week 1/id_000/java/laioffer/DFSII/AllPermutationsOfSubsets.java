package laioffer.DFSII;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPermutationsOfSubsets {

    public static void main(String[] args) {
        String set = "abc";
        System.out.println(new AllPermutationsOfSubsets().allPermutationsOfSubsets(set));
    }

    /**
     * input: string
     * output: List<String> (返回input的subset集合的permutation)
     * 假设：set != null
     * 如果不符合假设，则没有subset，那么也就没有permutation，所以返回空list
     *
     * high level: 使用DFS的permutation方案解答
     * mid level: 在使用之前的方案的时候，将每层的index之前的元素放入结果集，这个就是答案
     *  1、recursion tree 最多有多少层？            如果input的长度为n，那么最多就有n层
     *  2、recursion tree 每个node最多有几种case？  第一层有n种，第二层有n - 1种 。。。 最后一层1种
     *
     * time = O(n! * n)
     * space = O(n)
     * heap: cur list (n)
     * call stack: O(n)
     */
    public List<String> allPermutationsOfSubsets(String set) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }

        allPermutationsOfSubsets(set.toCharArray(), 0, res);
        return res;
    }

    private void allPermutationsOfSubsets(char[] input, int index, List<String> res) {
        res.add(new String(input, 0, index));

        if (input.length == index) {
            return;
        }

        for (int i = index; i < input.length; i++) {
            swap(input, i, index);
            allPermutationsOfSubsets(input, index + 1, res);
            swap(input, i, index);
        }
    }

    private void swap(char[] array, int left, int right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
