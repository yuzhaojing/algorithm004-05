package laioffer.DynamicProgrammingII;

import java.util.Arrays;

public class ArrayHopperII {

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 0};
        System.out.println(new ArrayHopperII().minJump(array));
    }


    /**
     * 假设：array != null && array.length > 0
     * 如果不符合假设，那么需要和面试官讨论，是算0还是-1，暂定0
     * high level: 使用一维DP进行解答
     * mid level: linear scan回头看，每次回头看能跳跃到的所有的情
     *  1、M[i]表示从array[i]跳到最后一个元素所需要的最少步数
     *  2、base case: M[array.length - 1] = 0
     *  3、induction rule: M[i] = for j in (i, i + array[i]]
     *                            if (M[j] != -1) then Min(M[j])
     * time = O(n^2)
     * space = O(n)
     */
    public int minJump(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return -1;
        }

        // 创建一个dp数组
        // 物理意义: 存储当前元素到array数组最后一个元素所需要跳的步数
        int[] M = new int[array.length];

        // base case
        // 将数组中的元素默认设置为-1，最后一个元素设置为0，因为自己到自己不需要跳跃
        Arrays.fill(M, -1);
        M[M.length - 1] = 0;

        // i是在对array的倒数第二个元素开始遍历，遍历到第一个元素
        for (int i = M.length - 2; i >= 0; i--) {
            // 如果当前元素可以跳跃到或者越过最后一个元素，
            // 直接将dp数组中对应角标的值设为1，因为一步就可以跳过去
            if (i + array[i] >= M.length - 1) {
                M[i] = 1;
                continue;
            }

            // j是在对i所能跳跃的所有落脚点进行遍历
            for (int j = i + 1; j <= i + array[i]; j++) {
                // 只有当M[j] != -1并且此处跳跃的步数+1小于M[i]的时候更新M[i]
                // 这样相当于M[i] = Min(M[j] + 1), for j in [i + 1, i + array[i]]
                if (M[j] != -1 && (M[i] == -1 || M[i] > M[j] + 1)) {
                    M[i] = M[j] + 1;
                }
            }
        }

        return M[0];
    }
}
