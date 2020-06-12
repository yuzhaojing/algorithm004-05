import java.util.Arrays;
import java.util.HashMap;

/**
 * 1、两数之和
 */
public class Leetcode_121_BestTimeStock {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int res = method1(nums);
        System.out.println(res);
    }

    private static int method1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int max = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j]- prices[i]);
            }
        }

        return max;
    }

    private static int method2(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                max = Math.max(max, prices[i] - min);
            }
        }

        return max;
    }
}
