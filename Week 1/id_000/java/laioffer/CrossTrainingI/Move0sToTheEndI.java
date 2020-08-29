package laioffer.CrossTrainingI;

public class Move0sToTheEndI {

    public static void main(String[] args) {

    }

    /**
     * input: array int[]
     * output: int[]
     * 假设：array != null && array.length > 0
     * 如果不符合假设，那么没有需要移动的元素，直接返回input array
     *
     * high level: 使用左右挡板，相向而行解答 (不保证原先顺序)
     * mid level: 定义左右挡板left，right
     *  1、left: 在[0，left - 1]的区间内，都是不等于0的数。
     *  2、right: 在[right + 1, array.length - 1]的区间内,都是0
     *  3、init: left = 0, right = array.length - 1 (初始化的时候，两个区间内都没有元素)
     *  4、if (array[left] != 0)  left++
     *     else                   swap(array, left, right--)
     *
     * time = O(n)
     * space = O(1)
     */
    public int[] moveZero(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            if (array[left] != 0) {
                left++;
            } else {
                swap(array, left, right--);
            }
        }

        return array;
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
