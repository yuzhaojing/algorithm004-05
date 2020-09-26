package laioffer.DynamicProgrammingIV;

public class CanIWInII {

    /**
     * input:  int[] nums
     * output: int
     * Assume: num != null && nums.length > 1
     *
     * high level: 使用二维DP，两头凑的方式解答
     * detail level:
     *  1、M[i][j]从第i个元素到第j个元素之间，能够拿到的最大的元素之和
     *  2、base case: M[i][i] = nums[i]; M[i][i + 1] = max(nums[i], nums[i + 1])
     *     induction rule: M[i][j] = max (nums[i] + M[i + 2][j]      if (nums[i + 1] > nums[j])
     *                                    nums[i] + M[i + 1][j - 1]  if (nums[i + 1] < nums[j])
     *                                    nums[j] + M[i + 1][j - 1]  if (nums[i] > nums[j - 1])
     *                                    nums[j] + M[i][j - 2]      if (nums[i] < nums[j - 1])
     *                                   )
     *
     * time = O(n^2)
     * space = O(n^2)
     */
    public int canWin(int[] nums) {
        // Write your solution here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int[][] M = new int[nums.length][nums.length];
        for (int j = 0; j < nums.length; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    M[i][j] = nums[i];
                } else if (i + 1 == j) {
                    M[i][j] = Math.max(nums[i], nums[j]);
                } else {
                    // 选择i
                    if (nums[i + 1] > nums[j]) {
                        M[i][j] = nums[i] + M[i + 2][j];
                    } else {
                        M[i][j] = nums[i] + M[i + 1][j - 1];
                    }

                    // 选择j
                    if (nums[i] > nums[j - 1]) {
                        M[i][j] = Math.max(M[i][j], nums[j] + M[i + 1][j - 1]);
                    } else {
                        M[i][j] = Math.max(M[i][j], nums[j] + M[i][j - 2]);
                    }
                }
            }
        }

        return M[0][nums.length - 1];
    }
}
