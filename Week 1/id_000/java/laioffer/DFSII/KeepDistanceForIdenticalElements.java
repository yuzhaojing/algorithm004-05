package laioffer.DFSII;


import java.util.*;

public class KeepDistanceForIdenticalElements {

    public static void main(String[] args) {
        new KeepDistanceForIdenticalElements().keepDistance(2);
    }

    /**
     * 假设：k > 0
     * 如果k <= 0，那么没有有效解，暂定都返回null
     *
     * high level: 使用DFS来的permutation方案进行解答
     * (如果给定元素全部出现在结果中，只是顺序不一样，用permutation方案)
     * mid level: 共有多少层 - k层 | 每层几个叉 - 最多2k - 1个叉
     *  1、建立一个大小为2k的数组
     *  2、从k开始枚举放法
     *
     * time = O(k^2k)
     * space = O(2k) + O(k) = O(k)
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
