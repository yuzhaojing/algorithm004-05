package laioffer.binary_search;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5, 9, 17, 21};
//        System.out.println(binarySearch(nums, 8));

//        int[][] nums = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
//        System.out.println(binarySearch2D(nums, 20));

//        int[] nums = {1, 2, 3, 4, 5, 9, 17, 21};
//        System.out.println(binarySearchClosest(nums, 6));

//        int[] nums = {1, 2, 3, 4, 5, 5, 5, 5, 9, 17, 21};
//        System.out.println(binarySearchFirstIndex(nums, 4));

        int[] nums = {1, 2, 3, 4, 5, 5, 5, 5, 9, 17, 21};
        System.out.println(Arrays.toString(binarySearchClosestK(nums, 5, 5)));
    }

    // Classical Version
    private static boolean binarySearch(int[] nums, int target) {
        // Base Case
        if (nums == null || nums.length == 0) return false;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                // left = mid也可以，这里使用left = mid + 1是因为mid一定不是需要的结果
                left = mid + 1;
            } else {
                // 原因同上
                right = mid - 1;
            }
        }

        return false;
    }

    // 搜索二维数组
    private static boolean binarySearch2D(int[][] nums, int target) {
        if (nums == null || nums.length == 0 || nums[0].length == 0) return false;

        int rows = nums.length;
        int cols = nums[0].length;

        int left = 0;
        int right = nums.length * nums[0].length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / rows;
            int col = mid % cols;

            if (nums[row][col] == target) {
                return true;
            } else if (nums[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    // 搜索大小和target最接近的数
    // 不是求具体值，而是求第一个、最后一个，最接近等问题模版
    private static int binarySearchClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] < target) {
                // 这里不能使用 left = mid + 1,因为mid有可能是离target最接近的数
                left = mid;
            } else {
                right = mid;
            }
        }

        return Math.abs(nums[left] - target) <
                Math.abs(nums[right] - target) ?
                nums[left] : nums[right];
    }

    // 搜索大小和target最接近的k个数
    private static int[] binarySearchClosestK(int[] nums, int target, int k) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0) return res;

        int left = 0;
        int right = nums.length - 1;


        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid;
                right = mid;
                break;
            }
        }

        while (right - left + 1 < k) {
            left--;
            right++;

            if (right - left + 1 > k) {
                if (target - nums[left] < nums[right] - target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        res[0] = left;
        res[1] = right;

        return res;
    }

    // 搜索目标元素的第一个角标
    private static int binarySearchFirstIndex(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 求的是第一个，所以往左找
                // 不能使用right = mid - 1,因为mid有可能是第一个
                right = mid;
            } else if (nums[mid] < target) {
                // 可以使用left = mid + 1,因为mid的值不是target，可以舍弃
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (nums[left] == target) return left;
        if (nums[right] == target) return right;

        return -1;
    }

    /**
     * 因为给定数组无法知道确切的长度，无法找到中点
     * 但是可以根据二分查找逆向思维，从第一个元素往后查找
     * 每次查找之后，下次查找的间隔*2，这样就可以使得算法复杂度为O(log(n))
     *
     * 思考：每次查找*2和每次查找*10哪个更快？
     * 定性分析：
     *          jump out          jump in
     * 2         较慢                较快
     * 10        较快                较慢
     * 定量分析：
     *          jump out          jump in
     * 2        log2(n)            log2(n)
     * 10       log10(n)           log2(n)
     *
     * 结论：很明显，在n无限大的情况下，两者jump in的时间复杂度一致
     *      但是在jump out的时间复杂度log10(n)明显比log2(n)小
     *      所以*10更快
     *
     *
     * 给定一个升序整数数组，写一个函数搜索 nums 中数字 target。如果 target 存在，返回它的下标，否则返回 -1。注意，这个数组的大小是未知的。你只可以通过 ArrayReader 接口访问这个数组，ArrayReader.get(k) 返回数组中第 k 个元素（下标从 0 开始）。
     *
     * 你可以认为数组中所有的整数都小于 10000。如果你访问数组越界，ArrayReader.get 会返回 2147483647。
     */

    private static int binarySearchUnknownLength(int[] nums, int target) {
        int step = 1;
        int index = 0;
        while (nums[index] < target) {
            step *= 2;
            index += step;
        }

        if (nums[index] == target) return index;

        int left = 0;
        int right = index - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
