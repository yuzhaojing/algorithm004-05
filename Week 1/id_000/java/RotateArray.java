import java.util.Arrays;

/**
 * 189. 旋转数组
 *
 * 1、用当前角标 (i + k) % nums.length算出旋转后的位置，
 *    将数据复制到新的数组中去
 * 2、
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] nums = new int[]{-1};
        int k = 2;
        method3(nums, k);
        System.out.println("nums = " + Arrays.toString(nums));
    }

    private static void method1(int[] nums, int k) {
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[(i + k) % nums.length] = nums[i];
        }

        System.arraycopy(copy, 0, nums, 0, nums.length);
    }

    private static void method2(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int a = nums[nums.length - 1];
            for (int j = 0; i < nums.length; i++) {
                int temp = nums[j];
                nums[j] = a;
                a = temp;
            }
        }
    }

    private static void method3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
