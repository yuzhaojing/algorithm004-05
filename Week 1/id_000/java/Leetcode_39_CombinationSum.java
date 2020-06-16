import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 1、两数之和
 */
public class Leetcode_39_CombinationSum {

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 7};
        System.out.println(method1(nums, 7));
    }

    private static List<List<Integer>> method1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        if (candidates == null || candidates.length == 0) return res;

        Arrays.sort(candidates);

        CombinationSum(res, new LinkedList<>(), candidates, target, 0);

        return res;
    }

    private static void CombinationSum(List<List<Integer>> res,
                                       LinkedList<Integer> tmp,
                                       int[] candidates,
                                       int target,
                                       int index) {
        if (target == 0) {
            res.add(new LinkedList<>(tmp));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (target < candidates[i]) break;

            tmp.addLast(candidates[i]);
            CombinationSum(res, tmp, candidates, target - candidates[i], i);
            tmp.removeLast();
        }
    }

}
