package laioffer.DynamicProgrammingI;

public class MaxProductOfCuttingRope {

    public static void main(String[] args) {
        System.out.println(new MaxProductOfCuttingRope().maxProduct(40));
        System.out.println(new MaxProductOfCuttingRope().maxProductBetter(40));
    }

    /**
     * 假设：length >= 2
     * 如果不符合假设，无法将绳子切成两段，暂定返回0
     * high level: 使用一维DP解答
     * mid level: linear scan回头看，每次需要看全部的元素
     *            每次切分的时候分成左大段，右小段
     *            左大段通过历史的计算获取，右小段为不可分割的部分
     *  1、M[i]表示绳子长度为i的时候，切出的最大乘积
     *  2、base case: M[0] = 0, M[1] = 0
     *  3、induction rule: M[i] = for j in [1, i)
     *                            Max(j, M[j]) * (i - j)
     * time = O(n^2)
     * space = O(n)
     */
    public int maxProductBetter(int length) {
        // Write your solution here
        if (length <= 1) {
            return 0;
        }

        int[] M = new int[length + 1];

        // base case
        M[0] = 0;
        M[1] = 0;

        // 递推面条的长度，由2->length
        for (int i = 2; i <= length; i++) {
            // j表示左边手里那一段，这一段还可以继续切
            // i - j的这一段表示已经下锅的面条，不能再切了
            for (int j = 1; j < i; j++) {
                // induction rule
                M[i] = Math.max(M[i],Math.max(j, M[j]) * (i - j));
            }
        }

        return M[length];
    }

    /**
     * 使用DFS的方式计算最大乘积
     *
     * time = O(n!)
     *
     * space = O(n)
     */
    public int maxProduct(int length) {
        // Write your solution here
        if (length <= 1) {
            return 0;
        }

        int maxProduct = 0;
        for (int i = 1; i < length; i++) {
            maxProduct = Math.max(maxProduct, Math.max(i, maxProduct(i)) * (length - i));
        }

        return maxProduct;
    }
}
