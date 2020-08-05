package laioffer.hash_table;


import java.util.*;

public class MissingNumberI {

    public static void main(String[] args) {
        int[] combo = {12, 11, 10, 9, 4, 5, 1, 7, 2, 3, 8};
        System.out.println(new MissingNumberI().missing1(combo));
    }

    /**
     * 使用hash table计算缺失的数字
     * 1、将数组中的数据全部放入hash table中
     * 2、遍历1 ～ array.length + 1的数字
     * 3、将不存在hash table中的数字返回
     * <p>
     * time = O(2n) = O(n)
     * <p>
     * Space = O(n)
     */
    public int missing(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 1;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int num : array) {
            set.add(num);
        }

        for (int i = 1; i < array.length + 1; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return array.length + 1;
    }

    /**
     * 使用异或的方式计算缺失的数字
     * 在二进制层面上，0^0,1^1 = 0 | 1^0,0^1 = 1
     *
     * 异或满足一下几种情况
     * 1、num1  XOR num1 =  0
     * 2、num1  XOR  0 =  num1
     * 3、交换律：a xor b = b xor a
     * 4、结合律：a xor (b xor c) = (a xor b) xor c
     * 5、0是单元：0 xor a = a
     *
     * 计算过程:
     * 1.定义一个res为0，作为初始数据
     * 2.将res与array中的每一个数字进行异或
     * 3.遍历1 ～ array.length + 1的数字，将与array异或后的结果与之异或
     * 4.返回res
     *
     * time = O(n)
     *
     * space = O(1)
     */
    public int missing1(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 1;
        }

        int res = 0;
        for (int num : array) {
            res ^= num;
        }

        for (int i = 1; i <= array.length + 1; i++) {
            res ^= i;
        }

        return res;
    }
}
