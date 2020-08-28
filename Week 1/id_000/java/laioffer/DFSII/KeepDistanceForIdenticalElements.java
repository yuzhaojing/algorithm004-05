package laioffer.DFSII;

public class KeepDistanceForIdenticalElements {

    public static void main(String[] args) {
        new KeepDistanceForIdenticalElements().keepDistance(2);
    }

    /**
     * input: k int (表示需要将1～k这些数，每个一对放入int[])
     * output: int[] (返回放置好的结果集)
     * 假设：k > 0
     * 如果不符合假设，则没有符合要求的数，返回null
     *
     * high level: 使用DFS的permutation方案解答
     * mid level: 从1开始遍历到array.length - 1 - (k + 1)，每次放一对括号
     *  1、recursion tree 最多有多少层？            k对数，每次放一对数，一共k层
     *  2、recursion tree 每个node最多有几种case？  最多有2k - 1种case
     *
     * time = O(k^(2k - 1))
     * space = O(k)
     */
    public int[] keepDistance(int k) {
        // Write your solution here.
        if (k <= 0) {
            return null;
        }

        int[] res = new int[2 * k];
        return keepDistance(res, k) ? res : null;
    }

    private boolean keepDistance(int[] res, int k) {
        if (k == 0) {
            return true;
        }

        // 数组中的最后一个角标为res.length - 1
        // 最后一个满足k的左边坐标为res.length - 1 - (k + 1) = res.length - 2 - k
        for (int i = 0; i < res.length - 1 - k; i++) {
            if (res[i] == 0 && res[i + k + 1] == 0) {
                res[i] = k;
                res[i + k + 1] = k;
                if (keepDistance(res, k - 1)) {
                    return true;
                }
                res[i] = 0;
                res[i + k + 1] = 0;
            }
        }
        return false;
    }
}
