package dp;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_70_ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(method1(4));
    }

    private static int method1(int n) {
        if (n <= 2) return n;
        int f1 = 1;
        int f2 = 2;
        int f3 = 3;

        for (int i = 3; i < n; i++) {
            f1 = f2;
            f2 = f3;
            f3 = f1 + f2;
        }
        return f3;
    }

}
