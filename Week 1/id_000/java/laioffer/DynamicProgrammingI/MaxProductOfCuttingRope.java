package laioffer.DynamicProgrammingI;

public class MaxProductOfCuttingRope {

    public static void main(String[] args) {
        System.out.println(new MaxProductOfCuttingRope().maxProduct(40));
        System.out.println(new MaxProductOfCuttingRope().maxProductBetter(40));
    }


    /**
     * 使用dp求最大乘积
     *
     * time = O(n^2)
     *
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
