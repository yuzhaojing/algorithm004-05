package laioffer.CrossTrainingIII;

public class MaxWaterTrappedI {

    public static void main(String[] args) {
        new MaxWaterTrappedI().maxTrappedDP(new int[]{5, 3, 2, 1, 4, 6});
    }

    /**
     * input: int[] array
     * output: int
     * Assume: array != null && array.length > 0
     * 如果不符合假设，没有元素返回-1
     *
     * high level: 使用一维DP解答
     * detail level:
     *  1、遍历两次数组，计算从两个方向看最大的值
     *  2、再次遍历数组，计算每个元素收集的水 Min(leftMax, rightMax) - array[index]
     *  3、将每个元素收集的水累加起来，求得总共收集的水
     *
     * time: 3n = O(n)
     * space: 2n = O(n)
     */
    public int maxTrappedDP(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }

        int[] leftMax = new int[array.length];
        leftMax[0] = array[0];

        int[] rightMax = new int[array.length];
        rightMax[array.length - 1] = array[array.length - 1];

        for (int i = 1; i < array.length; i++) {
            leftMax[i] = Math.max(array[i], leftMax[i - 1]);
        }

        for (int i = array.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(array[i], rightMax[i + 1]);
        }

        int res = 0;
        for (int i = 0; i < array.length; i++) {
            res += (Math.min(leftMax[i], rightMax[i]) - array[i]);
        }

        return res;
    }

    /**
     * input: int[] array
     * output: int
     * Assume: array != null && array.length > 0
     * 如果不符合假设，没有元素返回-1
     *
     * high level: 使用双指针解答
     * detail level:
     *  1、左右指针相向而行，初始leftMax和rightMax等于array[0]和array[array.length - 1]
     *  2、if (leftMax < rightMax) sum += (leftMax - array[index]) left++
     *     else                    sum += (rightMax - array[index]) right--
     *
     * time: O(n)
     * space: O(1)
     */
    public int maxTrapped(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }

        int left = 0;
        int right = array.length - 1;

        int leftMax = array[left];
        int rightMax = array[right];

        int res = 0;

        while (left <= right) {
            if (leftMax < rightMax) {
                res += (leftMax - array[left]);
                left++;
                leftMax = Math.max(leftMax, array[left]);
            } else {
                res += (rightMax - array[right]);
                right--;
                rightMax = Math.max(rightMax, array[right]);
            }
        }

        return res;
    }
}
