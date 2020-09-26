package laioffer.DynamicProgrammingIV;

public class CuttingWoodI {

    public static void main(String[] args) {
        System.out.println(new CuttingWoodI().minCost(new int[]{3, 16, 19, 28, 37, 44, 47, 48, 51, 52, 62}, 67));
    }

    // input:  int[] cuts
    //         int length
    // output: int
    // Assume: cuts != null && cut.length > 0 && length > cuts[cuts.length - 1]

    // high level: 使用二维DP，由于不是每一段都均匀可分，使用按块切的方式解答
    // detail level:
    //  1、M[i][j]表示从cuts[i]切到cuts[j]所需要的最小cost
    //     base case: M[0][1] = 0; M[1][2] = 0; M[2][3] = 0; M[3][4] = 0;
    //     induction rule:
    //
    public int minCost(int[] cuts, int length) {
        // Write your solution here
        if (cuts == null || cuts.length == 0 || cuts[cuts.length - 1] > length) {
            return 0;
        }

        int[] cost = new int[cuts.length + 2];
        int[][] M = new int[cost.length][cost.length];

        for (int i = 0; i < cuts.length; i++) {
            cost[i + 1] = cuts[i];
        }

        cost[cost.length - 1] = length;

        // 先将M[0][1]和M[1][2]算出来，才能算M[0][2]
        // 先将M[0][1]、M[1][2]、M[2][3]算出来，才能算M[0][3]、M[1][3]
        for (int i = 1; i < cost.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (j == i - 1) {
                    // base case
                    M[j][i] = 0;
                } else {
                    M[j][i] = Integer.MAX_VALUE;
                    // 枚举木头的所有切割方式，计算出消耗最小的
                    for (int k = j + 1; k <= i - 1; k++) {
                        M[j][i] = Math.min(M[j][i], M[j][k] + M[k][i]);
                    }

                    // 加上第一刀的消耗
                    M[j][i] += (cost[i] - cost[j]);
                }
            }
        }

        return M[0][cost.length - 1];
    }
}
