package laioffer.Exam.Final;

import java.util.Map;

public class MinmunNumOfCut {

    public static void main(String[] args) {
        System.out.println(new MinmunNumOfCut().packingUpSwags(4));
        System.out.println(new MinmunNumOfCut().packingUpSwags(10));
        System.out.println(new MinmunNumOfCut().packingUpSwags(20));
    }

    public int packingUpSwags(int num) {
        if (num <= 0) {
            return 0;
        }

        // 需要有0来对应糖的数量为0的情况
        int[] M = new int[num + 1];

        // base case
        M[0] = 0;
        M[1] = 1;

        for (int i = 2; i <= num; i++) { // 枚举糖的数量

            // 最少需要1个箱子
            M[i] = Integer.MAX_VALUE;;

            for (int j = 1; j * j <= i; j++) { // 枚举右小段的平方根
                // 读取左大段的值，将最小值赋值给M[i]，1为右小段需要的箱子数量
                M[i] = Math.min(M[i], M[i - j * j] + 1);
            }
        }

        // 返回当糖的数量为num的时候，需要的箱子数量
        return M[num];
    }

    /**
     * input:  int num
     * output: int
     * Assume: num > 0
     *
     * high level: 使用一维DP，linear scan回头看
     * detail level:
     *  1、M[i]表示当num等于i的时候，最少需要分成多少段
     *  2、base case: M[0] = 0, M[1] = 1
     *     induction rule: M[i] = min(M[j] + M[i - j]) where 0 < j < i
     *
     * time = O(n * )
     * space = O(n)
     */
    public int MinmunNumOfCut(int num) {
        if (num <= 0) {
            return 0;
        }

        int[] M = new int[num + 1];
        M[0] = 0;
        M[1] = 1;

        for (int i = 2; i <= num; i++) {
            M[i] = Integer.MAX_VALUE;
            // 枚举右小段,j为右小段的底数
            for (int j = 1; j * j <= i; j++) {
                M[i] = Math.min(M[i], M[i - j * j] + 1);
            }
        }

        return M[num];
    }
}
