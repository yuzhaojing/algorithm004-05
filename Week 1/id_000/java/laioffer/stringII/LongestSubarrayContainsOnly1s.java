package laioffer.stringII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubarrayContainsOnly1s {

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 0, 1, 1, 1, 0, 0, 0};
        System.out.println(new LongestSubarrayContainsOnly1s().longestConsecutiveOnes(nums, 2));
    }

    /**
     * 滑动窗口求最多包含k个0的最长子串长度
     * 1.双指针
     *   slow 字符串的起始角标
     *   i 字符串的结束角标
     * 2.记录0出现的次数，一旦超过k次，移动slow直到0出现次数小于等于k
     * 3.每轮循环记录slow～i最大长度
     *
     * time = O(n)
     * space = O(1)
     */
    public int longestConsecutiveOnes(int[] nums, int k) {
        // Write your solution here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLen = 0;
        int zeros = 0;
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
                while (zeros > k) {
                    if (nums[slow++] == 0) {
                        zeros--;
                    }
                }
            }
            maxLen = Math.max(maxLen, i - slow + 1);
        }

        return maxLen;
    }
}
