package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_50_Pow {

    public static void main(String[] args) {
        System.out.println(method1(2, -2));
    }

    private static double method1(double x, int n) {
        return n > 0 ? pow(x, n) : 1.0 / pow(x, n);
    }

    private static double pow(double x, int n) {
        if (n == 0) return 1;
        double half = pow(x, n / 2);
        return n % 2 == 0 ? half * half : half * half * x;
    }
}
