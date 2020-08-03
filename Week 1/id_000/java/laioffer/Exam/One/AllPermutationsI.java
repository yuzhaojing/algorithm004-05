package laioffer.Exam.One;


import java.util.ArrayList;
import java.util.List;

public class AllPermutationsI {

    public static void main(String[] args) {
        System.out.println(new AllPermutationsI().permutations("abc"));
    }

    /**
     * 总共有几层？每层存储了什么东西？
     * 总共有n层 每层存储当前层选择的字符（选择未选择字符中的任意一个）
     *
     * 每层有几个可能（每个节点会有几个分支）
     * 第一层有n个，以下每一层的分支递减一个
     *
     * Time = O(2^n)
     * 分析思路:
     * 第一层有n个node，以下每一层的分支递减一个，所以
     * 第二层有n * (n - 1)个node
     * 第三层有n * (n - 1) * (n - 2)个node
     * 到第n-1层n * (n - 1) * (n - 2) * ... * 2 = n! 个node
     * 到第n层n * (n - 1) * (n - 2) * ... * 1 = n! 个node
     * 等比数列只要比值大于2，最后一项一定大于之前所有之和
     * 而第n-1之前的所有层与前一层的比值都大于2
     * 所以总共为n!个node
     *
     * 每个叶子节点都需要用O(n)的时间将数据拷贝到结果集中
     * time = n! * n = O(n! * n)
     *
     *
     * Space = O(n)
     */
    public List<String> permutations(String input) {
        // Write your solution here
        List<String> res = new ArrayList<>();
        if (input == null) {
            return res;
        }

        char[] array = input.toCharArray();

        permutations(array, 0, res);
        return res;
    }

    private void permutations(char[] array, int index, List<String> res) {
        if (array.length == index) {
            res.add(new String(array));
            return;
        }

        // 由于是全排列，所以每一层都要将剩余的所有元素都选一遍
        for (int i = index; i < array.length; i++) {
            // 将选择的元素swap到当前层的位置
            // 进入下一层的时候，由于index增加了，所以该元素就会被忽略
            swap(array, i, index);
            permutations(array, index + 1, res);
            // 结束当次递归时，将数组还原
            swap(array, i, index);
        }
    }

    private void swap(char[] array, int left, int right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
