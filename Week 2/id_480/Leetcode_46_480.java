//给定一个没有重复数字的序列，返回其所有可能的全排列。
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Leetcode_46_480 {
    public static void main(String[] args) {
        Solution solution = new Leetcode_46_480().new Solution();
        List<List<Integer>> permute = solution.permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void backtrack(int n,
                              ArrayList<Integer> nums,
                              List<List<Integer>> output,
                              int first) {
            // if all integers are used up
            if (first == n)
                output.add(new ArrayList<Integer>(nums));
            for (int i = first; i < n; i++) {
                // place i-th integer first
                // in the current permutation
                Collections.swap(nums, first, i);
                // use next integers to complete the permutations
                backtrack(n, nums, output, first + 1);
                // backtrack
                Collections.swap(nums, first, i);
            }
        }

        public List<List<Integer>> permute(int[] nums) {
            // init output list
            List<List<Integer>> output = new LinkedList();

            // convert nums into list since the output is a list of lists
            ArrayList<Integer> nums_lst = new ArrayList<Integer>();
            for (int num : nums)
                nums_lst.add(num);

            int n = nums.length;
            backtrack(n, nums_lst, output, 0);
            return output;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}