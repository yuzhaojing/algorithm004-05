/**
 * 33. 搜索旋转排序数组
 */
public class Leetcode_704_BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        System.out.println(method1(nums, 3));
    }

    private static int method1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else right = mid - 1;
        }
        return -1;
    }
}

