package laioffer.binary_search;

public class FirstOccurrence {

    public static void main(String[] args) {

    }

    public int firstOccur(int[] array, int target) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                // 寻找第一个，找到target之后，将其当作右边界，不能排除是第一个的可能性
                right = mid;
            } else if (array[mid] < target) {
                // left = mid也是对的，但是此时mid不是target，可以将其排除
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 优先判断left的值
        if (array[left] == target) {
            return left;
        }

        // 然后判断right的值
        if (array[right] == target) {
            return right;
        }

        // 都不是target则返回-1
        return -1;
    }
}
