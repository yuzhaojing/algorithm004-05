package laioffer.stringII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AllPermutationsII {

    public static void main(String[] args) {
        System.out.println(new AllPermutationsII().permutations("ACBCD"));
    }

    /**
     * 由于input中有重复元素，所以必须保证每一层同一个元素只使用一次
     * 所以在每一个递归调用的时候构建一个hashset进行去重
     *
     * time = O(n! * n)
     * 剪枝不会降低时间复杂度的upper bound，lower bound太难计算，暂时不管
     *
     * space = O(n! * n)
     * 每个node都有几个hashset，所以一共是n!个node
     * 每个hashset最坏存n个元素
     */
    public List<String> permutations(String input) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (input == null) {
            return res;
        }

        permutations(input.toCharArray(), 0, res);
        return res;
    }

    private void permutations(char[] array, int index, List<String> res) {
        if (array.length == index) {
            res.add(new String(array));
            return;
        }

        HashSet<Character> set = new HashSet<>();
        for (int i = index; i < array.length; i++) {
            if (!set.contains(array[i])) {
                set.add(array[i]);
                swap(array, i, index);
                permutations(array, index + 1, res);
                swap(array, i, index);
            }
        }
    }

    private void swap(char[] array, int left, int right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
