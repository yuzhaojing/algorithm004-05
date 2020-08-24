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
            // 遍历[i + 1, i + array[i]]，不需要考虑i + array[i] > array.length - 1的问题
            // 因为array.length - 1的值一定为true，最多到这里就会break出去
            for (int j = i; j <= i + array[i]; j++) {
                if (M[j]) {
                    M[i] = true;
                    break;
                }
            }
        }

        return M[0];
    }
}
