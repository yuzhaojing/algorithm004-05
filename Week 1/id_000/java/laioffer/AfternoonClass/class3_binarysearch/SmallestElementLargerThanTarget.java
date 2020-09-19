package laioffer.AfternoonClass.class3_binarysearch;

public class SmallestElementLargerThanTarget {

    /**
     * 1、找出比target大的最小元素
     *
     * input:  int[] array
     * output: int
     * Assume: array != null && array.length > 0
     *
     * high level: 使用binary search解答
     * detail level: 将元素分为两部分，一部分小于等于target，必然不是解。另一部分大于target，有可能是解
     *  1、无法在循环内直接确定解，所以使用left + 1 < right作为循环条件
     *  2、if (array[mid] <= target) left = mid + 1
     *    else                      right = mid
     *  3、if (array[left] > target)         return array[left]
     *     else if (array[right] > target)  return array[right]
     *     else                             return -1;
     *
     * time = O(logN)
     * space = O(1)
     */
    public int smallestElementLargerThanTarget(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (array[left] > target) {
            return left;
        }

        if (array[right] > target) {
            return right;
        }

        return -1;
    }
}
