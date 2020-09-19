package laioffer.AfternoonClass.class3_binarysearch;

public class FindLargestElementInDescOrder {

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 3, 4, 6, 2};
        System.out.println(new FindLargestElementInDescOrder().FindLargestElementInDescOrder(array));
    }

    /**
     * 1、找出先升序后降序的数组中，最大的那个
     *
     * input:  int[] array
     * output: int
     * Assume: array != null && array.length > 0
     *
     * high level: 使用binary search解答
     * detail level: 找到一个数，它的两侧都比他小，数组之外都可以看作无限小
     *   1、由于需要和两边比较，可能会有越界问题，所以当left + 1 = right的时候结束循环
     *      保证里面至少有3个元素，不会有越界问题
     *   2、if (array[mid - 1] < array[mid] && array[mid + 1] < array[mid])      // 在峰顶
     *      return mid
     *      else if (array[mid - 1] > array[mid] && array[mid + 1] > array[mid]) // 在谷底
     *      left = mid + 1 or right = mid - 1 (都可以，两边都有解)
     *      else if (array[mid - 1] > array[mid] && array[mid + 1] < array[mid]) // 在下坡
     *      right = mid - 1;
     *      else                                                                 // 在上坡
     *      left = mid + 1
     *   3、return array[left] < array[right] ? right : left
     *      此时的两个数必然在数组的最边上，由于越界是无限小，所以两个数中较大的就是峰顶
     *
     * time = O(logN)
     * space = O(1)
     */
    public int FindLargestElementInDescOrder(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid - 1] < array[mid] && array[mid + 1] < array[mid]) {
                return mid;
            } else if (array[mid - 1] > array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return array[left] < array[right] ? right : left;
    }
}
