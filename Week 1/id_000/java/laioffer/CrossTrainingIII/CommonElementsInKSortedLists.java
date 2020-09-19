package laioffer.CrossTrainingIII;

import java.util.ArrayList;
import java.util.List;

public class CommonElementsInKSortedLists {

    public static void main(String[] args) {

    }

    /**
     * input: List<List<Integer>> input
     * output: List<Integer>
     * Assume: input != null && input.size() > 0
     * 如果不符合假设，没有相同元素返回空list
     *
     * high level: 使用三种方式可以做到，比较优劣势
     * detail level:
     *  1、iterative: 将一个list和另一个list进行比较，求得的公共元素再和第三个比较，依此累推
     *     time = 2n + 2n + ... + 2n = 2kn = O(kn) space = O(n)
     *  2、binary reduction: 将list进行两两分组，分别求得公共元素再重复比较，直到最后一个
     *     time = 2n * k/2 + 2n * k/4 + ... + 2n * 1 = 2nk = O(kn)
     *     space = n * k/2 + n * k/4 + ... + n * 1 = nk = O(kn)
     *  3、k-way: 由于只能pop出最小的元素，并不能同时对比不同数组的最小元素，非常难以实现
     */
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (input == null || input.size() == 0) {
            return res;
        }

        res = input.get(0);
        for (int i = 1; i < input.size(); i++) {
            res = helper(res, input.get(i));
        }

        return res;
    }

    private List<Integer> helper(List<Integer> a, List<Integer> b) {
        // 这里可以进行抉择，可以使用O(n)的时间清空一个helper list，这样可以节省O(n)的空间
        List<Integer> common = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i).equals(b.get(j))) {
                common.add(a.get(i));
                i++;
                j++;
            } else if (a.get(i) < b.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return common;
    }
}
