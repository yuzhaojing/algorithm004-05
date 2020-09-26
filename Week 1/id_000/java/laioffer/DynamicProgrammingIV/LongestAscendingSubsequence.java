package laioffer.DynamicProgrammingIV;

public class LongestAscendingSubsequence {

    public static void main(String[] args) {
        System.out.println(new LongestAscendingSubsequence().longestBinary(new int[] {1, 2, 3, 1, 1, 1}));
    }

    /**
     * input:  int[] array
     * output: int
     * Assume: array != null && array.length > 0
     *
     * high level: 使用一维DP，binary scan回头看解答
     * detail level:
     *  1、记录一个序列，里面存储的是同样长度序列的最小值
     *  2、每次遍历到一个元素的时候，去尝试改善序列，其实就是找到比当前元素小的最大值，无法改善就追加元素
     *     原因:因为前面都是比当前元素小的元素，当前元素能构成最长的升序子序列就是找到的位置
     *         又因为当前元素小于该元素，所以替换掉下一个元素可以使得该长度的解优化
     *  3、base case: lowestEnding[1] = array[0];
     *
     * time = O(nlogn)
     * space = O(n)
     */
    public int longestBinary(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }

        // lowestEnding[i]表示长度为i的升序序列的最优解
        // 所以index = 0无意义，需要比array多一个空间
        int[] lowestEnding = new int[array.length + 1];
        lowestEnding[1] = array[0];
        int res = 1;

        for (int i = 1; i < array.length; i++) {
            int index = smallerLargest(lowestEnding, 1, res, array[i]);
            lowestEnding[index + 1] = array[i];
            if (index == res) {
                res++;
            }
        }

        return res;
    }

    // 找比target小的最大值，如果找不到，则表示当前元素是序列中最小的元素
    // 此时要更新的元素是index = 1的元素，由于我们需要更新的是result + 1的元素，所以返回0
    // 找比target小的最大值和找比target大的最小值相比，又一个好处，那就是不需要考虑target在序列中已经存在的问题
    // 因为target如果存在，必定在result + 1的位置，更新无影响。但是找比target大的最小值则需要考虑target在result - 1的位置
    private int smallerLargest(int[] lowestEnding, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (lowestEnding[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        if (lowestEnding[right] < target) {
            return right;
        }

        if (lowestEnding[left] < target) {
            return left;
        }

        return 0;
    }

    /**
     * input:  int[] array
     * output: int
     * Assume: array != null && array.length > 0
     * <p>
     * high level: 使用一维DP，linear scan回头看解答
     * detail level:
     * 1、base case: M[0] = 1;
     * induction rule: M[i] = Max(M[j]) + 1 where 0 <= j < i && M[j] < M[i]
     * <p>
     * time = O(n^2)
     * space = O(n)
     */
    public int longest(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }

        int[] M = new int[array.length];
        M[0] = 1;

        int maxLen = 0;

        for (int i = 0; i < array.length; i++) {
            M[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    M[i] = Math.max(M[i], M[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, M[i]);
        }

        return maxLen;
    }
}
