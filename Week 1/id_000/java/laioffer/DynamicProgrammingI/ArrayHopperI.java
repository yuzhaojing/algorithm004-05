package laioffer.DynamicProgrammingI;

public class ArrayHopperI {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(new ArrayHopperI().canJump(array));
    }


    /**
     * 使用dp求一个数组能否从初始点跳跃到终点
     *
     * time = O(n^2)
     *
     * space = O(n)
     */
    public boolean canJump(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return true;
        }

        // 创建dp数组
        // 物理意义: 在array数组中对应的index能否跳到终点
        boolean[] M = new boolean[array.length];

        // base case
        // 从终点一定能跳到终点
        M[M.length - 1] = true;

        // 从倒数第二个数开始遍历，看看所能跳跃的范围内是否有true
        // 如果有true则该值为true，否则为false
        for (int i = M.length - 2; i >= 0; i--) {
            // 从i这个角标能跳到的最远距离
            int index = i + array[i];
            // 如果到达或者超过数组的最后一个元素了，说明可以直接跳到终点
            if (index >= M.length - 1) {
                M[i] = true;
            }
            // 否则遍历[j, index]，看有没有标记为true的元素
            // 如果有那么说明当前位置可以通过搭桥的方式跳到最后一个元素
            for (int j = i; j <= index; j++) {
                if (M[j]) {
                    M[i] = true;
                    break;
                }
            }
        }

        return M[0];
    }
}
