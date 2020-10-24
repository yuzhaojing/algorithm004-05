package laioffer.DynamicProgrammingIV;

public class CuttingWoodI {

    public static void main(String[] args) {
        System.out.println(new CuttingWoodI().minCost(new int[]{2, 4, 7}, 10));
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

        // cost[i]表示i到起点的距离
        // cost[i] - cost[j] = i ～ j的距离，也就是i和j中间切一刀的cost
        int[] cost = new int[cuts.length + 2];
        cost[0] = 0;
        cost[cost.length - 1] = length;
        for (int i = 0; i < cuts.length; i++) {
            cost[i + 1] = cuts[i];
        }

        // M[i][j]表示从cuts[i]到cuts[j]中间的最小cost
        int[][] M = new int[cost.length][cost.length];

        // 先将M[0][1]和M[1][2]算出来，才能算M[0][2]
        // 先将M[0][1]、M[1][2]、M[2][3]算出来，才能算M[0][3]、M[1][3]
        for (int j = 1; j < cost.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (i + 1 == j) {
                    M[i][j] = 0;
                } else {
                    M[i][j] = Integer.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        M[i][j] = Math.min(M[i][j], M[i][k] + M[k][j]);
                    }

                    M[i][j] += (cost[j] - cost[i]);
                }
            }
        }

        return M[0][cost.length - 1];
    }
}
