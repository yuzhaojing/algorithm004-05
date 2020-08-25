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
     * 假设：set != null
     * 如果set == null，那么返回空list，因为null是没有subSet的
     * high level：使用DFS的permutation方案进行求解
     * mid level：
     *  1、使用swap-swap的方式求set的permutation问题
     *  2、在每次递归调用时，将当前输入的字符串的不变动部分加入结果集
     *     这部分就是这个字符串在长度为level的情况下的全部permutation
     * time = O(n! + n^2)
     * 一共n!个node，每个node花费O(1) time = O(n!)
     * 一共n层，每层需要O(n)的时间copy元素进入结果集 time = O(n^2)
     * space = O(n) call stack
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
